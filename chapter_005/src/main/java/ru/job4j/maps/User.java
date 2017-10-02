package ru.job4j.maps;

import java.util.Calendar;

/**
 * User representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 02.10.17
 */
public class User {

    /**
     * Users name.
     */
    private String name;

    /**
     * Users children number.
     */
    private int children;

    /**
     * Users birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     *
     * @param name     users name
     * @param children children quantity
     * @param birthday users birthday
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Overridden method.
     *
     * @return string representation of user.
     */
    @Override
    public String toString() {
        return "User {"
                + "name='"
                + name
                + '\''
                + ", children="
                + children
                + ", birthday="
                + birthday.getTime().toString()
                + '}';
    }
}
