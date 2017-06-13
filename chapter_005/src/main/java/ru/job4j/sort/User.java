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
    private final int age;

    /**
     * Constructor.
     *
     * @param name some name
     * @param age  some age
     */
    public User(String name, int age) {
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
    public int getAge() {
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
        int result;
        if (this.age == o.age) {
            result = 0;
        } else if (this.age < o.age) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
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
}
