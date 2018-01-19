package main;

import gui.MainFrame;

/**
 * Game driver
 * Boots up the game
 */
public class CheeseQuest {
    private static String version = "0.7.0";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

    public static String getVersion() {
        return version;
    }
}
