package game.item.consumable.food;

import item.Item;

/**
 * Food restores hunger when consumed
 */
public class Food extends Consumable {
	private double hunger;

	public Food(String[] inSingleNames, String[] inPluralNames, String[] inDescriptions, double inValue, double inWeight, double inActionPoints, double inRads, double inHunger) {
		super(inSingleNames, inPluralNames, inDescriptions, inValue, inWeight, inActionPoints, inRads);
		hunger = inHunger;
	}

	public double getHunger() {
		return(hunger);
	}
}
