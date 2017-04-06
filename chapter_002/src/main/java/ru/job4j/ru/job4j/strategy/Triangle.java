package ru.job4j.ru.job4j.strategy;

/**
 * Triangle representation.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.04.17
 */
public class Triangle implements Shape {
    /**
     * Depicts triangle in pseudographic.
     * @return string with triangle-like shape
     */
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ^\n");
        stringBuilder.append(" /  \\\n");
        stringBuilder.append("------");
        return stringBuilder.toString();
    }
}
