package ru.job4j.aquarium;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.aquarium.actions.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for aquarium simulation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.11.17
 */
public class AquariumTest {
    /**
     * Aquarium instance.
     */
    private Aquarium aquarium;

    /**
     * Setup new aquarium before each test.
     */
    @Before
    public void setup() {
        this.aquarium = new Aquarium();
    }

    /**
     * Tests add a fish.
     */
    @Test
    public void whenAddFishThenPopulationSizeOne() {
        Fish fish = new Fish(1, true);

        this.aquarium.add(fish);

        assertThat(this.aquarium.getPopulation().size(), is(1));
    }

    /**
     * Tests meet action.
     */
    @Test
    public void whenAllFishAreMaleThenMeet() {
        for (int i = 0; i < 5; i++) {
            Fish fish = new Fish(1, true);
            aquarium.add(fish);
        }
        Action action = new MeetAction();
        String result = action.performAction(this.aquarium);

        assertThat(result, is(containsString("meet")));
    }

    /**
     * Tests meet action negative scenario.
     */
    @Test
    public void whenTwoFichesWithDifferentSexThenTheyDontMeet() {
        Fish fish = new Fish(1, true);
        Fish otherFish = new Fish(1, false);
        this.aquarium.add(fish);
        this.aquarium.add(otherFish);
        Action action = new MeetAction();

        assertThat(action.performAction(this.aquarium), is(containsString("")));
    }

    /**
     * Tests birth action.
     */
    @Test
    public void whenTwoFishThenNewFishWasBorn() {
        Fish fish = new Fish(1, true);
        Fish otherFish = new Fish(1, false);
        this.aquarium.add(fish);
        this.aquarium.add(otherFish);
        Action action = new BirthAction();
        String result = action.performAction(this.aquarium);

        System.out.println(result);
        assertThat(result, is(containsString("born")));
    }

    /**
     * Tests show action.
     */
    @Test
    public void whenAddFishThenShowActionGetsOne() {
        Fish fish = new Fish(1, true);

        this.aquarium.add(fish);
        Action action = new ShowPopulationAction();
        String result = action.performAction(this.aquarium);

        assertThat(result, is(containsString("1")));
    }

    /**
     * Tests die action.
     */
    @Test
    public void whenAddFishHasZeroLifeThenFishDie() {
        Fish fish = new Fish(0, true);

        this.aquarium.add(fish);
        Action action = new LifeCycleAction();
        String result = action.performAction(this.aquarium);

        System.out.println(result);
        assertThat(result, is(containsString("died")));
    }

    /**
     * Aquarium simulation.
     */
    @Test
    public void lifeSimulator() {
        Action[] actions = new Action[4];
        actions[0] = new LifeCycleAction();
        actions[1] = new ShowPopulationAction();
        actions[2] = new MeetAction();
        actions[3] = new BirthAction();
        Fish fish = new Fish(10, true);
        Fish fishTwo = new Fish(10, false);
        this.aquarium.add(fish);
        this.aquarium.add(fishTwo);

        while (this.aquarium.getPopulation().size() > 1) {
            for (Action action : actions) {
                String res = action.performAction(this.aquarium);
                System.out.println(res);
            }
        }
        System.out.println("Sorry all fish are died.");
    }
}