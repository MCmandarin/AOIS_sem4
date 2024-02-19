public class Main {
    public static void main(String[] args) {

        String a = Converter.integerToBinary( 12);
        System.out.println(a);

        int b = Converter.binaryToInteger(a);
        System.out.println(b);
    }
}