package game.system.console;

import game.menu.MainMenu;
import game.menu.Menu;
import game.menu.MenuManager;
import game.system.input.PlayerInputParser;
import game.system.output.ConsolePrintBuffer;
import game.system.output.IPrintBuffer;

import java.util.Scanner;

/**
 * Overlooks the whole game. Initializes and contains all game logic components to be used in the console.
 */
public class GameLoop {

    private IPrintBuffer out;

    /**
     * Default constructor. Sets up all game components to be used in the console.
     */
    public GameLoop() {
        Menu.setOut(new ConsolePrintBuffer());  // Set output to console
    }

    /**
     * Start the game.
     */
    public void start() {
        Scanner in = new Scanner(System.in);
        System.out.println("Start");
        MenuManager.setCurrentMenu(MainMenu.getInstance());
        System.out.println("Main menu set");
        while(MenuManager.hasCurrentMenu()) {
            System.out.println("Start loop...");
            MenuManager.receiveInput(PlayerInputParser.parse(in.nextLine()));
            System.out.println("End loop...");
        }
    }
}
