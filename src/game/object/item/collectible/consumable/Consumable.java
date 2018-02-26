package game.object.item.collectible.consumable;

import game.object.item.collectible.*;

/**
 * Can be consumed by player, removing from inventory
 * Can give corruption upon consumption
 */
public abstract class Consumable extends Collectible {

    private static final long serialVersionUID = 1L;

    private double corruption;

    /**
     * Default Consumable constructor
     */
    public Consumable() {
        setCorruption(DEFAULT);
    }

    /**
    * Returns value of corruption
    * @return
    */
    public double getCorruption() {
        return corruption;
    }

    /**
    * Sets new value of corruption
    * @param
    */
    public void setCorruption(double corruption) {
        this.corruption = corruption;
    }

}
