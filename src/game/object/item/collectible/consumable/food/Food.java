package game.object.item.collectible.consumable.food;

import game.object.item.collectible.consumable.*;

/**
 * Food restores hunger when consumed
 */
public abstract class Food extends Consumable {

    private static final long serialVersionUID = 1L;

    private double fullness;

    public Food() {
        setFullness(DEFAULT);
    }


    /**
    * Returns value of fullness
    * @return
    */
    public double getFullness() {
        return fullness;
    }

    /**
    * Sets new value of fullness
    * @param
    */
    public void setFullness(double fullness) {
        this.fullness = fullness;
    }
}
