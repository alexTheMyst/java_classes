package ru.job4j.game;

import java.util.Random;

/**
 * General creature representation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.05.17
 */
public class Creature {
    /**
     * Health points.
     */
    private int hp = 100;

    /**
     * Alive flag.
     */
    private boolean alive = true;

    /**
     * Creature type.
     */
    private final String type;

    /**
     * Index value for action array.
     */
    private int actionIndex;

    /**
     * Creatures' actions.
     */
    private final CreatureAction[] actions = new CreatureAction[2];

    /**
     * Random generator for an action choise.
     */
    private final Random random = new Random();

    /**
     * Improvement score.
     */
    private int improvement = 100;

    /**
     * Friendly squad.
     */
    private final Squad friendlySquad;

    /**
     * Constructor.
     *
     * @param type creature type
     * @param friendlySquad friendly squad
     */
    public Creature(String type, Squad friendlySquad) {
        this.type = type;
        this.friendlySquad = friendlySquad;
    }

    /**
     * Performs some action on given creature.
     *
     * @param creature creature to hit.
     */
    void action(Creature creature) {
        System.out.println(this.type + " makes: ");
        this.actions[this.random.nextInt(this.actionIndex)].performAction(creature, this.improvement);
        this.improvement = 100;
    }

    /**
     * Performs an action on random creature form given squad.
     *
     * @param enemySquad enemy squad
     */
    void action(Squad enemySquad) {
        System.out.println(this.type + " makes: ");
        this.actions[this.random.nextInt(this.actionIndex)].performAction(this.friendlySquad, enemySquad, this.improvement);
        this.improvement = 100;
    }

    /**
     * Type getter @return the type variable.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Alive getter @return true if creature alive.
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * HP getter @return health points variable.
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Adds an @param action to the creature.
     */
    public void addAction(CreatureAction action) {
        this.actions[this.actionIndex++] = action;
    }

    /**
     * Gets @param points and decrease creature HP.
     */
    public void getDamage(int points) {
        if (points < this.hp) {
            this.hp = this.hp - points;
            System.out.println(" " + this.getType() + " get damaged " + points + " HP");
        } else {
            this.hp = 0;
            this.alive = false;
            System.out.println(" " + this.getType() + " dead");
        }
    }

    /**
     * Setter for improvement variable.
     *
     * @param actionImprovement improvement ratio
     */
    public void setImprovement(int actionImprovement) {
        this.improvement = actionImprovement;
    }

    /**
     * Returns true if improvement ratio greater than 100.
     *
     * @return true if creatue has an improvement
     */
    public boolean isPrivileged() {
        return this.improvement > 100;
    }
}