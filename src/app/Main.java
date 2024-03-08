package app;


public class Main {
    public static void main(String[] args) {
        int numberForConvert = -16;
        Converter converter = new Converter();
        String directBinary = converter.integerToDirect(numberForConvert);
        System.out.println("Direct binary: " + directBinary);

        String reverseBinary = converter.directToReverse(directBinary);
        System.out.println("Reverse number: " + reverseBinary);

        String additionalBinary = converter.directToAdditional(directBinary);
        System.out.println("Additional number: " + additionalBinary);

        System.out.println("Default number: " + converter.directToInteger("10000001"));

        System.out.println("#############################################");

        int firstAdditionalSum = 155;
        int secondAdditionalSum = 14;
        Operation operation = new Operation();
        String sumResult = operation.additionalSum(firstAdditionalSum, secondAdditionalSum);
        System.out.println("Final sum result: " + sumResult + " --> " + converter.directToInteger(sumResult));

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
        String resultFloatingPointSum = operation.floatingPointSum(first, second);
        System.out.println("Result floating point sum: " + resultFloatingPointSum + " ---> " + (first + second));

        System.out.println("#############################################");

        int firstNum = 14;
        int secondNum = 15;
        String resultMultiplication = operation.directMultiplication(firstNum, secondNum);
        System.out.println("Result multiplication: " + resultMultiplication + " ---> " + converter.directToInteger(resultMultiplication));

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
        String divideResult = operation.divide(dividend, divisor);
        System.out.println("Divided result: " + divideResult + " ---> " + (float) dividend / divisor);
    }
}
