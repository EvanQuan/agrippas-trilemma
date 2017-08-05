package game.system;

import java.util.HashMap;
import game.object.*;

/**
 * Contains rooms which are spacially related to one another
 * Contains player
 */
public class World {
	Room currentRoom = JailCell.getInstance();
	Player player = Player.getInstance();

	public World() {
		// Starting room
		currentRoom = JailCell.getInstance();

		player = Player.getInstance();
	}
}
