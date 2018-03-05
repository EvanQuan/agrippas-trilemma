package game.system;

import java.io.Serializable;

import game.object.item.background.character.Player;
import game.object.location.Location;
import main.CheeseQuest;

/**
 * Object to be saved and loaded by WriteObject and ReadObject Contains: - rooms
 * which are spatially related to one another - player - turn count - game
 * version - old game version cannot be loaded
 */
public class Save implements Serializable {

    private static final long serialVersionUID = 1L;

    private Location room;
    private Player player;
    private int turnCount;
    private String name; // Name of game save
    private String version;

    public Save(Location room, Player player, int turnCount, String name) {
        // Starting room
        // currentRoom = JailCell.getInstance();
        this.room = room;
        this.player = player;
        this.turnCount = turnCount;
        // player = Player.getInstance();
        setName(name);
        this.version = CheeseQuest.getVersion();
    }

    public Save(String name) {
        initialize();
        this.turnCount = 0;
        this.version = CheeseQuest.getVersion();
        setName(name);

    }

    /**
     * This is the string representation of the world used in LoadMenu
     *
     * @return
     */
    public String getInfo() {
        return "Room: " + room.getSingleName() + " | Turns: " + turnCount + " | Version: " + version;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getRoom() {
        return room;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public String getVersion() {
        return version;
    }

    /**
     * Instantiates all the rooms, default player
     */
    public void initialize() {
        // TODO
    }

    public void setName(String name) {
        this.name = Utility.toLowerTitleCase(name);
    }
}
