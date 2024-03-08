package test;

import app.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTest {

    @Test
    void directToInteger() {
        int numberForConvert = -16;
        Converter converter = new Converter();
        String directBinary = converter.integerToDirect(numberForConvert);
        int defaultNumber = converter.directToInteger(directBinary);
        assertEquals(defaultNumber, numberForConvert);
    }

    @Test
    void integerToDirect() {
        int numberForConvert = -16;
        Converter converter = new Converter();
        String directBinary = converter.integerToDirect(numberForConvert);
        assertEquals(directBinary, "110000");
    }

    @Test
    void directToReverse() {
        int numberForConvert = -16;
        Converter converter = new Converter();
        String directBinary = converter.integerToDirect(numberForConvert);
        String reverseBinary = converter.directToReverse(directBinary);
        assertEquals(reverseBinary, "101111");

    }

    @Test
    void directToAdditional() {
        int numberForConvert = -16;
        Converter converter = new Converter();
        String directBinary = converter.integerToDirect(numberForConvert);
        String additionalBinary = converter.directToAdditional(directBinary);
        assertEquals(additionalBinary, "110000");
    }

    @Test
    void floatToBinary() {
        Converter converter = new Converter();
        float floatNumber = 1024.444F;
        String floatBinary = converter.floatToBinary(floatNumber);
        assertEquals(floatBinary, "010000000000.0111000110101");
    }

    @Test
    void floatToFloatingPointNumber() {
        Converter converter = new Converter();
        float floatNumber = 1024.444F;
        String floatingPointNumber = converter.floatToFloatingPointNumber(floatNumber);
        assertEquals(floatingPointNumber, "01000100100000000000111000110101");
    }
}