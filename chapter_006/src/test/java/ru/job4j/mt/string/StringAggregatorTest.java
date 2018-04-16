package ru.job4j.mt.string;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for StringAgregator.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.04.2018
 */
public class StringAggregatorTest {
    /**
     * StringAggregator instance.
     */
    private NumberAggregator numberAggregator;

    /**
     * Sets up instance before each test.
     */
    @Before
    public void setUp() {
        this.numberAggregator = new NumberAggregator();
    }

    /**
     * Adds zero and checks that string is zero.
     */
    @Test
    public void whenAddZeroThenStringEqualsZero() {
        this.numberAggregator.addInt(0);

        assertThat(this.numberAggregator.getCurrentString(), is("0"));
    }

    /**
     * Uses zeros generator and checks that string contains zero.
     *
     * @throws InterruptedException exception could be thrown by sleep
     */
    @Test
    public void whenRunWithZerosGeneratorThenContainsZeros() throws InterruptedException {
        NumberGenerator numberGenerator = new NumberGenerator(0, 100, this.numberAggregator);
        new Thread(numberGenerator).start();

        Thread.sleep(200);

        assertThat(this.numberAggregator.getCurrentString(), containsString("0"));
    }

    /**
     * Uses two generators and checks that string contains both figures.
     *
     * @throws InterruptedException exception could be thrown by sleep
     */
    @Test
    public void whenRunWithTwoGeneratorsThenContainsStringsFromBoth() throws InterruptedException {
        NumberGenerator numberGeneratorZeros = new NumberGenerator(0, 10, this.numberAggregator);
        NumberGenerator numberGeneratorOnes = new NumberGenerator(1, 10, this.numberAggregator);
        Thread threadZeros = new Thread(numberGeneratorZeros);
        Thread threadOnes = new Thread(numberGeneratorOnes);

        threadZeros.start();
        threadOnes.start();
        Thread.sleep(500);

        assertThat(this.numberAggregator.getCurrentString(), allOf(containsString("0"), containsString("1")));
    }

    /**
     * Uses two generators and checks that string has particular pattern.
     *
     * @throws InterruptedException exception could be thrown by sleep
     */
    @Test
    public void whenRunWithTwoGeneratorsThenContainsStrings() throws InterruptedException {
        NumberGenerator numberGeneratorZeros = new NumberGenerator(0, 10, this.numberAggregator);
        NumberGenerator numberGeneratorOnes = new NumberGenerator(1, 10, this.numberAggregator);
        Thread threadZeros = new Thread(numberGeneratorZeros);
        Thread threadOnes = new Thread(numberGeneratorOnes);

        threadZeros.start();
        threadOnes.start();
        Thread.sleep(1000);

        System.out.println(this.numberAggregator.getCurrentString());

        assertThat(this.numberAggregator.getCurrentString(), containsString("00000000001111111111"));
    }
}