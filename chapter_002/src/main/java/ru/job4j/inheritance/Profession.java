package ru.job4j.inheritance;

/**
 * Base class for professions.
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.03.17
 */
public class Profession {
    /**
     * Years of experience in profession.
     */
    private int yearsOfExperience;

    /**
     * Degree as a string.
     */
    private String degree;

    /**
     * Current job title as a string.
     */
    private String jobTitle;

    /**
     * Mutator method for yearOfExperience property.
     * @param yearsOfExperience int
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Getter for yearsOfExperience property.
     * @return int value
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Mutator method for yearOfExperience property.
     * @param degree String
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * Getter for yearsOfExperience property.
     * @return String value
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Mutator method for jobTitle property.
     * @param jobTitle String
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Getter for jobTitle property.
     * @return String
     */
    public String getJobTitle() {
        return jobTitle;
    }
}
