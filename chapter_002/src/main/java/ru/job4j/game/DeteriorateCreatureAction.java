package ru.job4j.game;

/**
 * Action to deteriorate enemies creatures.
 *
 * @author alexey
 * @version 30.05.17
 */
public class DeteriorateCreatureAction implements CreatureAction {
    /**
     * Deterioration action name.
     */
    private final String actionName;

    /**
     * Deterioration ratio..
     */
    private final int actionImprovement;

    /**
     * Constructor.
     *
     * @param actionName        some name
     * @param actionImprovement desirable deterioration ratio
     */
    public DeteriorateCreatureAction(String actionName, int actionImprovement) {
        this.actionName = actionName;
        this.actionImprovement = actionImprovement;
    }

    /**
     * Performs an action with given creature.
     *
     * @param creature  given creature
     * @param amendment improves or decreases damage points
     */
    @Override
    public void performAction(Creature creature, int amendment) {
        System.out.println(" Deteriorate " + creature.getType()
                + " with " + this.actionName
                + " remove " + this.actionImprovement + "% to points of damage.");
        creature.setImprovement(actionImprovement);
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
        try {
            performAction(enemySquad.getPrivilegedFighter(), amendment);
        } catch (NoPrivilegedFighters npf) {
            System.out.println("No privileged fighters in enemies squad!");
        }
    }
}