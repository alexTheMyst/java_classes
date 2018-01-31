package ru.job4j.mt.jcip;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParallelSearchTest {

    private ParallelSearch parallelSearch;

    @Before
    public void setUp() {
        this.parallelSearch = new ParallelSearch("../", "class", Arrays.asList("java"));
    }

    @Test
    public void startSearch() throws IOException, InterruptedException {

        this.parallelSearch.getResult().stream().forEach(System.out::println);

        assertThat(this.parallelSearch.getResult().size(), greaterThan(0));
    }
}