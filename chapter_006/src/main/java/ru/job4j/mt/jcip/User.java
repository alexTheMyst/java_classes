package ru.job4j.mt.jcip;

import java.util.Objects;

/**
 * User implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.01.2018
 */
public class User {

    /**
     * Default id.
     */
    public static final int DEFAULT_ID = 0;

    /**
     * Default amount.
     */
    public static final int DEFAULT_AMOUNT = 0;

    /**
     * Id field.
     */
    private final int id;

    /**
     * Amount field.
     */
    private int amount;

    /**
     * Default constructor.
     */
    public User() {
        this(DEFAULT_ID, DEFAULT_AMOUNT);
    }

    /**
     * Constructor.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Getter.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter.
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Setter.
     *
     * @param amount some amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Overridden equals.
     *
     * @param o some object
     * @return true if given object is user and it has the same id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    /**
     * Overridden hashCode
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}