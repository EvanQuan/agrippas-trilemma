package game.menu;

import org.apache.commons.lang.WordUtils;
import game.system.*;
import game.object.room.*;

/**
 * Manages all in game actions
 */
public class GameMenu extends Menu {

    private static GameMenu instance;
    private Room room;
    private Player player;
    private int turnCount;

    private GameMenu() {
    }

    @Override
    public void outputPrompt() {
        look();
    }
    public static GameMenu getInstance() {
        if (instance == null) {
            instance  = new GameMenu();
        }
        return instance;
    }
    public void setWorld(World world) {
        room = world.getRoom();
        player = world.getPlayer();
        turnCount = world.getTurnCount();
    }
    public World getWorld() {
        World world = new World(room,player,turnCount);
        return world;
    }
    public void quit() {
        changeToLoadMenu();
    }

    /**
     * Look at room surroundings
     * People present
     * Items
     * Adjacent rooms
     */
    public void outputLook() {
        outputTitle(room.getSingleName());
    }
    // The Northmen say the world will come to an end. The sun will grow black. The earth will sink into the sea. The stars will disappear. Fire and water will meet. Flames will play against the sky. The heavens and earth and all the world will be burned. All the gods will be dead, and the warriors of Valhalla, and people everywhere. Senua, prepare yourself for Ragnarok, for it is nigh.
    // The Northmen say the world will come to an end. The sun will grow black. The earth will sink into the sea. The stars will disappear. Fire and water will meet. Flames will play against the sky. The heavens and earth and all the world will be burned. All the gods will be dead, the warriors of Valhalla, and people everywhere. Prepare yourself for Ragnarok, for it is nigh.
}
