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
public class Save extends TextUtility implements Serializable {

    private static final long serialVersionUID = 1L;

    private Room room;
    private Player player;
    private int turnCount;
    private String name; // Name of game save
    private String version;

    public Save(String name) {
        initialize();
        this.turnCount = 0;
        this.version = CheeseQuest.getVersion();
        setName(name);

    }
    public Save(Room room, Player player, int turnCount, String name) {
        // Starting room
        // currentRoom = JailCell.getInstance();
        this.room = room;
        this.player = player;
        this.turnCount = turnCount;
        // player = Player.getInstance();
        setName(name);
        this.version = CheeseQuest.getVersion();
    }



    public void setName(String name) {
        this.name = toLowerTitleCase(name);
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
    public String getName() {
        return name;
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
