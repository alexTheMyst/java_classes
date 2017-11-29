package ru.job4j.aquarium;

/**
 * Simple fish representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class Fish implements Comparable<Fish> {
    /**
     * Used to generate id for next fish.
     */
    private static int idGen = 1;

    /**
     * Id.
     */
    private final int id;

    /**
     * fish life period.
     */
    private final int lifePeriod;

    /**
     * Fish gender.
     */
    private final boolean male;

    /**
     * Constructor.
     *
     * @param lifePeriod life period
     * @param male gender as a boolean
     */
    public Fish(int lifePeriod, boolean male) {
        this.id = idGen++;
        System.out.println(String.format("Fish #%d was born.", this.id));
        this.lifePeriod = lifePeriod;
        this.male = male;
    }

    /**
     * Checks that action can happen and print message.
     *
     * @param fish some fish
     * @return true if fish can meet or false otherwise
     */
    public boolean checkMeet(Fish fish) {
        boolean result = this.male == fish.male;
        if (result) {
            System.out.println(String.format("Fish #%d meet fish #%d", this.id, fish.id));
        }
        return result;
    }

    /**
     * Cgecks that action can happend
     *
     * @param fish some fish
     * @return true if fish can born the other fish or false otherwise
     */
    public boolean checkBorn(Fish fish) {
        return this.male != fish.male;
    }

    /**
     * Checks is the fish should die.
     *
     * @param timeCounter time period since aquarium started
     * @return true if it is time to die or false otherwise
     */
    public boolean checkDieTime(int timeCounter) {
        boolean result = timeCounter > this.lifePeriod;
        if (result) {
            System.out.println(String.format("Fish #%d died.", this.id));
        }
        return result;
    }

    @Override
    public int compareTo(Fish fish) {
        return this.lifePeriod - fish.lifePeriod;
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

        return lifePeriod == fish.lifePeriod && male == fish.male;
    }

    @Override
    public int hashCode() {
        int result = lifePeriod;
        result = 31 * result + (male ? 1 : 0);
        return result;
    }
}