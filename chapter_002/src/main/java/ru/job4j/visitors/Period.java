package ru.job4j.visitors;

import java.time.LocalTime;

/**
 * Represent period of time.
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.05.17
 */
public class Period {
    /**
     * Period start and end.
     */
    private LocalTime start, end;

    /**
     * Constructor.
     * @param start start time
     * @param end end time
     */
    public Period(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Start time getter.
     * @return start time
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * End time getter.
     * @return end time
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Overriden  equals.
     * @param o any object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Period period = (Period) o;

        if (start != null ? !start.equals(period.start) : period.start != null) {
            return false;
        }
        return end != null ? end.equals(period.end) : period.end == null;
    }

    /**
     * Overriden hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
