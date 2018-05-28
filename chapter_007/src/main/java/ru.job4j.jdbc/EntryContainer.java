package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents container for JAXB.
 *
 * @author alexey
 * @version 25.05.18
 */
@XmlRootElement(name = "entries")
public class EntryContainer {
    /**
     * Entry collection.
     */
    @XmlElement(name = "entry")
    private List<Entry> storage;

    /**
     * Constructor.
     */
    public EntryContainer() {
        this.storage = new ArrayList<>();
    }

    /**
     * Entry setter.
     *
     * @param entry an entry
     */
    public void setEntry(int entry) {
        this.storage.add(new Entry(entry));
    }

    /**
     * Entry representation for JAXB.
     */
    static class Entry {
        /**
         * Data store.
         */
        @XmlElement
        private int field;

        /**
         * Constructor.
         *
         * @param field entry data
         */
        public Entry(int field) {
            this.field = field;
        }
    }
}