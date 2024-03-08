package test;

import app.Operation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationTest {

    @Test
    void additionalSum() {
        Operation operation = new Operation();
        int firstAdditionalSum = 155;
        int secondAdditionalSum = 14;
        String sumResult = operation.additionalSum(firstAdditionalSum, secondAdditionalSum);
        assertEquals(sumResult, "0010101001");
    }

    @Test
    void floatingPointSum() {
        Operation operation = new Operation();
        float first = 128.70F;
        float second = 129.44F;
        String resultFloatingPointSum = operation.floatingPointSum(first, second);
        assertEquals(resultFloatingPointSum, "01000011100000010001000111101011");
    }

    @Test
    void directMultiplication() {
        Operation operation = new Operation();
        int firstNum = 14;
        int secondNum = 15;
        String resultMultiplication = operation.directMultiplication(firstNum, secondNum);
        assertEquals(resultMultiplication, "011010010");
    }

    @Test
    void subtractBinary() {
        Operation operation = new Operation();
        int firstAdditionalSum = 100;
        int secondAdditionalSum = -25;
        String subtractResult = operation.additionalSum(firstAdditionalSum, secondAdditionalSum);
        assertEquals(subtractResult, "01001011");
    }

    @Test
    void divide() {
        Operation operation = new Operation();
        int dividend = 25;
        int divisor = 15;
        String divideResult = operation.divide(dividend, divisor);
        assertEquals(divideResult, "000001.10101");
    }
}