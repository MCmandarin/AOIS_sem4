package app;

public class Converter {
    private static final int SIZE_MANTISSA = 23;
    private static final int NUMBER_FOR_CONVERT_EXPONENT = 127;

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

    public String floatToBinary(float flotNumber) {
        String mainPart = integerToDirect((int) flotNumber);
        StringBuilder fractionalPart = new StringBuilder();
        float floatFractionalPart = Math.abs(flotNumber - (int) flotNumber);
        for (int i = 0; i < SIZE_MANTISSA; i++) {
            if (floatFractionalPart == 1) {
                fractionalPart.append(1);
                break;
            }
            floatFractionalPart *= 2;
            if (floatFractionalPart > 1) {
                fractionalPart.append(1);
                floatFractionalPart -= 1;
            } else if (floatFractionalPart < 1) {
                fractionalPart.append(0);
            }
        }
        return mainPart + '.' + fractionalPart;
    }

    public String floatToFloatingPointNumber(float flotNumber) {
        StringBuilder floatBinary = new StringBuilder(floatToBinary(flotNumber));
        char sign = floatBinary.charAt(0);
        floatBinary = new StringBuilder(floatBinary.substring(1));
        System.out.println("Sign " + sign);
        System.out.println("Binary without sign: " + floatBinary);


        int dotIndex = floatBinary.indexOf(".");
        System.out.println("Dot index: " + dotIndex);
        if (dotIndex != -1 && dotIndex != 0) {
            floatBinary.deleteCharAt(dotIndex);
            dotIndex = dotIndex - 1;
            floatBinary.insert(1, '.');
        }
        System.out.println(floatBinary);
        String mantissa = String.valueOf(floatBinary.substring(2));
        System.out.println("Raw mantissa: " + mantissa);
        if (mantissa.length() < SIZE_MANTISSA){
            mantissa = mantissa + "0".repeat(SIZE_MANTISSA - mantissa.length());
        }
        System.out.println("Full mantissa: " + mantissa);


        int intExponent = dotIndex + NUMBER_FOR_CONVERT_EXPONENT;
        String exponent = integerToDirect(intExponent).substring(1);
        System.out.println("Exponent: " + exponent);

        return sign + exponent + mantissa;
    }

}

