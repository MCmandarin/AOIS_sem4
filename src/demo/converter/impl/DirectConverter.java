package demo.converter.impl;

import demo.converter.Converter;

public class DirectConverter implements Converter {
//    public int binaryToInteger(String binaryNumber) {
//        char signSymbol = validateForBinaryNumber(binaryNumber);
//        StringBuilder correctBinaryNumber = new StringBuilder(binaryNumber);
//        correctBinaryNumber.setCharAt(0, '0');
//        binaryNumber = correctBinaryNumber.toString();
//
//        int integerNumber = 0;
//        for (int i = 0; i < binaryNumber.length(); i++) {
//            int number = Character.getNumericValue(binaryNumber.charAt(i));
//            integerNumber += (int) (number * Math.pow(2, binaryNumber.length() - i - 1));
//        }
//        if (signSymbol == '1') {
//            return integerNumber * -1;
//        }
//        return integerNumber;
//    }

    @Override
    public String integerToBinary(int integerNumber) {
        StringBuilder binaryNumber = new StringBuilder();
        char signSymbol = validateForIntegerNumber(integerNumber);
        while (integerNumber != 0) {
            binaryNumber.append(Math.abs(integerNumber) % 2);
            integerNumber = integerNumber / 2;
        }
        binaryNumber.append(signSymbol);
        binaryNumber.reverse();
        while (binaryNumber.length() < BIT) {
            binaryNumber.insert(0, '0');
        }
        return binaryNumber.toString();
    }

    private char validateForIntegerNumber(int integerNumber) {
        if (integerNumber >= 0) {
            return '0';
        }
        return '1';
    }

//    private char validateForBinaryNumber(String binaryNumber) {
//        if (binaryNumber.charAt(0) == '1') {
//            return '1';
//        }
//        return '0';
//    }
}
