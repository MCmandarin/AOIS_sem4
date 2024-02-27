public class ReverseConverter {
    public static int binaryToInteger(String binaryNumber) {
        return 0;
    }

    public static String integerToBinary(int integerNumber) {
        String binaryNumber = DirectConverter.integerToBinary(integerNumber);
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
