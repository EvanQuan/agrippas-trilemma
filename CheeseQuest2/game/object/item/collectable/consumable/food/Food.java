package game.object.item.collectable.consumable.food;

import game.object.item.collectable.consumable.*;

/**
 * Food restores hunger when consumed
 */
public abstract class Food extends Consumable {

    private static final long serialVersionUID = 1L;

    private double hunger;

    public Food() {
        setHunger(DEFAULT);
    }


    /**
    * Returns value of hunger
    * @return
    */
    public double getHunger() {
        return hunger;
    }

    /**
    * Sets new value of hunger
    * @param
    */
    public void setHunger(double hunger) {
        this.hunger = hunger;
    }
}
