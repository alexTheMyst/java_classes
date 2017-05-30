package ru.job4j.game;

/**
 * Undeads squad.
 *
 * @author alexey
 * @version 30.05.17
 */
public class UndeadsSquad extends Squad {
    /**
     * Constructor fills Squad with creatures.
     */
    public UndeadsSquad() {
        super();
        int squadSize = super.getSquadSize();
        for (int index = 0; index < squadSize; index++) {
            if (index == 0) {
                Creature creature = new Creature("Necromancer", this);
                creature.addAction(new AttackCreatureAction("attack", 5));
                creature.addAction(new DeteriorateCreatureAction("damnation", 50));
                super.setupCreature(creature);
            } else if (index > 0 && index < 4) {
                Creature creature = new Creature("Hunter", this);
                creature.addAction(new AttackCreatureAction("bow", 4));
                creature.addAction(new AttackCreatureAction("attack", 2));
                super.setupCreature(creature);
            } else {
                Creature creature = new Creature("Zombie", this);
                creature.addAction(new AttackCreatureAction("spear", 18));
                super.setupCreature(creature);
            }
        }
    }

}
