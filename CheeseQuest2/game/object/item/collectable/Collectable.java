package game.object.item.collectable;

import game.object.item.*;

/**
 * Can be contained in inventories
 */
public abstract class Collectable extends Item {
    private double value;
    private double weight;

    /**
    * Default Collectable constructor
    */
    public Collectable() {
        setValue(DEFAULT);
        setWeight(DEFAULT);
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
