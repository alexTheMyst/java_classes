package ru.job4j;

/**
 * Check that given substring is a part of given string.
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.03.17
 */
public class SubstringFinder {
    /**
     * Search substring in string.
     * @param stringForSearch string
     * @param substring another string
     * @return true if another string found
     */
    public boolean contains(String stringForSearch, String substring) {
        boolean result = false;
        char[] stringForSearchArray = stringForSearch.toCharArray();
        char[] substringArray = substring.toCharArray();
        int sameCharCounter = 0;
        for (int stringIndex = 0; stringIndex < stringForSearchArray.length; stringIndex++) {
            if (stringForSearchArray[stringIndex] == substringArray[0]) {
                for (int substringIndex = 0; substringIndex < substringArray.length; substringIndex++) {
                    if (stringForSearchArray[stringIndex + substringIndex] != substringArray[substringIndex]) {
                        sameCharCounter = 0;
                        break;
                    } else {
                        sameCharCounter++;
                    }
                }
                if (sameCharCounter == substringArray.length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
