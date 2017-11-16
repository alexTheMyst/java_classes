package ru.job4j.additional;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains methods for compare two strings.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 16.11.17
 */
public class StringComparator {
    /**
     * String one.
     */
    private final String stringOne;

    /**
     * String two.
     */
    private final String stringTwo;

    /**
     * Constructor.
     *
     * @param one some string
     * @param two some string
     */
    public StringComparator(String one, String two) {
        this.stringOne = one;
        this.stringTwo = two;
    }

    /**
     * Compares strings.
     *
     * @return true if strings have the same letters set or false otherwise.
     */
    public boolean compare() {
        boolean result = false;
        if (stringOne.length() == stringTwo.length()) {
            Set<Character> setOne = new HashSet<>();
            Set<Character> setTwo = new HashSet<>();
            for (int i = 0; i < stringOne.length() - 1; i++) {
                setOne.add(stringOne.charAt(i));
                setTwo.add(stringTwo.charAt(i));
            }
            result = setOne.equals(setTwo);
        }
        return result;
    }
}
