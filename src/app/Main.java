package app;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operation operation = new Operation();
        int numberForConvert = -16;
        Converter converter = new Converter();
        getAllForms(converter, numberForConvert);

        System.out.println("#############################################");

        int firstAdditionalSum = 155;
        int secondAdditionalSum = 14;
        String sumResult1 = operation.additionalSum(firstAdditionalSum, secondAdditionalSum);
        System.out.println("Final sum result: " + sumResult1 + " --> " + converter.directToInteger(sumResult1));

        System.out.println("#############################################");

        float floatNumber = 1024.444F;
        System.out.println("Default number: " + floatNumber);
        String floatBinary = converter.floatToBinary(floatNumber);
        System.out.println("Float binary number: " + floatBinary);
        String floatingPointNumber = converter.floatToFloatingPointNumber(floatNumber);
        System.out.println("Floating point binary number: " + floatingPointNumber);

        System.out.println("#############################################");

        float first = 128.70F;
        float second = 129.44F;
        String resultFloatingPointSum1 = operation.floatingPointSum(first, second);
        System.out.println("Result floating point sum: " + resultFloatingPointSum1 + " ---> " + (first + second));

        System.out.println("#############################################");

        int firstNum = 14;
        int secondNum = 15;
        String resultMultiplication1 = operation.directMultiplication(firstNum, secondNum);
        System.out.println("Result multiplication: " + resultMultiplication1 + " ---> " + converter.directToInteger(resultMultiplication1));

        System.out.println("#############################################");

        int subtractInt1 = 3;
        int subtractInt2 = 2;
        String subtractBinary1 = converter.integerToDirect(subtractInt1);
        String subtractBinary2 = converter.integerToDirect(subtractInt2);
        String subtractResult = operation.subtractBinary(subtractBinary1, subtractBinary2);
        System.out.println("Subtract result: " + subtractResult);

        System.out.println("#############################################");

        int dividend = 25;
        int divisor = 15;
        String divideResult1 = operation.divide(dividend, divisor);
        System.out.println("Divided result: " + divideResult1 + " ---> " + (float) dividend / divisor);

        System.out.println("#############################################");

        System.out.println("""
                ~~~~~Menu~~~~~
                1. Sum
                2. Multiplication
                3. Division
                4. Positive floating point sum""");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("Enter first number: ");
            int firstNumb = scanner.nextInt();
            getAllForms(converter, firstNumb);
            System.out.print("\nEnter second number: ");
            int secondNumb = scanner.nextInt();
            getAllForms(converter, secondNumb);

            System.out.println("~~~~~~~~~~");
            String sumResult = operation.additionalSum(firstNumb, secondNumb);
            System.out.println("Final sum result: " + sumResult + " --> " + converter.directToInteger(sumResult));
        } else if (choice == 2) {
            System.out.print("Enter first number: ");
            int firstNumb = scanner.nextInt();
            getAllForms(converter, firstNumb);
            System.out.print("\nEnter second number: ");
            int secondNumb = scanner.nextInt();
            getAllForms(converter, secondNumb);

            System.out.println("~~~~~~~~~~");
            String resultMultiplication = operation.directMultiplication(firstNumb, secondNumb);
            System.out.println("Result multiplication: " + resultMultiplication + " ---> " + converter.directToInteger(resultMultiplication));
        } else if (choice == 3) {
            System.out.print("Enter first number: ");
            int firstNumb = scanner.nextInt();
            getAllForms(converter, firstNumb);
            System.out.print("\nEnter second number: ");
            int secondNumb = scanner.nextInt();
            getAllForms(converter, secondNumb);

            System.out.println("~~~~~~~~~~");
            String divideResult = operation.divide(firstNumb, secondNumb);
            System.out.println("Divided result: " + divideResult + " ---> " + (float) firstNumb / secondNumb);
        } else if (choice == 4) {
            System.out.print("Enter first number: ");
            float firstNumb = scanner.nextFloat();
            System.out.print("\nEnter second number: ");
            float secondNumb = scanner.nextFloat();

            System.out.println("~~~~~~~~~~");
            String resultFloatingPointSum = operation.floatingPointSum(firstNumb, secondNumb);
            System.out.println("Result floating point sum: " + resultFloatingPointSum + " ---> " + (firstNumb + secondNumb));
        }

    }

    private static void getAllForms(Converter converter, int numberForConvert) {
        String directBinary = converter.integerToDirect(numberForConvert);
        System.out.println("Direct binary: " + directBinary);

        String reverseBinary = converter.directToReverse(directBinary);
        System.out.println("Reverse number: " + reverseBinary);

        String additionalBinary = converter.directToAdditional(directBinary);
        System.out.println("Additional number: " + additionalBinary);

        System.out.println("Default number: " + converter.directToInteger(directBinary));
    }
}
