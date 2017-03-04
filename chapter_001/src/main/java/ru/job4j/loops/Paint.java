package ru.job4j.loops;

/**
 * Paint class draw pyramid in pseudo graphics.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.03.17
 */
public class Paint {
    /**
     * Create string represents a pyramid.
     * @param height number of pyramid levels
     * @return string
     */
    public String piramid(int height) {
        StringBuilder stringBuilder = new StringBuilder();
        //base level width
        int width = height + (height - 1);
        for (int i = 1; i <= height; i++) {
            int margin = (width - i - (i - 1)) / 2;
            stringBuilder.append(getMargin(margin));
            stringBuilder.append(getPyramidLevel(i));
            if (i != height) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Helper method creates margin string.
     * @param length number of space characters
     * @return string
     */
    private String getMargin(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Helper method creates string represents pyramid level.
     * @param size number of "^" characters
     * @return string
     */
    private String getPyramidLevel(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            stringBuilder.append("^");
            if (i != size) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}