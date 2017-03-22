package ru.job4j.inheritance;

/**
 * Engineer specific properties and behavior.
 * @author Alexey
 * @version $id$
 * @since 21.03.17
 */
public class Engineer extends Profession {

    /**
     * Default constructor.
     */
    public Engineer() {
        super();
    }

    /**
     * Desing method.
     */
    public void design() {
        System.out.println("Engineer is thinking about great design.");
    }

    /**
     * Implement method.
     */
    public void implement() {
        System.out.println("Engineer is implementing some features.");
    }

    /**
     * FixAnIssue method.
     */
    public void fixAnIssue() {
        System.out.println("Something is broken. Lets fix it.");
    }
}
