package ru.job4j.ru.job4j.strategy;

/**
 * Square representation.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.04.17
 */
public class Square implements Shape {
    /**
     * Depicts square in pseudographic.
     * @return string with square-like shape
     */
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+---+\n");
        stringBuilder.append("|   |\n");
        stringBuilder.append("+---+");
        return stringBuilder.toString();
    }
}
