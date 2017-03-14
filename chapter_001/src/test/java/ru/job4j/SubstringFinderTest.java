package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SubstringFinder.
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.03.17
 */
public class SubstringFinderTest {
    /**
     * Test SubstringFinder exists.
     */
    @Test
    public void classExists() {
        final SubstringFinder substringFinder = new SubstringFinder();
    }

    /**
     * Tests find positive.
     */
    @Test
    public void findMethodTestPositive() {
        final String stringForSearch = "some long string where we will search";
        final String substring = "will";
        final SubstringFinder substringFinder = new SubstringFinder();
        assertThat(substringFinder.contains(stringForSearch, substring), is(true));
    }

    /**
     * Test find negative.
     */
    @Test
    public void findMethodTestNegative() {
        final String stringForSearch = "some long string where we will search";
        final String substring = "with";
        final SubstringFinder substringFinder = new SubstringFinder();
        assertThat(substringFinder.contains(stringForSearch, substring), is(false));
    }
}
