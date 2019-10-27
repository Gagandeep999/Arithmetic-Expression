/*
 Author: Gagandeep Singh
 Date: 21 February, 2018
 Purpose: A class that contains a method that returns the integer value in terms of precedence of operators.
 */


public class Precedence {

    private int p;

    public int PrecedenceValue(String c){
        switch (c){
            case "(":
                p = 8;
                return p;
            case ")":
                p = 8;
                return p;
            case "!":
                p = 7;
                return p;
            case "^":
                p = 6;
                return p;
            case "/":
                p = 5;
                return p;
            case "*":
                p = 5;
                return p;
            case "+":
                p = 4;
                return p;
            case "-":
                p = 4;
                return p;
            case ">":
                p = 3;
                return p;
            case ">=":
                p = 3;
                return p;
            case "<":
                p = 2;
                return p;
            case "<=":
                p = 2;
                return p;
            case "==":
                p = 1;
                return p;
            case "!=":
                p = 1;
                return p;
        }
        return 0;
    }

}
 
