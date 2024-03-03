package app;

public class Operation {
    public String additionalSum(String firstAdditional, String secondAdditional) {
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
        StringBuilder result = getAdditionalSumResult(firstAdditional, secondAdditional, maxLength);

        return result.toString();
    }

    private StringBuilder getAdditionalSumResult(String first, String second, int maxLength) {
        StringBuilder result = new StringBuilder();

        int carry = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int sum = (first.charAt(i) - '0') + (second.charAt(i) - '0') + carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        return result;
    }

    public String getFinalAdditionalSumResult(String firstAdditional, String secondAdditional, String sumResult) {
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
        String resultMantissa = operation.getMantissaSum(firstMantissa.substring(0, 23), secondMantissa.substring(0, 23));
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

    private String getMantissaSum(String first, String second) {
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
        return result.toString();
    }
}
