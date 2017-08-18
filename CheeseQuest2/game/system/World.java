package game.system;

import java.io.Serializable;
import java.util.HashMap;
import game.*;
import game.object.*;
import game.object.room.*;

/**
 * Object to be saved and loaded by WriteObject and ReadObject
 * Contains rooms which are spacially related to one another
 * Contains player
 */
public class World implements Serializable {
	private Room currentRoom = JailCell.getInstance();
	private Player player = Player.getInstance();
	private int turnCount;

	public World() {
		// Starting room
		// currentRoom = JailCell.getInstance();
		turnCount = 0;

		player = Player.getInstance();
	}

	/**
	 * This is the string representation of the world used in LoadMenu
	 * @return
	 */
	public String getInfo() {
		return "Room: " + currentRoom.getSingleName() + " | Turns: " + turnCount;
	}
}
