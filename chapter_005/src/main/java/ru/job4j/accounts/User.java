package ru.job4j.accounts;

/**
 * User representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.07.17
 */
public class User {
    /**
     * Users name.
     */
    private final String name;

    /**
     * Users ID number.
     */
    private final String idNumber;

    /**
     * Users class constructor.
     *
     * @param name     some name
     * @param idNumber some id
     */
    public User(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    /**
     * Compares current user with the given one.
     *
     * @param o some user
     * @return true if current user has the same attributes as a given one
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

        if (!name.equals(user.name)) {
            return false;
        }
        return idNumber.equals(user.idNumber);
    }

    /**
     * Generates hash code.
     *
     * @return integer in range 1 - 31
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + idNumber.hashCode();
        return result;
    }
}
