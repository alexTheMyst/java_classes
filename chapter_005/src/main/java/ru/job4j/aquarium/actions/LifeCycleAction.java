package ru.job4j.aquarium.actions;

import ru.job4j.aquarium.Aquarium;
import ru.job4j.aquarium.Fish;

import java.util.Queue;

/**
 * Life cycle action.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class LifeCycleAction implements Action {
    /**
     * Checks population for outdated fish.
     *
     * @param aquarium aquarium
     * @return a string
     */
    @Override
    public String performAction(Aquarium aquarium) {
        Queue<Fish> population = aquarium.getPopulation();
        Aquarium.timeCounter++;
        Fish fish;
        String result = "";
        while (population.size() > 0 && population.peek().getLifePeriod() < Aquarium.timeCounter) {
            fish = population.remove();
            result += String.format("Fish #%d died.\n", fish.getId());
        }
        return result;
    }
}
