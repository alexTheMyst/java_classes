package ru.job4j.game;

/**
 * Represents squad of humans.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.05.17
 */
public class HumansSquad extends Squad {
    /**
     * Constructor fills Squad with creatures.
     */
    public HumansSquad() {
        super();
        int squadSize = super.getSquadSize();
        for (int index = 0; index < squadSize; index++) {
            if (index == 0) {
                Creature creature = new Creature("Magician", this);
                creature.addAction(new AttackCreatureAction("magic", 10));
                creature.addAction(new ImproveCreatureAction("improve", 150));
                super.setupCreature(creature);
            } else if (index > 0 && index < 4) {
                Creature creature = new Creature("Crossbowman", this);
                creature.addAction(new AttackCreatureAction("сrossbow", 5));
                creature.addAction(new AttackCreatureAction("weapon", 3));
                super.setupCreature(creature);
            } else {
                Creature creature = new Creature("Swordsman", this);
                creature.addAction(new AttackCreatureAction("sword", 18));
                super.setupCreature(creature);
            }
        }
    }
}