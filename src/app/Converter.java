package app;

public class Converter {
    public int directToInteger(String binaryNumber) {
        char signSymbol = validateForBinaryNumber(binaryNumber);
        StringBuilder correctBinaryNumber = new StringBuilder(binaryNumber);
        correctBinaryNumber.setCharAt(0, '0');
        binaryNumber = correctBinaryNumber.toString();

        int integerNumber = 0;
        for (int i = 0; i < binaryNumber.length(); i++) {
            int number = Character.getNumericValue(binaryNumber.charAt(i));
            integerNumber += (int) (number * Math.pow(2, binaryNumber.length() - i - 1));
        }
        if (signSymbol == '1') {
            return integerNumber * -1;
        }
        return integerNumber;
    }

    public String integerToDirect(int integerNumber) {
        StringBuilder binaryNumber = new StringBuilder();
        char signSymbol = validateForIntegerNumber(integerNumber);
        while (integerNumber != 0) {
            binaryNumber.append(Math.abs(integerNumber) % 2);
            integerNumber = integerNumber / 2;
        }
        binaryNumber.append(signSymbol);
        binaryNumber.reverse();
        return binaryNumber.toString();
    }

    private char validateForIntegerNumber(int integerNumber) {
        if (integerNumber >= 0) {
            return '0';
        }
        return '1';
    }

    private char validateForBinaryNumber(String binaryNumber) {
        if (binaryNumber.charAt(0) == '1') {
            return '1';
        }
        return '0';
    }

    public String directToReverse(String directNumber) {
        char[] charArray = directNumber.toCharArray();
        if (charArray[0] == '0') {
            return directNumber;
        }
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == '0') {
                charArray[i] = '1';
                continue;
            }
            charArray[i] = '0';
        }
        return new String(charArray);
    }

    public String directToAdditional(String directNumber) {
        directNumber = directToReverse(directNumber);
        char[] charArray = directNumber.toCharArray();
        if (charArray[0] == '0') {
            return directNumber;
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

