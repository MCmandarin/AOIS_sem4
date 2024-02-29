package demo.converter.impl;

import demo.converter.Converter;

public class AdditionalConverter implements Converter {
    private boolean negativeSign;
    @Override
    public String integerToBinary(int integerNumber) {
        Converter converter = new ReverseConverter();
        String binaryNumber = converter.integerToBinary(integerNumber);
        char[] charArray = binaryNumber.toCharArray();
        if (integerNumber > 0) {
            return binaryNumber;
        }
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (charArray[i] == '1') {
                charArray[i] = '0';
                continue;
            }
            charArray[i] = '1';
            break;
        }
        return new String(charArray);
    }
}
