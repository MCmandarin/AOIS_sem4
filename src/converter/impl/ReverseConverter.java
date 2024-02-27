package converter.impl;

import converter.Converter;

public class ReverseConverter implements Converter {
    @Override
    public String integerToBinary(int integerNumber) {
        Converter converter = new DirectConverter();
        String binaryNumber = converter.integerToBinary(integerNumber);
        char[] charArray = binaryNumber.toCharArray();
        if(charArray[0] == '0') {
            return binaryNumber;
        }
        for(int i = 1; i<charArray.length; i++) {
            if(charArray[i] == '0') {
                charArray[i] = '1';
                continue;
            }
            charArray[i] = '0';
        }
        return new String(charArray);
    }
}
