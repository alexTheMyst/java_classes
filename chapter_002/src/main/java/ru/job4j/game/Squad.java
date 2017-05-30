package ru.job4j.game;

import java.util.Random;

/**
 * Squad interface.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.05.17
 */
public abstract class Squad {

    /**
     * Last live creature index in creatures array.
     */
    private int creaturesIndex;

    /**
     * Container for creatures.
     */
    private final Creature[] creatures = new Creature[8];

    /**
     * Flag of defeated squad, if true means all creatures are dead.
     */
    private boolean defeated;

    /**
     * Random generator.
     */
    private final Random random = new Random();

    /**
     * Default constructor.
     */
    public Squad() {
    }

    /**
     * Adds a given creature to the array.
     *
     * @param creature given creature
     */
    void setupCreature(Creature creature) {
        this.creatures[creaturesIndex] = creature;
        if (creaturesIndex < this.creatures.length - 1) {
            this.creaturesIndex += 1;
        }
    }

    /**
     * Gets array of creatures size.
     *
     * @return creatures size
     */
    int getSquadSize() {
        return this.creatures.length;
    }

    /**
     * Gets at first privileged creature or just random creature from the squad.
     *
     * @return creature
     */
    public Creature getRandomFighter() {
        Creature creature;
        int index = 0;
        try {
            creature = getPrivilegedFighter();
        } catch (NoPrivilegedFighters npf) {
            if (this.creaturesIndex > 0) {
                index = this.random.nextInt(this.creaturesIndex);
                creature = this.creatures[index];
                if (!creature.isAlive()) {
                    moveKilledCreature(index);
                    creature = getRandomFighter();
                }
            } else {
                creature = this.creatures[index];
            }
        }
        return creature;
    }

    /**
     * Gets a first found privileged creature.
     *
     * @return creature
     * @throws NoPrivilegedFighters if on privileged creature in the squad
     */
    public Creature getPrivilegedFighter() throws NoPrivilegedFighters {
        Creature creature = null;
        for (Creature c : this.creatures) {
            if (c != null && c.isPrivileged()) {
                creature = c;
            }
        }
        if (creature == null) {
            throw new NoPrivilegedFighters();
        }
        return creature;
    }

    /**
     * Moves dead creature to the end of squad.
     *
     * @param killedCreatureIndex creature index in the creatures array.
     */
    private void moveKilledCreature(int killedCreatureIndex) {
        Creature tmpCreature = this.creatures[killedCreatureIndex];
        this.creatures[killedCreatureIndex] = this.creatures[creaturesIndex];
        this.creatures[creaturesIndex] = tmpCreature;
        this.creaturesIndex--;
        if (this.creaturesIndex <= 0) {
            this.defeated = true;
        }
    }

    /**
     * Getter for defeated flag.
     *
     * @return true if current squad is defeated
     */
    public boolean isDefeated() {
        return this.defeated;
    }
}