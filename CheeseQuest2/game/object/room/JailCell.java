package game.object.room;

public class JailCell extends Room {
	private static JailCell jailCell = new JailCell();
	private JailCell() {
		addSingleName(new String[] {"jail cell","cell"});
		addDescription(new String[] {"It's cold and dusty."});

		getCoordinates().setEastRoom(Hallway.getInstance());
	}

	public static JailCell getInstance() {
		return jailCell;
	}
}
