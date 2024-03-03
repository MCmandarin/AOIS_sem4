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

        String firstDirect = converter.integerToDirect(3);
        String secondDirect = converter.integerToDirect(-14);
        Operation operation = new Operation();
        System.out.println("BFirst: " + firstDirect + " BSecond: " + secondDirect);
        String firstAdditional = converter.directToAdditional(firstDirect);
        String secondAdditional = converter.directToAdditional(secondDirect);
        System.out.println("AFirst: " + firstAdditional + " ASecond: " + secondAdditional);
        String sumResult = operation.additionalSum(firstAdditional, secondAdditional);
        System.out.println("Sum Result: " + sumResult);
        String finalSumResult = operation.getFinalAdditionalSumResult(firstAdditional, secondAdditional, sumResult);
        System.out.println("Final sum result: " + finalSumResult + " --> " + converter.directToInteger(finalSumResult));

        System.out.println("#############################################");

        float floatNumber = 1024.444F;
        System.out.println("Default number: " + floatNumber);
        String floatBinary = converter.floatToBinary(floatNumber);
        System.out.println("Float binary number: " + floatBinary);

        System.out.println("#############################################");

        String floatingPointNumber = converter.floatToFloatingPointNumber(floatNumber);
        System.out.println("Floating point binary number: " + floatingPointNumber);

        System.out.println("#############################################");

        float first = 10.70F;
        float second = 64.44F;
        String resultFloatingPointSum = operation.floatingPointSum(first, second);
        System.out.println("Result floating point sum: " + resultFloatingPointSum);
    }
}