package ru.job4j.visitors;

import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for visitor counter.
 * @author Alexey Aleshin
 * @version $id$
 * @since 20.05.17
 */
public class CounterTest {
    /**
     * Tests VisitorCounter existence.
     */
    @Test
    public void testMaxVisitorCounter() {
        VisitorCounter visitorCounter = new VisitorCounter();
        assertThat(visitorCounter, notNullValue());
    }

    /**
     * Tests that when add visit start and visit end counter is equals to one.
     */
    @Test
    public void testAddVisitReturnOneAsCounter() {
        VisitorCounter visitorCounter = new VisitorCounter();
        Visit visitStart = new Visit(LocalTime.of(10, 00), true);
        Visit visitEnd = new Visit(LocalTime.of(13, 00), false);
        visitorCounter.add(visitStart);
        visitorCounter.add(visitEnd);
        visitorCounter.calculate();
        assertThat(visitorCounter.getMaxCounter(), is(1));
    }

    /**
     * Tests that when add visit start and visit end start period date equals visit start.
     */
    @Test
    public void testAddVisitReturnVisitStart() {
        VisitorCounter visitorCounter = new VisitorCounter();
        Visit visitStart = new Visit(LocalTime.of(10, 00), true);
        Visit visitEnd = new Visit(LocalTime.of(13, 00), false);
        visitorCounter.add(visitStart);
        visitorCounter.add(visitEnd);
        visitorCounter.calculate();
        assertThat(visitorCounter.getMaxVisitorsPeriod().getStart(), is(visitStart.getTime()));
    }

    /**
     * Tests that when add visit start and visit end start period date equals visit end.
     */
    @Test
    public void testAddVisitReturnVisitEnd() {
        VisitorCounter visitorCounter = new VisitorCounter();
        Visit visitStart = new Visit(LocalTime.of(11, 00), true);
        Visit visitEnd = new Visit(LocalTime.of(13, 00), false);
        visitorCounter.add(visitStart);
        visitorCounter.add(visitEnd);
        visitorCounter.calculate();
        assertThat(visitorCounter.getMaxVisitorsPeriod().getEnd(), is(visitEnd.getTime()));
    }

    /**
     * Tests that when add three visits and max visits counter is equals to two.
     */
    @Test
    public void testAddVisitsGetRightCounter() {
        VisitorCounter visitorCounter = new VisitorCounter();
        Visit visitOneStart = new Visit(LocalTime.of(10, 00), true);
        visitorCounter.add(visitOneStart);
        Visit visitOneEnd = new Visit(LocalTime.of(13, 00), false);
        visitorCounter.add(visitOneEnd);
        Visit visitTwoStart = new Visit(LocalTime.of(10, 30), true);
        visitorCounter.add(visitTwoStart);
        Visit visitTwoEnd = new Visit(LocalTime.of(12, 45), false);
        visitorCounter.add(visitTwoEnd);
        Visit visitThreeStart = new Visit(LocalTime.of(13, 50), true);
        visitorCounter.add(visitThreeStart);
        Visit visitThreeEnd = new Visit(LocalTime.of(14, 55), false);
        visitorCounter.add(visitThreeEnd);
        visitorCounter.calculate();
        assertThat(visitorCounter.getMaxCounter(), is(2));
    }

    /**
     * Tests that VisitorCounter returns right period.
     */
    @Test
    public void testAddVisitsGetPeriod() {
        VisitorCounter visitorCounter = new VisitorCounter();
        Visit visitOneStart = new Visit(LocalTime.of(10, 00), true);
        visitorCounter.add(visitOneStart);
        Visit visitOneEnd = new Visit(LocalTime.of(13, 00), false);
        visitorCounter.add(visitOneEnd);
        Visit visitTwoStart = new Visit(LocalTime.of(10, 30), true);
        visitorCounter.add(visitTwoStart);
        Visit visitTwoEnd = new Visit(LocalTime.of(12, 45), false);
        visitorCounter.add(visitTwoEnd);
        Visit visitThreeStart = new Visit(LocalTime.of(13, 50), true);
        visitorCounter.add(visitThreeStart);
        Visit visitThreeEnd = new Visit(LocalTime.of(14, 55), false);
        visitorCounter.add(visitThreeEnd);
        visitorCounter.calculate();
        Period testPeriod = new Period(LocalTime.of(10, 30), LocalTime.of(12, 45));
        assertThat(visitorCounter.getMaxVisitorsPeriod(), is(testPeriod));
    }
}
