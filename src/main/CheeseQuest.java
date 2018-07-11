package main;

import game.system.gui.MainFrame;

/**
 * Game driver
 *
 * @author Evan Quan
 * @version 0.10.0
 * @since March 4, 2018
 */
public class CheeseQuest {
    private static String version = "0.10.0";

    /**
     *
     * @return the current game version
     */
    public static String getVersion() {
        return version;
    }

    /**
     * Boots up the game
     *
     * @param args
     *            command line arguments
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
