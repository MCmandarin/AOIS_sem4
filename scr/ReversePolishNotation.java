import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {
        String expression = "!(A & B) | (C -> D)";
        String rpn = convertToRPN(expression);
        System.out.println("Обратная польская запись: " + rpn);
    }

    public static String convertToRPN(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Убираем '(' из стека
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static boolean isOperator(char ch) {
        return ch == '&' || ch == '|' || ch == '!' || ch == '-' || ch == '~';
    }

    public static int precedence(char operator) {
        return switch (operator) {
            case '&', '|' -> 1;
            case '!' -> 2;
            case '~' -> 3;
            case '-' -> 4;
            default -> -1;
        };
    }
}
