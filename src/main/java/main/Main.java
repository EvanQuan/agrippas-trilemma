package main;

import game.system.console.GameLoop;
import game.system.gui.MainFrame;

/**
 * Game driver
 *
 * @author Evan Quan
 * @version 0.10.0
 */
public class Main {
    private static String version = "0.11.0";

    /**
     * This is used for setting the game version when game saves are made.
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
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        args = new String[] {"-c"};  // Debug for now
        if (args.length != 1) {
            help();
        } else if (args[0].equals("-c")) {
            GameLoop loop = new GameLoop();
            loop.start();
        } else if (args[0].equals("-g")) {
            MainFrame frame = new MainFrame();
        } else {
            help();
        }
    }

    private static void help() {
        System.out.println("Agripp'a Trilemma v" + version + System.lineSeparator()
                            + "Usage: java Main [args...]" + System.lineSeparator()
                            + "Argument options:" + System.lineSeparator()
                            + "\t-c"
                            + "\t\tOpen the game in the console."
                            + "\t-g"
                            + "t\tOpen the game in a GUI.");
    }
}
