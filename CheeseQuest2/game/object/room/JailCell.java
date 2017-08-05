package game.object.room;

public class JailCell extends Room {
	private JailCell() {
		addSingleName(new String[] {"jail cell","cell"});
		addDescription(new String[] {"It's cold and dusty."});
		
		coordinates.setEastRoom(Hallway.getInstance());
	}
}
