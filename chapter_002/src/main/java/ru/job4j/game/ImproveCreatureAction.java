package ru.job4j.game;

/**
 * Action to improve friendly creature.
 *
 * @author alexey
 * @version 23.05.17
 */
public class ImproveCreatureAction implements CreatureAction {
    /**
     * Action name.
     */
    private final String actionName;

    /**
     * Improvement ratio.
     */
    private final int actionImprovement;

    /**
     * Constructor.
     *
     * @param actionName        some action name
     * @param actionImprovement desirable improvement ratio
     */
    public ImproveCreatureAction(String actionName, int actionImprovement) {
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
        System.out.println(" Improve " + creature.getType()
                + " with " + this.actionName
                + " adds " + this.actionImprovement + "% to points of damage.");
        creature.setImprovement(actionImprovement);
    }

    /**
     * Performs the action against random creature from friendly squad.
     *
     * @param friendlySquad friendly squad
     * @param enemySquad    enemy squad
     * @param amendment     amendment amount
     */
    @Override
    public void performAction(Squad friendlySquad, Squad enemySquad, int amendment) {
        performAction(friendlySquad.getRandomFighter(), amendment);
    }
}