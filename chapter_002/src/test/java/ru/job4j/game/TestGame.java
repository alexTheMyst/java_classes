package ru.job4j.game;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for console game.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.05.17
 */
public class TestGame {

    /**
     * Tests creature creation.
     */
    @Test
    public void createSquad() {
        Squad squad = new HumansSquad();
        Creature creature = squad.getRandomFighter();
        assertThat(creature, notNullValue());
    }

    /**
     * Tests kill of creature.
     */
    @Test
    public void killCreature() {
        Creature goodGuy = new Creature("Good guy with a sword", null);
        goodGuy.addAction(new AttackCreatureAction("sword", 18));
        goodGuy.addAction(new AttackCreatureAction("bow", 5));
        Creature badGuy = new Creature("Bad guy", null);
        while (badGuy.isAlive()) {
            goodGuy.action(badGuy);
        }
        assertThat(badGuy.isAlive(), is(false));
    }

    /**
     * Tests that squad kills the creature.
     */
    @Test
    public void killCreatureWithSquad() {
        Squad squad = new HumansSquad();
        Creature badGuy = new Creature("Bad guy", null);
        while (badGuy.isAlive()) {
            squad.getRandomFighter().action(badGuy);
        }
        assertThat(badGuy.isAlive(), is(false));
    }

    /**
     * Tests that squad can be defeated.
     */
    @Test
    public void killAllCreaturesInSquad() {
        Squad squadHumans = new HumansSquad();
        Squad squadOrcs = new OrcsSquad();
        while (!squadOrcs.isDefeated()) {
            squadHumans.getRandomFighter().action(squadOrcs);
        }
        assertThat(squadOrcs.isDefeated(), is(true));
    }

    /**
     * Tests improvement action.
     */
    @Test
    public void improvementAction() {
        Creature someCreatureWithImprovement = new Creature("very good guy", null);
        someCreatureWithImprovement.addAction(new ImproveCreatureAction("improve", 150));
        Creature creature = new Creature("good guy", null);
        creature.addAction(new AttackCreatureAction("sword", 18));
        someCreatureWithImprovement.action(creature);
        Creature otherCreature = new Creature("bad guy", null);
        creature.action(otherCreature);
        creature.action(otherCreature);
        assertThat(otherCreature.getHp(), is(55));
    }

    /**
     * Tests deterioration action.
     */
    @Test
    public void deteriorationAction() {
        Creature badGuy = new Creature("Bad Guy", null);
        badGuy.addAction(new DeteriorateCreatureAction("bad action", 50));
        Creature goodGuy = new Creature("Good Guy", null);
        goodGuy.addAction(new AttackCreatureAction("attack", 10));
        badGuy.action(goodGuy);
        goodGuy.action(badGuy);
        assertThat(badGuy.getHp(), is(95));
    }

    /**
     * Tests that squad at first returns privileged fighter.
     *
     * @throws NoPrivilegedFighters if on privileged creature in the squad
     */
    @Test
    public void getPrivilegedCreature() throws NoPrivilegedFighters {
        Squad humansSquad = new HumansSquad();
        Creature goodGuy = new Creature("goodGuy", null);
        goodGuy.addAction(new ImproveCreatureAction("improve", 150));
        goodGuy.action(humansSquad.getRandomFighter());
        assertThat(humansSquad.getPrivilegedFighter(), is(Creature.class));
    }

    /**
     * Tests NoPrivilegedFighters exception.
     *
     * @throws NoPrivilegedFighters if on privileged creature in the squad
     */
    @Test(expected = NoPrivilegedFighters.class)
    public void tryGetPrivilegedCreature() throws NoPrivilegedFighters {
        Squad humansSquad = new HumansSquad();
        assertThat(humansSquad.getPrivilegedFighter(), is(Creature.class));
    }

    /**
     * Tests two random squad battle.
     */
    @Test
    public void twoRandomSquadFigth() {
        Random random = new Random();
        Squad squad1;
        Squad squad2;
        if (random.nextInt(10) < 5) {
            squad1 = new HumansSquad();
        } else {
            squad1 = new ElvesSquad();
        }
        if (random.nextInt(10) < 5) {
            squad2 = new OrcsSquad();
        } else {
            squad2 = new UndeadsSquad();
        }

        while (!squad1.isDefeated() && !squad2.isDefeated()) {
                squad1.getRandomFighter().action(squad2);
            if (!squad2.isDefeated()) {
                squad2.getRandomFighter().action(squad1);
            }
        }
        System.out.println(String.format("Squad 1 defeated is: %s", squad1.isDefeated()));
        System.out.println(String.format("Squad 2 defeated is: %s", squad2.isDefeated()));
        assertThat(true, anyOf(equalTo(squad1.isDefeated()), equalTo(squad2.isDefeated())));
    }
}
