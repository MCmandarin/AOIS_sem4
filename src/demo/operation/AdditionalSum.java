package demo.operation;

import demo.converter.Converter;
import demo.converter.impl.DirectConverter;

import java.util.Arrays;

public class AdditionalSum {
    public String sum(String first, String second) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        String newFirst = validateForSign(first);
        String newSecond = validateForSign(second);
        for (int i = first.length() - 1; i >= 0; i--) {
            int sum = (first.charAt(i) - '0') + (second.charAt(i) - '0') + carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            result.insert(0, '1');
        }


        return result.toString();
    }

    private String insertSign(String first, String second) {
        Converter converter = new DirectConverter();
        return null;
    }

    private String validateForSign(String binaryNumber) {
        char[] charArray = binaryNumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1' && charArray[i + 1] == '1') {
                charArray[i] = '0';
                return Arrays.toString(charArray);
            } else if (charArray[i] == '0' && charArray[i + 1] == '1') {
                return binaryNumber;
            }
        }
        return binaryNumber;
    }
}
