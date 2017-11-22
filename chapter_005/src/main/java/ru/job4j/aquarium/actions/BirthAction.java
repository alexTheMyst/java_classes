package ru.job4j.aquarium.actions;

import ru.job4j.aquarium.Aquarium;
import ru.job4j.aquarium.Fish;
import ru.job4j.aquarium.FishPair;

/**
 * Birth action.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class BirthAction implements Action {
    /**
     * Creates string which represents birth action.
     *
     * @param aquarium population
     * @return string which represents birth action
     */
    @Override
    public String performAction(Aquarium aquarium) {
        Fish fish;
        String result = "";
        FishPair randomFishPair = aquarium.getFishPair();
        Fish fishOne = randomFishPair.getFishOne();
        Fish fishTwo = randomFishPair.getFishTwo();
        if (fishOne != fishTwo && fishOne.isMale() != fishTwo.isMale()) {
            int randomInt = aquarium.getRandom().nextInt(100);
            int life = 100 + randomInt;
            boolean isMale = randomInt % 2 == 0;
            fish = new Fish(life, isMale);
            aquarium.add(fish);
            result = String.format("Fish #%d was born.", fish.getId());
        }
        return result;
    }
}
