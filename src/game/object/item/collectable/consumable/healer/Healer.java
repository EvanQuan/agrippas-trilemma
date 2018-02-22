package game.object.item.collectable.consumable.healer;

import game.object.item.collectable.consumable.*;

/**
 * Healer restores health when consumed
 */
 public abstract class Healer extends Consumable {

    private static final long serialVersionUID = 1L;

    private double health;

    /**
     * @param health
     */
    public Healer() {
    setHealth(DEFAULT);
    }

    /**
     * @return the health
     */
    public double getHealth() {
    return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(double health) {
    this.health = health;
    }
 }
