package ru.job4j.game;

/**
 * Orcs squad.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 22.05.17
 */
public class OrcsSquad extends Squad {
    /**
     * Constructor fills Squad with creatures.
     */
    public OrcsSquad() {
        super();
        int squadSize = super.getSquadSize();
        for (int index = 0; index < squadSize; index++) {
            if (index == 0) {
                Creature creature = new Creature("Shaman", this);
                creature.addAction(new AttackCreatureAction("magic", 10));
                creature.addAction(new DeteriorateCreatureAction("damnation", 50));
                super.setupCreature(creature);
            } else if (index > 0 && index < 4) {
                Creature creature = new Creature("Archer", this);
                creature.addAction(new AttackCreatureAction("bow", 3));
                creature.addAction(new AttackCreatureAction("knife", 2));
                super.setupCreature(creature);
            } else {
                Creature creature = new Creature("Hoblin", this);
                creature.addAction(new AttackCreatureAction("sword", 20));
                super.setupCreature(creature);
            }
        }
    }

}
