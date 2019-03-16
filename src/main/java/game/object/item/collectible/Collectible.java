package game.object.item.collectible;

import game.object.item.Item;

/**
 * Items that can be collected by the player. Stored in player inventory.
 * Contains a value (which is how shop price is determined), and weight, which
 * contributes to player total inventory weight.
 *
 * @author Evan Quan
 */
public abstract class Collectible extends Item {

    private static final long serialVersionUID = 1L;

    private double value;
    private double weight;

    /**
     * Default Collectible constructor
     */
    public Collectible() {
        setValue(DEFAULT);
        setWeight(DEFAULT);
    }

    /**
     * Returns value of item, which is a multiplier of how much it costs in
     * stores
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets new value of collectible
     *
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Returns value of weight
     *
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets new value of weight
     *
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
