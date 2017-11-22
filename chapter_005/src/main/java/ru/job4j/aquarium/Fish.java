package ru.job4j.aquarium;

/**
 * Simple fish representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class Fish implements Comparable {
    /**
     * Used to generate id for next fish.
     */
    public static int idGen = 1;

    /**
     * Id.
     */
    private int id;

    /**
     * fish life period.
     */
    private int lifePeriod;

    /**
     * Fish gender.
     */
    private boolean male;

    /**
     * Constructor.
     *
     * @param lifePeriod life period
     * @param male gender as a boolean
     */
    public Fish(int lifePeriod, boolean male) {
        this.id = idGen++;
        this.lifePeriod = lifePeriod;
        this.male = male;
    }

    /**
     * Life period getter.
     *
     * @return life period
     */
    public int getLifePeriod() {
        return lifePeriod;
    }

    /**
     * Id getter.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Gender getter.
     *
     * @return gender as a boolean
     */
    public boolean isMale() {
        return male;
    }

    @Override
    public int compareTo(Object o) {
        return this.lifePeriod - ((Fish) o).getLifePeriod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Fish fish = (Fish) o;

        if (lifePeriod != fish.lifePeriod) {
            return false;
        }
        return male == fish.male;
    }

    @Override
    public int hashCode() {
        int result = lifePeriod;
        result = 31 * result + (male ? 1 : 0);
        return result;
    }
}
