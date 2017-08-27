package game.object.room;

public class Hallway extends Room {
	public Hallway() {
		addSingleName(new String[] {"hallway"});
		addDescription(new String[] {"It's long"});

		// getCoordinates().setWestRoom(JailCell.getInstance());
	}
}
