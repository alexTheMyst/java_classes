package ru.job4j.game;

/**
 * Action to attack another creature.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 22.05.17
 */
public class AttackCreatureAction implements CreatureAction {

    /**
     * Weapon type variable.
     */
    private final String weaponType;

    /**
     * Points of power.
     */
    private final int strength;

    /**
     * Constructor.
     *
     * @param weaponType weapon name
     * @param strength   weapon power
     */
    public AttackCreatureAction(String weaponType, int strength) {
        this.weaponType = weaponType;
        this.strength = strength;
    }

    /**
     * Performs an action with given creature.
     *
     * @param creature  given creature
     * @param amendment improves or decreases damage points
     */
    @Override
    public void performAction(Creature creature, int amendment) {
        int strikePower = this.strength * amendment / 100;
        System.out.println(" Attack with a " + this.weaponType + " inflicts " + strikePower + " points of damage.");
        creature.getDamage(strikePower);
    }

    /**
     * Performs the action against random creature from enemies squad.
     *
     * @param friendlySquad friendly squad
     * @param enemySquad    enemy squad
     * @param amendment     amendment amount
     */
    @Override
    public void performAction(Squad friendlySquad, Squad enemySquad, int amendment) {
        performAction(enemySquad.getRandomFighter(), amendment);
    }
}
