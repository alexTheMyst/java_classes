package ru.job4j.visitors;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Contains logic to count visitors.
 * @author Alexey Aleshin
 * @version $id$
 * @since 20.05.17
 */
public class VisitorCounter {

    /**
     * Vsitors counter.
     */
    private int maxVisitors;

    /**
     * Start and end time of period with maximum visitors.
     */
    private LocalTime maxPeriodStart, maxPeriodEnd;

    /**
     * Index for visitors array.
     */
    private int visitIntex = 0;

    /**
     * Contains visits variables.
     */
    private Visit[] visits = new Visit[10];

    /**
     * Adds a visit to visits array.
     * @param visit any visit
     */
    public void add(Visit visit) {
        if (visitIntex < visits.length) {
            this.visits[visitIntex++] = visit;
        } else {
            this.visits = Arrays.copyOf(visits, visits.length * 2);
            this.visits[visitIntex++] = visit;
        }
    }

    /**
     * Calculates visitors per time period.
     */
    public void calculate() {
        visits = Arrays.copyOf(visits, visitIntex);
        Arrays.sort(visits, 0, visits.length);
        int visitorsCounter = 0;
        for (Visit visit : visits) {
            if (visit.isStart())  {
                visitorsCounter++;
                if (visitorsCounter > maxVisitors) {
                    this.maxVisitors = visitorsCounter;
                    this.maxPeriodStart = visit.getTime();
                }
            } else {
                if (visitorsCounter == maxVisitors) {
                    visitorsCounter--;
                    this.maxPeriodEnd = visit.getTime();
                } else {
                    visitorsCounter--;
                }
            }
        }
    }

    /**
     * Getter  for maxVisitors.
     * @return int
     */
    public int getMaxCounter() {
        return this.maxVisitors;
    }

    /**
     * Getter for period with max visitors in the office.
     * @return period
     */
    public Period getMaxVisitorsPeriod() {
        return new Period(maxPeriodStart, maxPeriodEnd);
    }

}
