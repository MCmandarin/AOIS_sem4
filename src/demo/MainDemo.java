package demo;

import demo.converter.Converter;
import demo.converter.impl.AdditionalConverter;
import demo.converter.impl.DirectConverter;
import demo.converter.impl.ReverseConverter;
import demo.operation.AdditionalSum;

public class MainDemo {
    public static void main(String[] args) {
        int numberForConvert = -482;
        DirectConverter directConverter = new DirectConverter();
        String directBinary = directConverter.integerToBinary(numberForConvert);
        System.out.println("Direct binary: " + directBinary);

        Converter reverseConverter = new ReverseConverter();
        String reverseBinary = reverseConverter.integerToBinary(numberForConvert);
        System.out.println("Reverse number: " + reverseBinary);

        Converter additionalConverter = new AdditionalConverter();
        String additionalBinary = additionalConverter.integerToBinary(numberForConvert);
        System.out.println("Additional number: " + additionalBinary);

        System.out.println("Default number: " + numberForConvert);

        int first = -482;
        int second = 22;
        AdditionalSum additionalSum = new AdditionalSum();
        String firstBinary = additionalConverter.integerToBinary(first);
        String secondBinary = additionalConverter.integerToBinary(second);
        System.out.println("BFirst: " + directConverter.integerToBinary(first) + " BSecond: " + directConverter.integerToBinary(second));
        System.out.println("AFirst: " + firstBinary + " ASecond: " + secondBinary);
        System.out.println(additionalSum.sum(firstBinary, secondBinary));

    }
}