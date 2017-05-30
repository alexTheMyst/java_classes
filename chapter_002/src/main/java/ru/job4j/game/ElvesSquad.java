package ru.job4j.game;

/**
 * Elves squad.
 *
 * @author alexey
 * @version 30.05.17
 */
public class ElvesSquad extends Squad {
    /**
     * Constructor fills Squad with creatures.
     */
    public ElvesSquad() {
        super();
        int squadSize = super.getSquadSize();
        for (int index = 0; index < squadSize; index++) {
            if (index == 0) {
                Creature creature = new Creature("Magician", this);
                creature.addAction(new AttackCreatureAction("magic", 10));
                creature.addAction(new ImproveCreatureAction("improve", 150));
                super.setupCreature(creature);
            } else if (index > 0 && index < 4) {
                Creature creature = new Creature("Archer", this);
                creature.addAction(new AttackCreatureAction("bow", 5));
                creature.addAction(new AttackCreatureAction("weapon", 3));
                super.setupCreature(creature);
            } else {
                Creature creature = new Creature("Warrior", this);
                creature.addAction(new AttackCreatureAction("sword", 15));
                super.setupCreature(creature);
            }
        }
    }

}