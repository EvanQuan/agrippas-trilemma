package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;
import game.system.output.SemanticColor;
import main.Main;

import java.util.Arrays;

/**
 * Starting menu. Allows the player to start playing, learn more about the game,
 * or quit the program.
 *
 * @author Evan Quan
 */
public class MainMenu extends Menu {
    private static MainMenu instance;

    private static final String[] START_GAME = {"1", "1.", "start game",
            "start", "s"};
    private static final String[] HOW_TO_PLAY = {"2", "2.", "how to play", "how", "h"};
    private static final String[] ABOUT_GAME = {"3", "3.", "about this game",
            "about", "about game", "a"};
    private static final String[] QUIT = {"4", "4.", "quit", "q"};
    /**
     * ASCII text: http://patorjk.com/software/taag/ Title Font: Doom Character
     * Width: Default Character Height: Default Subtitle Font: Small Character
     * Width: Default Character Height: Default Scroll border:
     * http://www.chris.com/ascii/index.php?art=art%20and%20design/borders
     */

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu() {
    }

    private void printHowToPlay() {
        out.println("Type in stringCommands to do stuff. See what works and" +
                "what doesn't. Trial and error is your friend.");
        out.println();
        out.println("Examples:");
        out.println();
        out.println(IPrintBuffer.INPUT_MARKER + "drink eggnog",
                SemanticColor.PLAYER);
        out.println("You drink the eggnog. It's like Christmas morning!");
        out.println();
        out.println(IPrintBuffer.INPUT_MARKER + "give catnip to the kitten",
                SemanticColor.PLAYER);
        out.println("You give the catnip to the kitten, making it go crazy.");
        out.println();
    }

    @Override
    protected void initializeCommands() {
        addCommand(stringCommands, START_GAME, () -> startGame());
        addCommand(stringCommands, HOW_TO_PLAY, () -> startHowToPlay());
        addCommand(stringCommands, ABOUT_GAME, () -> startAboutThisGame());
        addCommand(stringCommands, QUIT, () -> quitGame());
    }

    private void printAboutThisGame() {
        out.println("Current version: v" + Main.getVersion() + System.lineSeparator(), SemanticColor.TITLE);
        out.print("Agrippa's Trilemma", SemanticColor.ITEM);
        out.print(" started development in August of 2017. It was inspired by ");
        out.print("\"The Hitchhiker's Guide to the Galaxy\"", SemanticColor.SPEECH);
        out.print(" text adventure game published by Infocom in 1984. You can learn more about the game's history "
                + "and development at: ");
        out.println("https://github.com/EvanQuan/AgrippasTrilemma/wiki.");
    }

    private void printCommandShortcuts() {
        out.println("Basic shortcut commands:", SemanticColor.ITEM);
        out.print("l", SemanticColor.PLAYER);
        out.println(" - Look");
        out.print("x", SemanticColor.PLAYER);
        out.println(" - Examine");
        out.print("i", SemanticColor.PLAYER);
        out.println(" - Inventory");
        out.print("h", SemanticColor.PLAYER);
        out.println(" - Health/Hunger (stats)");
        out.print("o", SemanticColor.PLAYER);
        out.println(" - Current objective");
        out.print("z", SemanticColor.PLAYER);
        out.println(" - Wait");
        out.print("g", SemanticColor.PLAYER);
        out.println(" - Again (Repeat previous action)");
        out.println();
        out.println("Movement:", SemanticColor.ITEM);
        out.print("n", SemanticColor.PLAYER);
        out.println(" - Go North");
        out.print("e", SemanticColor.PLAYER);
        out.println(" - Go East");
        out.print("w", SemanticColor.PLAYER);
        out.println(" - Go West");
        out.print("s", SemanticColor.PLAYER);
        out.println(" - Go South");
        out.print("u", SemanticColor.PLAYER);
        out.println(" - Go Up");
        out.print("d", SemanticColor.PLAYER);
        out.println(" - Go Down");
    }

    /**
     * If command was invalid, explicitly prompt what
     */
    @Override
    protected void printInvalidInput() {
        out.print("Choose ");
        out.print("Start Game", SemanticColor.PLAYER);
        out.print(", ");
        out.print("How to Play", SemanticColor.PLAYER);
        out.print(", or ");
        out.print("About this Game", SemanticColor.PLAYER);
        out.print(".");
    }

    /**
     * Outputs the title screen and menu options.
     */
    @Override
    public void printMainPrompt() {
        printTitleScreen();
        printMenuOptions();
    }

    private void printMenuOptions() {
        out.printlns(OPTIONS_SPACING);
        out.print("1", SemanticColor.PLAYER);
        out.print(". Start Game    ");
        out.print("2", SemanticColor.PLAYER);
        out.print(". How to Play    ");
        out.print("3", SemanticColor.PLAYER);
        out.print(". About this Game    ");
        out.print("4", SemanticColor.PLAYER);
        out.println(". Quit");
    }

    private void printTitleScreen() {
        out.println("        Welcome to...");
        out.print(
                "          ___             _                   _" + System.lineSeparator()
                        + "         / _ \\           (_)                 ( )" + System.lineSeparator()
                        + "        / /_\\ \\ __ _ _ __ _ _ __  _ __   __ _|/ ___" + System.lineSeparator()
                        + "        |  _  |/ _` | '__| | '_ \\| '_ \\ / _` | / __|" + System.lineSeparator()
                        + "        | | | | (_| | |  | | |_) | |_) | (_| | \\__ \\" + System.lineSeparator()
                        + "        \\_| |_/\\__, |_|  |_| .__/| .__/ \\__,_| |___/" + System.lineSeparator()
                        + "                __/ |      | |   | |" + System.lineSeparator()
                        + "               |___/       |_|   |_|" + System.lineSeparator() + "         _____    _ _"
                        + System.lineSeparator() + "        |_   _|  (_) |" + System.lineSeparator()
                        + "          | |_ __ _| | ___ _ __ ___  _ __ ___   __ _" + System.lineSeparator()
                        + "          | | '__| | |/ _ \\ '_ ` _ \\| '_ ` _ \\ / _` |" + System.lineSeparator()
                        + "          | | |  | | |  __/ | | | | | | | | | | (_| |" + System.lineSeparator()
                        + "          \\_/_|  |_|_|\\___|_| |_| |_|_| |_| |_|\\__,_|" + System.lineSeparator(),
                SemanticColor.ITEM);
        out.println();
        out.println("                    A Text Adventure Game");
        out.println();
        // Cheese Quest
        // printLocation(" .---.\n"
        // + " / . \\ \n"
        // + " |\\_/| |\n"
        // + " | | /|\n"
        // + "
        // .--------------------------------------------------------------------------------'
        // |\n"
        // + " / .-. _____ _ _ _____ _____ _____ _____ _____ _ _ _____ _____ _____ |\n"
        // + "| / \\ / __ \\| | | || ___| ___/ ___| ___| | _ | | | | ___/ ___|_ _| |\n"
        // + "| |\\_. | | / \\/| |_| || |__ | |__ \\ `--.| |__ | | | | | | | |__ \\ `--.
        // | | |\n"
        // + "|\\| | /| | | | _ || __|| __| `--. \\ __| | | | | | | | __| `--. \\ | |
        // |\n"
        // + "| `---' | | \\__/\\| | | || |___| |___/\\__/ / |___ \\ \\/' / |_| |
        // |___/\\__/ / | | |\n"
        // + "| | \\____/\\_| |_/\\____/\\____/\\____/\\____/
        // \\_/\\_\\\\___/\\____/\\____/ \\_/ |\n"
        // + "| | _____ _ ___ _ __ |\n"
        // + "| | |_ _|| |_ ___ | _ \\| | __ _ __ _ _ _ ___ ___ / _| |\n"
        // + "| | | | | ' \\ / -_) | _/| |/ _` |/ _` || || |/ -_) / _ \\| _| |\n"
        // + "| | |_| |_||_|\\___| |_| |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_| |\n"
        // + "| | _ __ _ _ |___/ _ |\n"
        // + "| | | |/ / __ _ ___| |_ | |__ __ _ __ __ __ _ | | |\n"
        // + "| | | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || | |\n"
        // + "| | |_|\\_s\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_| |\n"
        // + "| | /\n"
        // + "|
        // |--------------------------------------------------------------------------'\n"
        // + "\\ |\n" + " \\ /");
        // println(" A Text Adventure Game");
        // printLocationln(" `---'");
    }

    /**
     * Every command that does not start the game is sandwiched between the
     * title screen and the menu options. This checks that the input is not
     * starting the game, and if it
     */
    @Override
    protected void preProcessInput() {
        if (!Arrays.asList(START_GAME).contains(thisCommand.getString().toLowerCase())) {
            printTitleScreen();
        }
    }

    @Override
    protected void processInput() {
        // Only care about matching the total string for simplicity.
        String command = thisCommand.getString().toLowerCase();
        if (stringCommands.containsKey(command)) {
            stringCommands.get(command).run();
        } else {
            printInvalidInput();
        }
    }

    @Override
    protected void postProcessInput() {
        if (!Arrays.asList(START_GAME).contains(thisCommand.getString().toLowerCase())) {
            printMenuOptions();
        }
    }

    /**
     * By setting MenuManger's current menu to null, {@link
     * game.system.console.GameLoop} should end the program.
     */
    private void quitGame() {
        MenuManager.setCurrentMenu(null);
    }

    /**
     * Print information about this game and re-prompt options.
     */
    private void startAboutThisGame() {
        printTitleScreen();
        printAboutThisGame();
        printMenuOptions();
    }

    /**
     * If there are already existing game saves, go to the load menu. Otherwise,
     * immediately start a new game.
     */
    private void startGame() {
        changeTo(LoadMenu.getInstance());
    }

    /**
     * Print information about how to play this game and re-prompt options.
     */
    private void startHowToPlay() {
        printTitleScreen();
        printHowToPlay();
        printCommandShortcuts();
        printMenuOptions();
    }
}
