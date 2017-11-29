package ru.job4j.aquarium;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Aquarium.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class Aquarium {
    /**
     * Artificial time counter.
     */
    public static int timeCounter = 0;

    /**
     * Aquarium population.
     */
    private final Queue<Fish> population;

    /**
     * Random generator.
     */
    private final Random random = new Random();

    /**
     * Constructor.
     */
    public Aquarium() {
        this.population = new PriorityQueue<>();
    }

    /**
     * Population getter.
     *
     * @return population
     */
    public Queue<Fish> getPopulation() {
        return population;
    }

    /**
     * Adds a fish to aquirium.
     *
     * @param fish a fish
     */
    public void add(Fish fish) {
        this.population.add(fish);
    }

    /**
     * Chose a pair of fish.
     *
     * @return a pair of fish
     */
    public FishPair getFishPair() {
        FishPair fishPair = new FishPair();
        Iterator<Fish> populationIterator = this.population.iterator();
        if (this.population.size() == 1) {
            Fish fish = populationIterator.next();
            fishPair.setFishOne(fish);
            fishPair.setFishTwo(fish);
        } else if (this.population.size() == 2) {
            fishPair.setFishOne(populationIterator.next());
            fishPair.setFishTwo(populationIterator.next());
        } else if (this.population.size() != 0) {
            getRandomFishPair(fishPair, populationIterator);
        }
        return fishPair;
    }

    /**
     * Random generator getter.
     *
     * @return random generator
     */
    public Random getRandom() {
        return this.random;
    }

    /**
     * Sets to random fiches in the given pair.
     *
     * @param fishPair           some pair
     * @param populationIterator population Iterator
     */
    private void getRandomFishPair(FishPair fishPair, Iterator<Fish> populationIterator) {
        int populationSize = this.population.size();
        int firstRandomInt = this.random.nextInt(populationSize);
        int secondRandomInt = this.random.nextInt(populationSize);
        int counter = 0;
        Fish firstFish = null;
        Fish secondFish = null;

        if (firstRandomInt != secondRandomInt) {
            while (populationIterator.hasNext()) {
                Fish fish = populationIterator.next();
                if (counter == firstRandomInt) {
                    fishPair.setFishOne(fish);
                } else if (counter == secondRandomInt) {
                    fishPair.setFishTwo(fish);
                } else if (firstFish != null && secondFish != null) {
                    break;
                }
                counter++;
            }
        }
    }
}
