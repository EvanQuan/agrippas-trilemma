package game.object.room;

public class JailCell extends Room {
	public JailCell() {
		addSingleName(new String[] {"jail cell","cell"});
		addDescription(new String[] {"It's cold and dusty."});

		// getCoordinates().setEastRoom(Hallway.getInstance());
	}
}
