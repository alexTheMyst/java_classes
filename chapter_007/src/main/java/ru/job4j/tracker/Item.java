package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Item represents one application item.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.03.17
 */
public class Item {
    /**
     * Id of the item.
     */
    private String id;

    /**
     * Name of the item.
     */
    private String name;

    /**
     * Item description.
     */
    private String description;

    /**
     * Creation date as long.
     */
    private long creationDate;

    /**
     * Item comments.
     */
    private String[] comments;

    /**
     * Constructor.
     * @param name accept item name
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Name getter.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Name setter.
     * @param name item name as a string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Id getter.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Id setter.
     * @param id item id sa a stirng
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", comments=" + Arrays.toString(comments) +
                '}';
    }
}
