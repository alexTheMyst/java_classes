package ru.job4j.aquarium;

/**
 * Pair of fish.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class FishPair {
    /**
     * Fish one.
     */
    private Fish fishOne;

    /**
     * Fish two.
     */
    private Fish fishTwo;


    /**
     * Fish getter.
     *
     * @return first fish
     */
    public Fish getFishOne() {
        return fishOne;
    }

    /**
     * Fish setter.
     *
     * @param fishOne a fish
     */
    public void setFishOne(Fish fishOne) {
        this.fishOne = fishOne;
    }

    /**
     * Fish getter.
     *
     * @return second fish
     */
    public Fish getFishTwo() {
        return fishTwo;
    }

    /**
     * Fish setter.
     *
     * @param fishTwo a fish
     */
    public void setFishTwo(Fish fishTwo) {
        this.fishTwo = fishTwo;
    }
}
