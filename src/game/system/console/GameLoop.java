package game.system.console;

import game.menu.MainMenu;
import game.menu.Menu;
import game.menu.MenuManager;
import game.system.input.PlayerInputParser;
import game.system.output.ConsolePrintBuffer;
import game.system.output.IPrintBuffer;
import game.system.output.SemanticColor;

import java.util.Currency;
import java.util.Scanner;

/**
 * Overlooks the whole game. Initializes and contains all game logic components to be used in the console.
 */
public class GameLoop {

    private ConsolePrintBuffer out = new ConsolePrintBuffer();

    /**
     * Default constructor. Sets up all game components to be used in the console.
     */
    public GameLoop() {
        Menu.setOut(out);  // Set output to console
    }

    /**
     * Start the game.
     */
    public void start() {
        Scanner in = new Scanner(System.in);
        MenuManager.setCurrentMenu(MainMenu.getInstance());
        while(MenuManager.hasCurrentMenu()) {
            out.append(IPrintBuffer.INPUT_MARKER, SemanticColor.PLAYER);
            MenuManager.receiveInput(PlayerInputParser.parse(in.nextLine()));
        }
    }
}
