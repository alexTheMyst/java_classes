package ru.job4j.max;

/**
 * @author Alexey Aleshin
 * @version $id$
 * @since 02.03.17.
 */
public class Max {

    /**
     * Function return greatest parameter.
     * @param first First parameter
     * @param second Second parameter
     * @return greatest value
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }

    /**
     * Function return greatest parameter with three numbers.
     * @param first parameter
     * @param second parameter
     * @param third parameter
     * @return greatest value
     */
    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}
