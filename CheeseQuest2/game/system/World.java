package game.system;

import java.io.Serializable;
import java.util.HashMap;
import game.*;
import game.object.*;
import game.object.room.*;
import game.object.item.background.person.*;
import main.CheeseQuest;

/**
 * Object to be saved and loaded by WriteObject and ReadObject
 * Contains:
 * - rooms which are spacially related to one another
 * - player
 * - turn count
 * - game version
 * - old game version cannot be loaded
 */
public class World implements Serializable {

    private static final long serialVersionUID = 1L;

    private Room room;
    private Player player;
    private int turnCount;
    private String version;

    public World(Room room, Player player, int turnCount) {
        // Starting room
        // currentRoom = JailCell.getInstance();
        this.room = room;
        this.player = player;
        this.turnCount = turnCount;
        // player = Player.getInstance();
        this.version = CheeseQuest.getVersion();
    }
    public World() {
        initialize();
        this.turnCount = 0;
        this.version = CheeseQuest.getVersion();
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
    public String getVersion() {
        return version;
    }

    /**
     * This is the string representation of the world used in LoadMenu
     * @return
     */
    public String getInfo() {
        return "Room: " + room.getSingleName() + " | Turns: " + turnCount + " | Version: " + version;
    }

    /**
     * Instantiates all the rooms, default player
     */
    public void initialize() {
        TestRoom testRoom = new TestRoom();

        // Starting room
        this.room = testRoom;
    }
}
