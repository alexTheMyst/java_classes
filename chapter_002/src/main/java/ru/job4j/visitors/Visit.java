package ru.job4j.visitors;

import java.time.LocalTime;

/**
 * Represents start or end visit event.
 * @author Alexey Aleshin
 * @version $id$
 * @since 20.05.17
 */
public class Visit implements Comparable<Visit> {

    /**
     * Time when event started.
     */
    private LocalTime time;

    /**
     * Flag.
     */
    private boolean isStart;

    /**
     * Constructor.
     * @param time event time
     * @param isStart flag shows start or end visit
     */
    public Visit(LocalTime time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }

    /**
     * Gets event time.
     * @return time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets  event flag.
     * @return boolean
     */
    public boolean isStart() {
        return isStart;
    }

    /**
     * Comparable implementation.
     * @param visit
     * @return int
     */
    @Override
    public int compareTo(Visit visit) {
        int result;
        if (this.time.equals(visit.time)) {
            result = 0;
        } else if (this.time.isBefore(visit.time)) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }
}
