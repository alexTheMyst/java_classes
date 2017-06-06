package ru.job4j.transform;

/**
 * User entity.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 06.06.17
 */
public class User {
    /**
     * User id.
     */
    private final Integer id;

    /**
     * Users name and city properties.
     */
    private final String name;
    private final String city;

    /**
     * Constructor.
     *
     * @param id   user id
     * @param name user name
     * @param city user city
     */
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Id getter.
     *
     * @return id as Integer
     */
    public Integer getId() {
        return id;
    }
}
