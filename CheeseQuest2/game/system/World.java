package game.system;

import java.io.Serializable;
import java.util.HashMap;
import game.*;
import game.object.*;

/**
 * Object to be saved and loaded by WriteObject and ReadObject
 * Contains rooms which are spacially related to one another
 * Contains player
 */
public class World implements Serializable {
	Room currentRoom = JailCell.getInstance();
	Player player = Player.getInstance();

	public World() {
		// Starting room
		// currentRoom = JailCell.getInstance();

		player = Player.getInstance();
	}

	public String toString() {
		return player.toString();
	}
}
