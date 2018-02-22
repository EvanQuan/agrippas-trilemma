package game.object.item.collectible;

import game.object.item.*;

/**
 * Contains a value and weight
 */
public abstract class Collectible extends Item {

    private static final long serialVersionUID = 1L;

    /**
     * Cost multiplier for price in stores
     */
    private double value;
    private double weight;

    /**
    * Default Collectible constructor
    */
    public Collectible() {
        setValue(DEFAULT);
        setWeight(DEFAULT);
        addProperty("collectable");
    }

    /**
    * Returns value of value
    * @return
    */
    public double getValue() {
        return value;
    }

    /**
    * Returns value of weight
    * @return
    */
    public double getWeight() {
        return weight;
    }

    /**
    * Sets new value of value
    * @param
    */
    public void setValue(double value) {
        this.value = value;
    }

    /**
    * Sets new value of weight
    * @param
    */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
