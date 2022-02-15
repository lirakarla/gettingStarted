package com.pluralsight.calcengine;

public class Main {

    public static void main(String[] args) {
                double[] leftVals={100.0d,25.0d,225.0d,11.0d};
                double [] rightVlas ={50.0d,92.0d,17.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        if(args.length == 0){

            for(int i=0; i<opCodes.length; i++){

                results[i]= execute(opCodes[i], leftvals[i], rightvals[i]);
            }
            for (double currentResult: results) {
                System.out.println(currentResult);
            }
        } else if(args.length == 1 && args[0].equals("interactive")){
            executeInteractively();
        }
        else if (args.length == 3){ //Command line arguments
            handleCommandLine(args);
        }else {
            System.out.println("Please provide operation code and 2 numerical values");}
    }

    //Getting inputs from user
    static void executeInteractively(){
        System.out.println("Enter an operation and 2 numbers:");
        Scanner scanner = new Scanner(System.in); //Gets input from user
        String userInput = scanner.nextLine(); //assigns user input to the string, scanner.nextLine() reads all input from user till user hits the enter key
        String[] parts = userInput.split(" ");//splits the user inputs into parts of an array
        performOperations(parts);
    }

    private  static void performOperations(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
        if(opCode == 'w'){
            handleWhen(parts);

        }else{
            double leftval = valueFromWord(parts[1]);
            double rightval = valueFromWord(parts[2]);
            double result = execute(opCode, leftval, rightval);
            displayResult (opCode, leftval, rightval, result);
        }

    }

    private static void handleWhen(String[] parts) {
        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d is %s", startDate, daysToAdd, newDate);
        System.out.println(output);
    }

    private static void displayResult(char opCode, double leftval, double rightval, double result){
        char symbol = symbolFromOpcode(opCode);
   /* StringBuilder builder = new StringBuilder(40);
    builder.append(leftval);
    builder.append(" ");
    builder.append(symbol);
    builder.append(" ");
    builder.append(rightval);
    builder.append(" = ");
    builder.append(result);
    String output = builder.toString(); */ //Use of StringBuilder
        String output = String.format("%.3f %c %.3f = %.3f", leftval, symbol, rightval, result);

        System.out.println(output);
    }
    //Converts character of opcode to symbol
    private static char symbolFromOpcode (char opCode){
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';
        for (int index = 0; index<opCodes.length; index++){
            if (opCode == opCodes[index]){
                symbol = symbols[index];
                break;
            }
        }
        return symbol;
    }

    //Handling command line
    private static void handleCommandLine (String[] args){
        char opCode = args[0].charAt(0);
        double leftval = Double.parseDouble(args[1]);
        double rightval = Double.parseDouble(args[2]);
        double result = execute(opCode, leftval, rightval);
        System.out.println(result);
    }

    //Method
    static double execute (char opCode, double leftval, double rightval){
        double result;
        switch (opCode) {
            case 'a' :
                result = leftval + rightval;
                break;
            case 's' :
                result = leftval - rightval;
                break;
            case 'd' :
                result= rightval!= 0.0d ? leftval / rightval : 0.0d;
                break;
            case 'm' :
                result = leftval * rightval;
                break;

            default:
                System.out.println("Invalid Opcode"+opCode);
                result= 0.00d;
                break;

        }
        return result;
    }
    //Inputs from user are statements and this code converts them to the relevant types
    static char opCodeFromString(String operationName) {
        char opCode = operationName.charAt(0);
        return opCode;
    }

    static double valueFromWord(String word){
        //code that caters for word input
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        double value = -1d;
        for (int index= 0; index<numberWords.length; index++){
            if(word.equals(numberWords[index])){
                value = index;
                break;
            }
        }
//Code that caters for numerical inputs
        if(value == -1){
            value = Double.parseDouble(word);
        }


        return value;
    }
}
