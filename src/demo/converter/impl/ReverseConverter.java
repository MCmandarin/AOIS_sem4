package demo.converter.impl;

import demo.converter.Converter;

public class ReverseConverter implements Converter {

    @Override
    public String integerToBinary(int integerNumber) {
        Converter converter = new DirectConverter();
        String binaryNumber = converter.integerToBinary(integerNumber);
        char[] charArray = binaryNumber.toCharArray();
        int signIndex = validateForSign(binaryNumber);
        if (integerNumber > 0) {
            return binaryNumber;
        }
        for (int i = charArray.length - 1; i >= signIndex; i--) {
            if (charArray[i] == '0') {
                charArray[i] = '1';
                continue;
            }
            charArray[i] = '0';
        }
        charArray[signIndex] = '1';
        return new String(charArray);
    }

    private int validateForSign(String binaryNumber) {
        char[] charArray = binaryNumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '0' && charArray[i + 1] == '1' && charArray[i + 2] == '1') {
                charArray[i] = '0';
                return i + 1;
            } else if (charArray[i] == '1' && charArray[i + 1] == '1') {
                charArray[i] = '0';
                return i;
            } else if (charArray[i] == '0' && charArray[i + 1] == '1') {
                return i;
            }
        }
        return 0;
    }
}
