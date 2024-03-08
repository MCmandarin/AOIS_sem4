package app;

public class Operation {
    public String additionalSum(int first, int second) {
        Converter converter = new Converter();
        String firstDirect = converter.integerToDirect(first);
        String secondDirect = converter.integerToDirect(second);
        String firstAdditional = converter.directToAdditional(firstDirect);
        String secondAdditional = converter.directToAdditional(secondDirect);

        int maxLength = Math.max(firstAdditional.length(), secondAdditional.length());
        if ((firstAdditional.length() < maxLength) && firstAdditional.charAt(0) == '0') {
            firstAdditional = String.format("%" + maxLength + "s", firstAdditional).replace(' ', '0');
        } else if ((firstAdditional.length() < maxLength) && firstAdditional.charAt(0) == '1') {
            firstAdditional = String.format("%" + maxLength + "s", firstAdditional).replace(' ', '1');
        }
        if ((secondAdditional.length() < maxLength) && secondAdditional.charAt(0) == '0') {
            secondAdditional = String.format("%" + maxLength + "s", secondAdditional).replace(' ', '0');
        } else if ((secondAdditional.length() < maxLength) && secondAdditional.charAt(0) == '1') {
            secondAdditional = String.format("%" + maxLength + "s", secondAdditional).replace(' ', '1');
        }

        String sumResult = getAdditionalSumResult(firstAdditional, secondAdditional, maxLength);
        if (first > 0 && second > 0) {
            return '0' + sumResult;
        }
        return getFinalAdditionalSumResult(firstAdditional, secondAdditional, sumResult);
    }

    private String getAdditionalSumResult(String first, String second, int maxLength) {
        StringBuilder result = new StringBuilder();

        int carry = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int sum = (first.charAt(i) - '0') + (second.charAt(i) - '0') + carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        return result.toString();
    }

    private String getFinalAdditionalSumResult(String firstAdditional, String secondAdditional, String sumResult) {
        StringBuilder stringBuilder = new StringBuilder(sumResult);
        if (firstAdditional.charAt(0) == '1' && secondAdditional.charAt(0) == '1') {
            stringBuilder.insert(0, '1');
        }
        Converter converter = new Converter();
        if (stringBuilder.charAt(0) == '1') {
            return converter.directToAdditional(stringBuilder.toString());
        }
        return sumResult;
    }

    public String floatingPointSum(float first, float second) {
        Converter converter = new Converter();
        String firstFloatingPoint = converter.floatToFloatingPointNumber(first);
        String secondFloatingPoint = converter.floatToFloatingPointNumber(second);
        System.out.println("First number: " + firstFloatingPoint);
        System.out.println("Second number: " + secondFloatingPoint);
        String firstExponent = firstFloatingPoint.substring(0, 9);
        String secondExponent = secondFloatingPoint.substring(0, 9);
        int firstExponentNumber = converter.directToInteger(firstExponent);
        int secondExponentNumber = converter.directToInteger(secondExponent);
        int exponentDifference = Math.abs(firstExponentNumber - secondExponentNumber);

        StringBuilder firstMantissa = new StringBuilder(firstFloatingPoint.substring(9));
        firstMantissa.insert(0, '1');
        StringBuilder secondMantissa = new StringBuilder(secondFloatingPoint.substring(9));
        secondMantissa.insert(0, '1');
        String resultExponent;
        if (firstExponentNumber > secondExponentNumber) {
            for (int i = 0; i < exponentDifference; i++) {
                secondMantissa.insert(0, '0');
            }
            resultExponent = firstExponent;
        } else if (secondExponentNumber > firstExponentNumber) {
            for (int i = 0; i < exponentDifference; i++) {
                firstMantissa.insert(0, '0');
            }
            resultExponent = secondExponent;
        } else {
            resultExponent = firstExponent;
        }
        Operation operation = new Operation();
        StringBuilder resultMantissa = operation.getSum(firstMantissa.substring(0, 23), secondMantissa.substring(0, 23));
        if (resultMantissa.length() > 23) {
            return changeExponent(resultExponent) + resultMantissa.substring(1);
        }
        return resultExponent + resultMantissa.substring(1, 23) + '0';
    }

    private String changeExponent(String exponent) {
        char[] charArray = exponent.toCharArray();
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

    private StringBuilder getSum(String first, String second) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = first.length() - 1; i >= 0; i--) {
            int sum = (first.charAt(i) - '0') + (second.charAt(i) - '0') + carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            result.insert(0, '1');
        }
        return result;
    }

    public String directMultiplication(int first, int second) {
        Converter converter = new Converter();
        Operation operation = new Operation();
        String firstBinary = converter.integerToDirect(first).substring(1);
        String secondBinary = converter.integerToDirect(second).substring(1);
        StringBuilder resultBinary = new StringBuilder();
        resultBinary.append("0".repeat(firstBinary.length()));
        for (int i = secondBinary.length() - 1; i >= 0; i--) {
            if (secondBinary.charAt(i) == '1') {
                resultBinary = operation.getSum(firstBinary, resultBinary.toString());
                firstBinary += '0';
                while (resultBinary.length() < firstBinary.length()) {
                    resultBinary.insert(0, '0');
                }
                continue;
            }
            firstBinary += '0';
            resultBinary.insert(0, '0');
        }
        if ((first < 0 && second < 0) || (first > 0 && second > 0)) {
            resultBinary.insert(0, '0');
        } else if (first < 0 || second < 0) {
            resultBinary.insert(0, '1');
        }
        return resultBinary.toString();
    }

    public String subtractBinary(String binary1, String binary2) {
        int maxLength = Math.max(binary1.length(), binary2.length());
        if (binary1.length() < maxLength) {
            binary1 = String.format("%" + maxLength + "s", binary1).replace(' ', '0');
        }
        if (binary2.length() < maxLength) {
            binary2 = String.format("%" + maxLength + "s", binary2).replace(' ', '0');
        }
        int borrow = 0;
        StringBuilder result = new StringBuilder();

        for (int i = maxLength - 1; i >= 0; i--) {
            int bit1 = Character.getNumericValue(binary1.charAt(i));
            int bit2 = Character.getNumericValue(binary2.charAt(i));

            int difference = bit1 - bit2 - borrow;
            if (difference < 0) {
                difference += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.insert(0, difference);
        }
        return result.toString();
    }

    public String divide(int dividend, int divisor) {
        char sign;
        if (dividend < 0 && divisor < 0) {
            sign = '0';
        } else if (dividend < 0 || divisor < 0) {
            sign = '1';
        } else {
            sign = '0';
        }

        Converter converter = new Converter();
        String dividendBinary = converter.integerToDirect(dividend).substring(1);
        String divisorBinary = converter.integerToDirect(divisor).substring(1);
        return sign + division(dividendBinary, divisorBinary);
    }

    private String division(String dividend, String divisor) {
        StringBuilder result = new StringBuilder();
        String remainder = "0";

        for (int i = 0; i < dividend.length(); i++) {
            remainder = remainder + dividend.charAt(i);
            if (equalOrGreater(remainder, divisor)) {
                result.append("1");
                remainder = subtractBinary(remainder, divisor);
            } else {
                result.append("0");
            }
        }
        result.append(".");

        for (int i = 0; i < 5; i++) {
            remainder = remainder + "0";
            if (equalOrGreater(remainder, divisor)) {
                result.append("1");
                remainder = subtractBinary(remainder, divisor);
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    private boolean equalOrGreater(String first, String second) {
        int maxLength = Math.max(first.length(), second.length());
        if (first.length() < maxLength) {
            first = String.format("%" + maxLength + "s", first).replace(' ', '0');
        }
        if (second.length() < maxLength) {
            second = String.format("%" + maxLength + "s", second).replace(' ', '0');
        }

        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                int bit1 = Character.getNumericValue(first.charAt(i));
                int bit2 = Character.getNumericValue(second.charAt(i));

                if (bit1 > bit2) {
                    return true;
                } else if (bit1 < bit2) {
                    return false;
                }
            }
        }
        return true;
    }
}
