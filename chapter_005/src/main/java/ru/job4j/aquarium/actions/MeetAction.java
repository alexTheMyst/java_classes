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
     * @return string which represents meet action
     */
    @Override
    public String performAction(Aquarium aquarium) {
        String result;
        FishPair pair = aquarium.getFishPair();
        Fish firstFish = pair.getFishOne();
        Fish secondFish = pair.getFishTwo();

        if (firstFish != secondFish && firstFish.isMale() == secondFish.isMale()) {
            result = String.format("Fish #%d meet fish #%d", firstFish.getId(), secondFish.getId());
        } else {
            result = "";
        }
        return result;
    }
}
