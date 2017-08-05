package game.system;

import java.util.HashMap;
import game.object.*;

/**
 * Contains rooms which are spacially related to one another
 */
public class World {
	HashMap<Room,Coordinates> rooms;
	Room currentRoom;

	public World() {
		rooms = new HashMap<Room,Coordinates>();
		
	}
}
