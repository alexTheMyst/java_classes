package ru.job4j.tracker;

/**
 * Item represents one application item.
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
     * Id getter.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Id setter.
     * @param id item id sa a string
     */
    public void setId(String id) {
        this.id = id;
    }


}
