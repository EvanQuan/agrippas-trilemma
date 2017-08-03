package game.object.item.collectable.currency;

public class GoblinTooth extends Currency {
	public GoblinTooth() {

	addSingleName(new String[] {"goblin tooth","tooth"});
	addPluralName(new String[] {"goblin teeth", "teeth"});
	addDescription(new String[] {"Small and pointy."});
	setWeight(0.01);
	}
}
