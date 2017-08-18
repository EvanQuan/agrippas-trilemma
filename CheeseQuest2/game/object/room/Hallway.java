package game.object.room;

public class Hallway extends Room {
	private static Hallway hallway = new Hallway();
	private Hallway() {
		addSingleName(new String[] {"hallway"});
		addDescription(new String[] {"It's long"});

		getCoordinates().setWestRoom(JailCell.getInstance());
	}

	public static Hallway getInstance() {
		return hallway;
	}
}
