package game.menu;

import file.*;
/**
 * Prompts to save game save
 * Uses WriteObject to save game
 */
public class SaveMenu extends Menu {

    private static SaveMenu instance;

    private SaveMenu() {

    }

    public static SaveMenu getInstance() {
        if (instance == null) {
            instance = new SaveMenu();
        }
        return instance;
    }
}
