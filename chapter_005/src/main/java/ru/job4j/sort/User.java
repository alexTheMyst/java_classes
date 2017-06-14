package ru.job4j.sort;

/**
 * User representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 13.06.17
 */
public class User implements Comparable<User> {

    /**
     * Users name.
     */
    private final String name;

    /**
     * Users age.
     */
    private final Integer age;

    /**
     * Constructor.
     *
     * @param name some name
     * @param age  some age
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Name getter.
     *
     * @return name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Age getter.
     *
     * @return age as int
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Compares user with given user by age.
     *
     * @param o any user instance
     * @return 0 if age equals, 1 if current user age greater than given users age,
     * -1 if current user age less than given users age
     */
    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }


    /**
     * Represent user as a string.
     *
     * @return string with users name and users age
     */
    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.age);
    }

    /**
     * Overridden equals method.
     *
     * @param o some object to compare.
     * @return true if objects are equal
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

        return age.equals(user.age);
    }

    /**
     * Overridden hashCode method.
     *
     * @return int as hash code
     */
    @Override
    public int hashCode() {
        return age.hashCode();
    }
}
