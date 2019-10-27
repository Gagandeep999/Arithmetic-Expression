/*
 Author: Gagandeep Singh
 Date: 21 February, 2018
 Purpose: Java code that calculates the output of a syntactically arithmeatic expression.
 */

import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Character.isDigit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Calculator {
    
    //this method is ised to split the expression. It reads character by character and depending on the
    //conditions stores each input into a StringBuilder(which are mutable strings) and when a token is read it
    //puts that into an ArrayList because we dont know the size of the array list.
	private static String[] split(String input) {
		ArrayList<String> exp = new ArrayList<String>();
		char[] each = input.toCharArray();
		StringBuilder individual = new StringBuilder();
		for(int i=0;i<each.length;i++){
			if(each[i] == ' '){
				continue;
			}
			else if(Character.isDigit(each[i])){
				individual.append(each[i]);
				while((i<each.length-1) && Character.isDigit(each[i+1])){
					individual.append(each[i+1]);
					i++;
				}				
			}
			else if(!Character.isDigit(each[i])){
				individual.append(each[i]);
				if((i<each.length-1) && (each[i] == '=' && each[i+1] == '=')){
					individual.append(each[i+1]);
					i++;
				}
				else if((i<each.length-1)&&(each[i] == '<' && each[i+1] == '=')){
					individual.append(each[i+1]);
					i++;
				}
				else if((i<each.length-1)&&(each[i] == '>' && each[i+1] == '=')){
					individual.append(each[i+1]);
					i++;
				}
				else if((i<each.length-1)&&(each[i] == '!' && each[i+1] == '=')){
					individual.append(each[i+1]);
					i++;
				}
				else if((each[0] == '-') && (i==0)){
					while((i<each.length-1) && Character.isDigit(each[i+1])){
						individual.append(each[i+1]);
						++i;
					}				
				}
				else if((each[i] == '-')&&(!Character.isDigit(each[i-1]))){
					while((i<each.length-1) && Character.isDigit(each[i+1])){
						individual.append(each[i+1]);
						i++;
					}
				}
			}
			exp.add(individual.toString());
			individual.delete(0, individual.length());
		}
        //conversion of ArrayList to Array
		String[] arr = exp.toArray(new String[exp.size()]);
//		for(String x : arr){
//			System.out.println(x);}
		return arr;
	}

	public static void main(String args[]) {

		MyStack<String> stkInt = new MyStack<>();//user created stack that stores numbers as Strings
		MyStack<String> stkOpr = new MyStack<>();//user created stack that stores operators as Strings
		Precedence p = new Precedence();
		Operation op = new Operation();
		Scanner sc = null;PrintWriter pw = null;String finalAnswer="";
		try{
			sc = new Scanner(new FileInputStream("Input.txt"));
			pw = new PrintWriter(new FileOutputStream("Results.txt"));
		}catch(FileNotFoundException e){
			System.out.println("File not found. Exiting!!");
			System.exit(0);
		}
		while(sc.hasNextLine()){
			String input = sc.nextLine();   //reads an input from the text file
			String[] each = split(input);   //splits the string as per our conditions
			for (String a :
				each) {
				if(a.equals(" "))
					continue;
                //if the array element is a number
				if (a.matches("[0-9]+")) {
					// System.out.println("pushed in stack "+a);
					stkInt.push(a);
				}//if the array element is a negative number
				else if((a.startsWith("-"))&&(a.length()>1)){
					// System.out.println("pushed in stack "+a);
					stkInt.push(a);
				}//if the  array element is not a digit
				else if ((a != " ") && (!a.matches("[0-9]+"))) {
                    //if it's factorial calculate it right away and then do the other operations
					if(a.equals("!")){
						String x = stkInt.pop();
						String answer = op.operationPerformed(x, a);
						stkInt.push(answer);
					}
					else if ((stkOpr.isEmpty()) && (!a.equals(")"))) {
						stkOpr.push(a);
					}
					else if (a.equals("(") || (stkOpr.top().equals("("))) {
						if(a.equals(")")){
							stkOpr.pop();
							continue;
						}
						stkOpr.push(a);
					}//if closing parenthesis is encountered the run a loop till we reach the matching closing
                    //braces and perform necessary operations
					else if (a.equalsIgnoreCase(")")) {
						while(!stkOpr.top().equals("(")){
							String x = stkInt.pop();
							String y = stkInt.pop();
							String c = stkOpr.pop();
							String answer = op.operationPerformed(y, x, c);
							stkInt.push(answer);
						}
						stkOpr.pop();
					}

					else if(p.PrecedenceValue(a) < (p.PrecedenceValue(stkOpr.top()))){
						while((!stkOpr.isEmpty())&&(p.PrecedenceValue(a) < (p.PrecedenceValue(stkOpr.top())))&& !stkOpr.top().equals("(")){
							String x = stkInt.pop();
							String y = stkInt.pop();
							String c = stkOpr.pop();
							String answer = op.operationPerformed(y, x, c);
							stkInt.push(answer);	
						}
						stkOpr.push(a);
					}

					else if (p.PrecedenceValue(a) <= (p.PrecedenceValue(stkOpr.top()))) {

						String x = stkInt.pop();
						String y = stkInt.pop();
						String c = stkOpr.pop();
						String answer = op.operationPerformed(y, x, c);
						stkInt.push(answer);
						stkOpr.push(a);
					}
					else if(p.PrecedenceValue(a) > (p.PrecedenceValue(stkOpr.top()))){
						stkOpr.push(a);
					}

				}
			}
            //after the for loop has finished going through the array the stack will have all lower precedence
            //operators which were not executed before. This while loop makes sure that all the operators get
            //executed and that the correct output is returned.
			while (!stkOpr.isEmpty()) {
				String x = stkInt.pop();
				String y = stkInt.pop();
				String c = stkOpr.pop();
				String answer = op.operationPerformed(y, x, c);
				finalAnswer = answer;
				stkInt.push(answer);
			}
			pw.println(input);
			pw.println(finalAnswer+"\n");
		}
		sc.close();
		pw.close();
	}
}
