import java.util.*;

public class ConstructionOfSknfAndSdnf {
    public static void main(String[] args) {
        String expression3 = "((P - (Q & R)) ~ ((P & (!Q)) - (!R)))";
        String expression2 = "((((P & (!Q)) - R) ~ P) | (P & Q))";
        String expression = "(!A) - (C | B)";
        String rpn = convertToRPN(expression);
        System.out.println("Обратная польская запись: " + rpn);
        System.out.println("``````````````````````````````````");

        System.out.println("Таблица истинности:");
        Map<Map<Character, Boolean>, Boolean> truthTable = buildTruthTable(rpn);
        for (Map.Entry<Map<Character, Boolean>, Boolean> entry : truthTable.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        System.out.println("``````````````````````````````````");

        System.out.println("СДНФ: " + buildSDNF(truthTable));
        System.out.println("СКНФ: " + buildSKNF(truthTable));
        System.out.println("``````````````````````````````````");

        System.out.println("Числовая формула СДНФ: " + numericFormSDNF(truthTable));
        System.out.println("Числовая формула СКНФ: " + numericFormSKNF(truthTable));
        System.out.println("``````````````````````````````````");

        System.out.println("Индексная форма:");
        List<String> bitMasks = buildIndexForm(truthTable);
        StringBuilder binaryString = new StringBuilder();
        for (String bitmask : bitMasks) {
            binaryString.append(bitmask);
        }
        int decimal = Integer.parseInt(binaryString.toString(), 2);
        System.out.println(binaryString + " ---> " + decimal);
    }

    public static String convertToRPN(String expression) {
        StringBuilder result = new StringBuilder();
        Deque<Character> queue = new ArrayDeque<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                queue.push(ch);
            } else if (ch == ')') {
                while (!queue.isEmpty() && queue.peek() != '(') {
                    result.append(queue.pop());
                }
                queue.pop();
            } else if (isOperator(ch)) {
                while (!queue.isEmpty() && precedence(ch) <= precedence(queue.peek())) {
                    result.append(queue.pop());
                }
                queue.push(ch);
            }
        }
        while (!queue.isEmpty()) {
            result.append(queue.pop());
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
        Map<Map<Character, Boolean>, Boolean> truthTable = new LinkedHashMap<>();

        List<Character> variables = extractVariables(expression);
        int n = variables.size();
        int combinations = 1 << n;

        for (int i = 0; i < combinations; i++) {
            Map<Character, Boolean> variableValues = getCharacterBooleanMap(i, n, variables);
            boolean result = evaluateExpression(expression, variableValues);
            truthTable.put(variableValues, result);
        }

        return truthTable;
    }

    private static Map<Character, Boolean> getCharacterBooleanMap(int i, int n, List<Character> variables) {
        Map<Character, Boolean> variableValues = new HashMap<>();
        StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(i));
        while (binaryString.length() < n) {
            binaryString.insert(0, "0");
        }
        for (int j = 0; j < n; j++) {
            char variable = variables.get(j);
            boolean value = binaryString.charAt(j) == '1';
            variableValues.put(variable, value);
        }
        return variableValues;
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
            case '~' -> operand1 == operand2;
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

    public static List<String> buildIndexForm(Map<Map<Character, Boolean>, Boolean> truthTable) {
        List<String> bitMasks = new ArrayList<>();
        for (boolean result : truthTable.values()) {
            bitMasks.add(result ? "1" : "0");
        }
        return bitMasks;
    }
}
