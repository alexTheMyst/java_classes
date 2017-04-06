package ru.job4j.ru.job4j.strategy;

/**
 * Prints different shapes.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.04.17
 */
public class Paint {
    /**
     * Shape field.
     */
    private Shape shape;

    /**
     * Constructor method.
     */
    public Paint() {
    }

    /**
     * Shape setter.
     * @param shape any implementation of shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Creates a shape.
     * @return shpe string representation
     */
    public String draw() {
        return this.shape.pic();
    }
}
