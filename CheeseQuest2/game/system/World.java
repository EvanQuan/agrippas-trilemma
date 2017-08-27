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
 * Contains turn count
 */
public class World implements Serializable {

	public static final long serialVersionUID = 1L;

	private Room room;
	private Player player;
	private int turnCount;

	public World(Room room, Player player, int turnCount) {
		// Starting room
		// currentRoom = JailCell.getInstance();
		this.room = room;
		this.player = player;
		this.turnCount = turnCount;
		// player = Player.getInstance();
	}


	public Room getRoom() {
		return room;
	}
	public Player getPlayer() {
		return player;
	}
	public int getTurnCount() {
		return turnCount;
	}

	/**
	 * This is the string representation of the world used in LoadMenu
	 * @return
	 */
	public String getInfo() {
		return "Room: " + room.getSingleName() + " | Turns: " + turnCount;
	}
}
