package at.tectrain.cicd.app.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceTest {

    CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAdd() {
        int result = calculatorService.add(1, 2);
        assertEquals(3, result);
    }

    @Test
    void testSubtract() {
        int result = calculatorService.subtract(1, 2);
        assertEquals(-1, result);
    }

    @Test
    void testMultiply() {
        int result = calculatorService.multiply(1, 2);
        assertEquals(2, result);
    }

    @Test
    void testDivide() {
        int result = calculatorService.divide(1, 2);
        assertEquals(0, result);
    }

}
