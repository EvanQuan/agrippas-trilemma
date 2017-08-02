package game.item.consumable;

public class Consumable extends Item {
	private double actionPoints;
	private double rads;

	public Consumable(String[] inSingleNames, String[] inPluralNames, String[] inDescriptions, double inValue, double inWeight, double inActionPoints, double inRads) {
		super(inSingleNames, inPluralNames, inDescriptions, inValue, inWeight);
		actionPoints = inActionPoints;
		rads = inRads;
	}

	public double getActionPoints() {
		return(actionPoints);
	}
	public double getRads() {
		return(rads);
	}
}
