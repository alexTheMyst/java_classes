package ru.job4j.game;

/**
 * Creature action abstraction.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.05.17
 */
interface CreatureAction {

    /**
     * Some action on given creature with given amendment.
     *
     * @param creature some creature
     * @param amendment amendment
     */
    void performAction(Creature creature, int amendment);

    /**
     * Some action on creature from one of given squads.
     *
     * @param friendlySquad friendly squad
     * @param enemySquad enemy squad
     * @param amendment some amendment
     */
    void performAction(Squad friendlySquad, Squad enemySquad, int amendment);
}
