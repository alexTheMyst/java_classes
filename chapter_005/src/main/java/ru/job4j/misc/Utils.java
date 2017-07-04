package ru.job4j.misc;

import java.util.Iterator;
import java.util.List;

/**
 * Contains methods which solve.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.07.17
 */
public class Utils {

    /**
     * Searches an element in linked list on given position from the end of the list.
     *
     * @param list            some linked list
     * @param positionFromEnd position
     * @return an element
     */
    static Integer getElementFromList(List<Integer> list, int positionFromEnd) {
        Iterator<Integer> firstIterator = list.iterator();
        Iterator<Integer> secondIterator = null;
        int counter = 0;
        Integer result = null;
        while (firstIterator.hasNext()) {
            firstIterator.next();
            counter++;
            if (counter == positionFromEnd) {
                secondIterator = list.iterator();
            }
            if (counter >= positionFromEnd) {
                result = secondIterator.next();
            }
        }
        return result;
    }
}