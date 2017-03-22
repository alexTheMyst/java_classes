package ru.job4j.inheritance;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Tests profession classes.
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.03.17
 */
public class ProfessionTest {

    /**
     * Tests Profession class existence.
     */
    @Test
    public void testProfessionExists() {
        final Profession profession = new Profession();
        assertThat(profession, notNullValue());
    }

    /**
     * Tests Doctor class exists.
     */
    @Test
    public void testDoctorExistence() {
        final Doctor doctor = new Doctor();
        assertThat(doctor, notNullValue());
    }

    /**
     * Tests Engineer class existence.
     */
    @Test
    public void testEgnineerExistence() {
        final Engineer engineer = new Engineer();
        assertThat(engineer, notNullValue());
    }

    /**
     * Tests Teacher class existence.
     */
    @Test
    public void testTescherExistence() {
        final Teacher teacher = new Teacher();
        assertThat(teacher, notNullValue());
    }

    /**
     * Tests yearsOfExperience property of Profession class.
     */
    @Test
    public void testProfessionYearsOfExperienceProperyAcessibility() {
        final Profession profession = new Profession();
        final int testYearsValue = 3;
        profession.setYearsOfExperience(testYearsValue);
        assertThat(profession.getYearsOfExperience(), is(testYearsValue));
    }

    /**
     * Tests degree property of Profession class.
     */
    @Test
    public void testProfessionDegreeProperyAccessebility() {
        final Profession profession = new Profession();
        final String testDegreeName = "Masters";
        profession.setDegree(testDegreeName);
        assertThat(profession.getDegree(), is(testDegreeName));
    }

    /**
     * Tests jobTitle property of Profession class.
     */
    @Test
    public void testProfessionJobTitlePropertyAccesibility() {
        final Profession profession = new Profession();
        final String testJobTitle = "General Practice Doctor";
        profession.setJobTitle(testJobTitle);
        assertThat(profession.getJobTitle(), is(testJobTitle));
    }

    /**
     * Tests doctor diagnose method.
     */
    @Test
    public void testDoctorDiagnoge() {
        final Doctor doctor = new Doctor();
        final Engineer engineer = new Engineer();
        final String diagnoseString = "Patient is healthy.";
        assertThat(doctor.diagnose(engineer), is(diagnoseString));
    }

    /**
     * Tests doctor checkBodyTemperature method.
     */
    @Test
    public void testDoctorCheckBodyTemperatureMethod() {
        final Doctor doctor = new Doctor();
        final double testTemperature = 36.6d;
        assertThat(doctor.checkBodyTemperature(), closeTo(testTemperature, 0.1));
    }

    /**
     * Test teacher makeAnAssesment method.
     */
    @Test
    public void testTeacherMakeAnAssesmentMathod() {
        final Teacher teacher = new Teacher();
        final int testMark = 5;
        assertThat(teacher.makeAnAssesment(), is(testMark));
    }
}
