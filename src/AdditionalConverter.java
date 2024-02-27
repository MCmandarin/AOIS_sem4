public class AdditionalConverter {
    public static String integerToBinary(int integerNumber) {
        String binaryNumber = ReverseConverter.integerToBinary(integerNumber);
        char[] charArray = binaryNumber.toCharArray();
        if (charArray[0] == '0') {
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
