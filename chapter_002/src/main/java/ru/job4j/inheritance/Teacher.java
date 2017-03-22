package ru.job4j.inheritance;

/**
 * Engineer specific properties and behavior.
 * @author Alexey
 * @version $id$
 * @since 21.03.17
 */
public class Teacher extends Profession {

    /**
     * Default constructor.
     */
    public Teacher() {
        super();
    }

    /**
     * Teach method.
     * @param student accept a doctor instance
     */
    public void teach(Doctor student) {
        System.out.println("I'm teaching a student with " + student.getYearsOfExperience() + " years of experience.");
    }

    /**
     * Make an assessment.
     * @return always return a good mark
     */
    public int makeAnAssesment() {
        return 5;
    }

    /**
     * give homework method.
     */
    public void giveHomeWork() {
        System.out.println("Your homework is NONE.");
    }
}
