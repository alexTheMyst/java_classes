package ru.job4j.aquarium.actions;

import ru.job4j.aquarium.Aquarium;

/**
 * Action abstraction.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public interface Action {

    /**
     * Performs some action with aquarium.
     *
     * @param aquarium aquarium
     */
    void performAction(Aquarium aquarium);
}
