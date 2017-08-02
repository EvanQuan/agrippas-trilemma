package game.item.food;

public class Biscuit extends Food {
	public Biscuit() {
		super(
			new String[] {"hardtack biscuit", "biscuit"},
			new String[] {"hardtack biscuits", "biscuits"},
			new String[] {"harder than a brick and probably just as tasty."},
			5,
			0.1,
			30
		);
	}
}
