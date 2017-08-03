package game.object.item.collectable.consumable.food;

public class Biscuit extends Food {
	public Biscuit() {
		addSingleName(new String[] {"hardtack biscuit", "biscuit"});
		addPluralName(new String[] {"hardtack biscuits", "biscuits"});
		addDescription(new String[] {"harder than a brick and probably just as tasty."});
		setValue(5);
		setWeight(0.1);
		setHunger(30);
	}
}
