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
        System.out.println("``````````````````````````````````");

        // Построение таблицы истинности
        System.out.println("Таблица истинности:");
//        buildTruthTable(rpn);
        Map<Map<Character, Boolean>, Boolean> truthTable = buildTruthTable(rpn);
        for (Map.Entry<Map<Character, Boolean>, Boolean> entry : truthTable.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        System.out.println("``````````````````````````````````");

        // Построение СДНФ и СКНФ
        System.out.println("СДНФ: " + buildSDNF(truthTable));
        System.out.println("СКНФ: " + buildSKNF(truthTable));
        System.out.println("``````````````````````````````````");

        System.out.println("Числовая формула СДНФ: " + numericFormSDNF(truthTable));
        System.out.println("Числовая формула СКНФ: " + numericFormSKNF(truthTable));
        System.out.println("``````````````````````````````````");
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

    public static Map<Map<Character, Boolean>, Boolean> buildTruthTable(String expression) {
        Map<Map<Character, Boolean>, Boolean> truthTable = new HashMap<>();

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
//            System.out.print(variableValues + " => " + result);
//            System.out.println();
            truthTable.put(variableValues, result);
        }

        return truthTable;
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

    public static String buildSDNF(Map<Map<Character, Boolean>, Boolean> truthTable) {
        List<String> terms = new ArrayList<>();
        for (Map.Entry<Map<Character, Boolean>, Boolean> entry : truthTable.entrySet()) {
            if (entry.getValue()) {
                StringBuilder term = new StringBuilder();
                Map<Character, Boolean> variables = entry.getKey();
                for (Map.Entry<Character, Boolean> varEntry : variables.entrySet()) {
                    char variable = varEntry.getKey();
                    boolean value = varEntry.getValue();
                    if (value) {
                        term.append(variable);
                    } else {
                        term.append("!").append(variable);
                    }
                }
                terms.add(term.toString());
            }
        }
        return String.join(" | ", terms);
    }

    public static String buildSKNF(Map<Map<Character, Boolean>, Boolean> truthTable) {
        List<String> terms = new ArrayList<>();
        for (Map.Entry<Map<Character, Boolean>, Boolean> entry : truthTable.entrySet()) {
            if (!entry.getValue()) {
                StringBuilder term = new StringBuilder();
                Map<Character, Boolean> variables = entry.getKey();
                for (Map.Entry<Character, Boolean> varEntry : variables.entrySet()) {
                    char variable = varEntry.getKey();
                    boolean value = varEntry.getValue();
                    if (value) {
                        term.append("!").append(variable);
                    } else {
                        term.append(variable);
                    }
                }
                terms.add(term.toString());
            }
        }
        return String.join(" & ", terms);
    }

    public static String numericFormSDNF(Map<Map<Character, Boolean>, Boolean> truthTable) {
        StringBuilder numericFormula = new StringBuilder("(");
        boolean firstEntry = true;
        int index = 0;
        for (Map.Entry<Map<Character, Boolean>, Boolean> entry : truthTable.entrySet()) {
            if (entry.getValue()) {
                if (!firstEntry) {
                    numericFormula.append(", ");
                } else {
                    firstEntry = false;
                }
                numericFormula.append(index);
            }
            index++;
        }
        numericFormula.append(") ∨");
        return numericFormula.toString();
    }

    public static String numericFormSKNF(Map<Map<Character, Boolean>, Boolean> truthTable) {
        StringBuilder numericFormula = new StringBuilder("(");
        boolean firstEntry = true;
        int index = 0;
        for (Map.Entry<Map<Character, Boolean>, Boolean> entry : truthTable.entrySet()) {
            if (!entry.getValue()) {
                if (!firstEntry) {
                    numericFormula.append(", ");
                } else {
                    firstEntry = false;
                }
                numericFormula.append(index);
            }
            index++;
        }
        numericFormula.append(") ∧");
        return numericFormula.toString();
    }
}
