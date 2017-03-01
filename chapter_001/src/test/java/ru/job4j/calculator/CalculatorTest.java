package ru.job4j.calculator;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Test simple calculator class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.03.17.
 */
public class CalculatorTest {
    /**
     * Calculator variable.
     */
    private Calculator calculator = new Calculator();

    /**
     * Test Calculator class exists.
     */
    @Test
    public void testCalculatorClassExistance() {
        assertNotNull(calculator);
    }

    /**
     * Test default result return.
     */
    @Test
    public void testGetResultMethodReturnDefaultValue() {
        final double defaultResult = 0d;
        assertEquals(defaultResult, calculator.getResult(), 0.01);
    }

    /**
     * Test add method.
     */
    @Test
    public void testAdd() {
        final double addResult = 2d;
        calculator.add(1d, 1d);
        assertEquals(addResult, calculator.getResult(), 0.01);
    }

    /**
     * Test substruct method.
     */
    @Test
    public void testSubstruct() {
        final double substructResult = 1d;
        calculator.substruct(2d, 1d);
        assertEquals(substructResult, calculator.getResult(), 0.01);
    }

    /**
     * Test div method.
     */
    @Test
    public void testDiv() {
        final double divResult = 1d;
        calculator.div(1, 1);
        assertEquals(divResult, calculator.getResult(), 0.01);
    }

    /**
     * Test multiple method.
     */
    @Test
    public void testMultiple() {
        final double multipleResult = 4d;
        calculator.multiple(2, 2);
        assertEquals(multipleResult, calculator.getResult(), 0.01);
    }
}
