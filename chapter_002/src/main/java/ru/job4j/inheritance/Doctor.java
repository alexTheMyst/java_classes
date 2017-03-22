package ru.job4j.inheritance;

/**
 * Doctor specific properties and behavior.
 * @author Alexey
 * @version $id$
 * @since 21.03.17
 */
public class Doctor extends Profession {

    /**
     * Default constructor.
     */
    public Doctor() {
        super();
    }

    /**
     * Check Engineer heath.
     * @param engineer accepts Engineer instance as a patient
     * @return diagnose string
     */
    public String diagnose(Engineer engineer) {
        return "Patient is healthy.";
    }

    /**
     * Normal body temperature.
     * @return 36.6
     */
    public double checkBodyTemperature() {
        return 36.6;
    }

    /**
     * Heal method.
     * @param patient accept an Engineer instance
     */
    public void heal(Engineer patient) {
        System.out.println("Healing a patient with job title: " + patient.getJobTitle());
    }
}
