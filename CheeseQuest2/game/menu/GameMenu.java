package game.menu;

import game.system.*;
import game.object.room.*;

public class GameMenu extends Menu {

    private static GameMenu instance;
    private Room room;
    private Player player;
    private int turnCount;

    private GameMenu() {
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
    // The Northmen say the world will come to an end. The sun will grow black. The earth will sink into the sea. The stars will disappear. Fire and water will meet. Flames will play against the sky. The heavens and earth and all the world will be burned. All the gods will be dead, and the warriors of Valhalla, and people everywhere. Senua, prepare yourself for Ragnarok, for it is nigh.
}
