package ru.job4j.aquarium.actions;

import ru.job4j.aquarium.Aquarium;
import ru.job4j.aquarium.Fish;
import ru.job4j.aquarium.FishPair;

/**
 * Meet action.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class MeetAction implements Action {
    /**
     * Creates string which represents meet action.
     *
     * @param aquarium aquarium
     */
    @Override
    public void performAction(Aquarium aquarium) {
        FishPair pair = aquarium.getFishPair();
        Fish firstFish = pair.getFishOne();
        Fish secondFish = pair.getFishTwo();

        if (firstFish != secondFish) {
            firstFish.checkMeet(secondFish);
        }
    }
}
