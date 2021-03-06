/*
 Author: Gagandeep Singh
 Date: 21 February, 2018
 Purpose: This class has a method that takes three String inputs and depending on the type of operator passed in String "c" it does the operations.
 */


public class Operation {

    // private int x;
    // private int y;
    // private String c;

    public String operationPerformed(String x, String y, String c){
    	int xInt, yInt;
        switch (c){
            case "+":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
                return (Integer.toString(xInt+yInt));
            case "-":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	return (Integer.toString(xInt-yInt));
            case "*":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	return (Integer.toString(xInt*yInt));
            case "/":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	return (Integer.toString(xInt/yInt));
            case "^":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	return(Integer.toString((int)Math.pow(xInt, yInt)));            	
            case "<":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	if(xInt<yInt)
            		return "true";
            	return "false";
            case "<=":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	if(xInt<=yInt)
                	return "true";
                return "false";
            case ">":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	if(xInt>yInt)
            		return "true";
            	return "false";
            case ">=":
            	xInt = Integer.parseInt(x);
            	yInt = Integer.parseInt(y);
            	if(xInt>=yInt)
            		return "true";
            	return "false";
            case "==":
            	if(x.equals(y))
            		return "true";
            	return "false";
            case "!=":
            	if(!x.equals(y))
            		return "true";
            	return "false";
        }
        return null;
    }
    public String operationPerformed(String x, String c){
    	int fact = 1;int xInt;
    	switch (c){
    	case "!":
    		xInt = Integer.parseInt(x);
    		for(int i =1;i<=xInt;i++){
    			fact *= i;
    		}
    		return (Integer.toString(fact));
    	}
    	return null;
    }
}
