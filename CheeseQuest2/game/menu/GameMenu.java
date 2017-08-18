package game.menu;

import game.system.*;

public class GameMenu extends Menu {

    private static GameMenu gameMenu = new GameMenu();
    private World world;

    private GameMenu() {
    }
    public static GameMenu getInstance() {
        return gameMenu;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public void quit() {

        changeToLoadMenu();
    }
    // The Northmen say the world will come to an end. The sun will grow black. The earth will sink into the sea. The stars will disappear. Fire and water will meet. Flames will play against the sky. The heavens and earth and all the world will be burned. All the gods will be dead, and the warriors of Valhalla, and people everywhere. Senua, prepare yourself for Ragnarok, for it is nigh.
}
