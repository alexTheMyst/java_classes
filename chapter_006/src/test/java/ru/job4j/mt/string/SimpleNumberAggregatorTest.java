package ru.job4j.mt.string;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * SimpleNumberAggregator test.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 28.04.2018
 */
public class SimpleNumberAggregatorTest {

    /**
     * Uses two generators and checks that string has particular pattern.
     *
     * @throws InterruptedException exception could be thrown by sleep
     */
    @Test
    public void whenRunWithTwoGeneratorsThenContainsStrings() throws InterruptedException {
        SimpleNumberAggregator aggregator = new SimpleNumberAggregator(10);
        NumberGenerator numberGeneratorZeros = new NumberGenerator(0, 10, aggregator);
        NumberGenerator numberGeneratorOnes = new NumberGenerator(1, 10, aggregator);
        Thread threadZeros = new Thread(numberGeneratorZeros);
        Thread threadOnes = new Thread(numberGeneratorOnes);

        threadZeros.start();
        threadOnes.start();
        Thread.sleep(1000);

        System.out.println(aggregator.getCurrentString());

        assertThat(aggregator.getCurrentString(), containsString("00000000001111111111"));
    }

}