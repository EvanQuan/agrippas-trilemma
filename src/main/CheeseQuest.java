package main;

import gui.MainFrame;

/**
 * Game driver
 *
 * @author Evan Quan
 */
public class CheeseQuest {
    private static String version = "0.7.0";

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
