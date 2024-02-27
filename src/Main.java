public class Main {
    public static void main(String[] args) {
        int numberForConvert = -12;

        String directBinary = DirectConverter.integerToBinary(numberForConvert);
        System.out.println("Direct binary: " + directBinary);

        String reverseBinary = ReverseConverter.integerToBinary(numberForConvert);
        System.out.println("Reverse number: " + reverseBinary);

        String additionalBinary = AdditionalConverter.integerToBinary(numberForConvert);
        System.out.println("Additional number: " + additionalBinary);

        int integerBinary = DirectConverter.binaryToInteger(directBinary);
        System.out.println("Default number: " + integerBinary);
    }
}