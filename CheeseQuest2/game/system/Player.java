/**
 *
 */
package game.system;

import java.io.Serializable;
import game.*;

/**
 * @author Quan
 *
 */
public class Player implements Serializable {

	private String name;
	private static Player player = new Player();
	/**
	 *
	 */
	private Player() {
		name = "Player name: Bob"; // Placeholder
	}


	public static Player getInstance() {
		return player;
	}
	public String toString() {
		return name;
	}

}
