package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents object for JAXB.
 *
 * @author alexey
 * @version 25.05.18
 */
@XmlRootElement(name = "result")
public class SumResult {
    /**
     * Result store.
     */
    private long result;

    /**
     * Result setter.
     *
     * @param result as an int
     */
    @XmlElement(name = "sum")
    public void setResult(long result) {
        this.result = result;
    }

    /**
     * Result getter.
     *
     * @return result
     */
    public long getResult() {
        return result;
    }
}
