package ru.job4j.aquarium.actions;

import ru.job4j.aquarium.Aquarium;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Show population action.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class ShowPopulationAction implements Action {
    /**
     * Creates show population string.
     *
     * @param aquarium population
     * @return string with current population
     */
    @Override
    public String performAction(Aquarium aquarium) {
        return String.format("Aquarium population at %s is: %d",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.mm.yyyy H:m:s")),
                aquarium.getPopulation().size());
    }
}
