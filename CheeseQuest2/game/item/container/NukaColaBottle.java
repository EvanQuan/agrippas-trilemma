package game.item.container;

public class NukaColaBottle extends Container {
	public NukaColaBottle() {
		super(
			new String[] {"Nuka-Cola bottle", "cola bottle", "empty bottle", "bottle"},
			new String[] {"Nuka-Cola bottles", "cola bottles", "empty bottles", "bottles"},
			new String[] {"It is currently empty."},
			2,
			0.5
		);
	}

}
