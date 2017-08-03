package game.object.item.collectable.consumable;

import game.object.item.collectable.*;

/**
 * Can be consumed by player, removing from inventory
 * Can give corruption upon consumption
 */
public abstract class Consumable extends Collectable {
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
