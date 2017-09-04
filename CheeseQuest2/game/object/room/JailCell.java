package game.object.room;

public class JailCell extends Room {
	private static final long serialVersionUID = 1L;
	public JailCell() {
		addSingleName(new String[] {"jail cell","cell"});
		addDescription(new String[] {"It's cold and dusty."});

		// getCoordinates().setEastRoom(Hallway.getInstance());
	}
}
