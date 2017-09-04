package game.object.room;

public class Hallway extends Room {
	private static final long serialVersionUID = 1L;
	public Hallway() {
		addSingleName(new String[] {"hallway"});
		addDescription(new String[] {"It's long"});

		// getCoordinates().setWestRoom(JailCell.getInstance());
	}
}
