package game.object.room;

public class Hallway extends Room {
	private Hallway() {
		addSingleName(new String[] {"hallway"});
		addDescription(new String[] {"It's long"});
		
		coordinates.setWestRoom(JailCell.getInstance());
	}
}
