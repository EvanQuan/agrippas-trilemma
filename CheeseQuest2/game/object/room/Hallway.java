package game.object.room;

public class Hallway extends Room {
	private static final long serialVersionUID = 1L;
	public Hallway() {
		setSingleNames(new String[] {"hallway"});
		setDescriptions(new String[] {"It's long"});

		// getCoordinates().setWestRoom(JailCell.getInstance());
	}
}
