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
     */
    @Override
    public void performAction(Aquarium aquarium) {
        Queue<Fish> population = aquarium.getPopulation();
        Aquarium.timeCounter++;
        while (population.size() > 0 && population.peek().checkDieTime(Aquarium.timeCounter)) {
            population.remove();
        }
    }
}
