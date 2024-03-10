import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstructionOfSknfAndSdnfTest {
    @Test
    public void testConvertToRPN() {
        String expression = "((((P & (!Q)) - R) ~ P) | (P & Q))";
        String expectedRPN = "PQ!&R-P~PQ&|";
        assertEquals(expectedRPN, ConstructionOfSknfAndSdnf.convertToRPN(expression));
    }

    @Test
    public void testBuildTruthTable() {
        String expression = "(((P & Q) - R) ~ P)";
        String rpn = ConstructionOfSknfAndSdnf.convertToRPN(expression);
        Map<Map<Character, Boolean>, Boolean> expectedTruthTable = new LinkedHashMap<>();
        expectedTruthTable.put(Map.of('P', false, 'Q', false, 'R', false), false);
        expectedTruthTable.put(Map.of('P', false, 'Q', false, 'R', true), false);
        expectedTruthTable.put(Map.of('P', false, 'Q', true, 'R', false), false);
        expectedTruthTable.put(Map.of('P', false, 'Q', true, 'R', true), false);
        expectedTruthTable.put(Map.of('P', true, 'Q', false, 'R', false), true);
        expectedTruthTable.put(Map.of('P', true, 'Q', false, 'R', true), true);
        expectedTruthTable.put(Map.of('P', true, 'Q', true, 'R', false), false);
        expectedTruthTable.put(Map.of('P', true, 'Q', true, 'R', true), true);
        assertEquals(expectedTruthTable, ConstructionOfSknfAndSdnf.buildTruthTable(rpn));
    }

    @Test
    public void testBuildSKNF() {
        String expression = "((P - (Q & R)) ~ ((P & (!Q)) - (!R)))";
        String rpn = ConstructionOfSknfAndSdnf.convertToRPN(expression);
        Map<Map<Character, Boolean>, Boolean> truthTable = ConstructionOfSknfAndSdnf.buildTruthTable(rpn);
        assertEquals("!PQR & !P!QR", ConstructionOfSknfAndSdnf.buildSKNF(truthTable));
    }

    @Test
    public void testBuildSDNF() {
        String expression = "((P - (Q & R)) ~ ((P & (!Q)) - (!R)))";
        String rpn = ConstructionOfSknfAndSdnf.convertToRPN(expression);
        Map<Map<Character, Boolean>, Boolean> truthTable = ConstructionOfSknfAndSdnf.buildTruthTable(rpn);
        assertEquals("!P!Q!R | !P!QR | !PQ!R | !PQR | P!QR | PQR", ConstructionOfSknfAndSdnf.buildSDNF(truthTable));
    }

    @Test
    public void testNumericFormSDNF() {
        String expression = "((P - (Q & R)) ~ ((P & (!Q)) - (!R)))";
        String rpn = ConstructionOfSknfAndSdnf.convertToRPN(expression);
        Map<Map<Character, Boolean>, Boolean> truthTable = ConstructionOfSknfAndSdnf.buildTruthTable(rpn);
        assertEquals("(0, 1, 2, 3, 5, 7) ∨", ConstructionOfSknfAndSdnf.numericFormSDNF(truthTable));
    }

    @Test
    public void testNumericFormSKNF() {
        String expression = "((P - (Q & R)) ~ ((P & (!Q)) - (!R)))";
        String rpn = ConstructionOfSknfAndSdnf.convertToRPN(expression);
        Map<Map<Character, Boolean>, Boolean> truthTable = ConstructionOfSknfAndSdnf.buildTruthTable(rpn);
        assertEquals("(4, 6) ∧", ConstructionOfSknfAndSdnf.numericFormSKNF(truthTable));
    }

    @Test
    public void testBuildIndexForm() {
        String expression = "((P - (Q & R)) ~ ((P & (!Q)) - (!R)))";
        String rpn = ConstructionOfSknfAndSdnf.convertToRPN(expression);
        Map<Map<Character, Boolean>, Boolean> truthTable = ConstructionOfSknfAndSdnf.buildTruthTable(rpn);

        List<String> bitMasks = ConstructionOfSknfAndSdnf.buildIndexForm(truthTable);
        StringBuilder binaryString = new StringBuilder();
        for (String bitmask : bitMasks) {
            binaryString.append(bitmask);
        }
        int decimal = Integer.parseInt(binaryString.toString(), 2);

        assertEquals("11110101", binaryString.toString());
        assertEquals(245, decimal);
    }
}