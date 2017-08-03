package game.object.item.collectable.currency;

public class Coin extends Currency {
	public Coin() {
		addSingleName(new String[] {"coin"});
		addPluralName(new String[] {"coins"});
		addDescription(new String[] {"The edges are worn down from handling."});
		setWeight(0.01);
	}
}
