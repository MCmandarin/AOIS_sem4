import converter.Converter;
import converter.impl.AdditionalConverter;
import converter.impl.DirectConverter;
import converter.impl.ReverseConverter;

public class Main {
    public static void main(String[] args) {
        int numberForConvert = -12;
        DirectConverter directConverter = new DirectConverter();
        String directBinary = directConverter.integerToBinary(numberForConvert);
        System.out.println("Direct binary: " + directBinary);

        Converter reverseConverter = new ReverseConverter();
        String reverseBinary = reverseConverter.integerToBinary(numberForConvert);
        System.out.println("Reverse number: " + reverseBinary);

        Converter additionalConverter = new AdditionalConverter();
        String additionalBinary = additionalConverter.integerToBinary(numberForConvert);
        System.out.println("Additional number: " + additionalBinary);

        int integerBinary = directConverter.binaryToInteger(directBinary);
        System.out.println("Default number: " + integerBinary);
    }
}