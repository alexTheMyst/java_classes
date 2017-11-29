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
     */
    @Override
    public void performAction(Aquarium aquarium) {
        Fish fish;
        FishPair randomFishPair = aquarium.getFishPair();
        Fish fishOne = randomFishPair.getFishOne();
        Fish fishTwo = randomFishPair.getFishTwo();
        if (fishOne != fishTwo && fishOne.checkBorn(fishTwo)) {
            int randomInt = aquarium.getRandom().nextInt(100);
            int life = 100 + randomInt;
            boolean isMale = randomInt % 2 == 0;
            fish = new Fish(life, isMale);
            aquarium.add(fish);
        }
    }
}