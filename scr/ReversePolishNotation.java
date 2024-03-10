import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {
        String expression2 = "!(A & B) | (C -> D)";
        String expression = "(A | B) & !C";
        String rpn = convertToRPN(expression);
        System.out.println("Обратная польская запись: " + rpn);

        // Построение таблицы истинности
        System.out.println("Таблица истинности:");
        buildTruthTable(rpn);
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

    public static void buildTruthTable(String expression) {
        List<Character> variables = extractVariables(expression);
        int n = variables.size();
        int combinations = 1 << n; // 2 в степени n

        for (int i = 0; i < combinations; i++) {
            Map<Character, Boolean> variableValues = new HashMap<>();
            for (int j = 0; j < n; j++) {
                char variable = variables.get(j);
                boolean value = ((i >> j) & 1) == 1; // Получаем j-тый бит числа i
                variableValues.put(variable, value);
            }
            boolean result = evaluateExpression(expression, variableValues);
            System.out.print(variableValues + " => " + result);
            System.out.println();
        }
    }

    public static boolean evaluateExpression(String expression, Map<Character, Boolean> variableValues) {
        Stack<Boolean> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch)) {
                stack.push(variableValues.get(ch));
            } else if (isOperator(ch)) {
                if (ch == '!') {
                    stack.push(!stack.pop());
                } else {
                    boolean operand2 = stack.pop();
                    boolean operand1 = stack.pop();
                    boolean result = applyOperation(ch, operand1, operand2);
                    stack.push(result);
                }
            }
        }

        return stack.pop();
    }

    public static List<Character> extractVariables(String expression) {
        List<Character> variables = new ArrayList<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch) && !variables.contains(ch)) {
                variables.add(ch);
            }
        }
        return variables;
    }

    public static boolean applyOperation(char operator, boolean operand1, boolean operand2) {
        return switch (operator) {
            case '&' -> operand1 & operand2;
            case '|' -> operand1 | operand2;
            case '-' -> !operand1 | operand2;
            default -> false;
        };
    }
}
