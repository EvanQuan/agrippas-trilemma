package game.menu;

import java.util.ArrayList;
import java.util.Arrays;

import main.CheeseQuest;
import util.ArrayUtils;

public class MainMenu extends Menu {
    private static MainMenu instance;
    /**
     * ASCII text: http://patorjk.com/software/taag/ Title Font: Doom Character
     * Width: Default Character Height: Default Subtitle Font: Small Character
     * Width: Default Character Height: Default Scroll border:
     * http://www.chris.com/ascii/index.php?art=art%20and%20design/borders
     */
    public static final int OPTIONS_SPACING = 2;

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private ArrayList<String> startGameVerbs;
    private ArrayList<String> howToPlayVerbs;
    private ArrayList<String> aboutThisGameVerbs;

    private MainMenu() {
        startGameVerbs = new ArrayList<String>(
                ArrayUtils.getArrayList(new String[] { "1", "1.", "start game", "start", "s" }));
        howToPlayVerbs = new ArrayList<String>(
                ArrayUtils.getArrayList(new String[] { "2", "2.", "how to play", "how", "h" }));
        aboutThisGameVerbs = new ArrayList<String>(ArrayUtils
                .getArrayList(new String[] { "3", "3.", "about this game", "about", "about game", "a" }));
        validVerbs = new ArrayList<ArrayList>(
                Arrays.asList(new ArrayList[] { startGameVerbs, howToPlayVerbs, aboutThisGameVerbs }));
    }

    public void outputAboutThisGame() {
//        printTitleln("Current version: " + CheeseQuest.getVersion());
//        printItem("Cheese Quest 2");
//        print(" started development in ");
//        printItem("August of 2017");
//        print(". It was inspired by ");
//        printItem("The Hitchhiker's Guide to the Galaxy");
//        print(" text adventure game published by ");
//        printItem("Infocom");
//        print(" in ");
//        printItem("1984");
//        println(".");
    }

    public void outputHowToPlay() {
//        println("Type in commands to do stuff. See what works and what doesn't.");
//        println();
//        printItemln("Examples:");
//        println();
//        printPlayerInputNoSpace("drink eggnog");
//        println("You drink the eggnog. It's like Christmas morning!");
//        println();
//        printPlayerInputNoSpace("give catnip to kitten");
//        println("You give the catnip to the kitten, making it go crazy.");
//        println();
        outputShortcuts();
    }

    public void outputInvalid() {
//        print("Choose ");
//        printPlayer("Start Game");
//        print(", ");
//        printPlayer("How to Play");
//        print(", or ");
//        printPlayer("About this Game");
//        println(".");
    }

    public void outputOptions() {
//        printlns(OPTIONS_SPACING);
//        print("                ");
//        printPlayer("1");
//        print(". Start Game        ");
//        printPlayer("2");
//        print(". How to Play        ");
//        printPlayer("3");
//        println(". About this Game");
    }

    public void outputPrompt() {
        outputTitleScreen();
        outputOptions();
    }

    public void outputShortcuts() {
//        printItemln("Basic shortcut commands:");
//        printPlayer("l");
//        println(" - Look");
//        printPlayer("x");
//        println(" - Examine");
//        printPlayer("i");
//        println(" - Inventory");
//        printPlayer("h");
//        println(" - Health/Hunger (stats)");
//        printPlayer("o");
//        println(" - Current objective");
//        printPlayer("z");
//        println(" - Wait");
//        printPlayer("g");
//        println(" - Again (Repeat previous playerAction)");
//        println();
//        printItemln("Movement:");
//        printPlayer("n");
//        println(" - Go North");
//        printPlayer("e");
//        println(" - Go East");
//        printPlayer("w");
//        println(" - Go West");
//        printPlayer("s");
//        println(" - Go South");
//        printPlayer("u");
//        println(" - Go Up");
//        printPlayer("d");
//        println(" - Go Down");
    }

    public void outputTitleScreen() {
        // Cheese Quest 2
        // outputRoom(" .---.\n" +
        // " / . \\ \n" +
        // " |\\_/| |\n" +
        // " | | /|\n" +
        // "
        // .----------------------------------------------------------------------------------------'
        // |\n" +
        // " / .-. _____ _ _ _____ _____ _____ _____ _____ _ _ _____ _____ _____ _____
        // |\n" +
        // "| / \\ / __ \\| | | || ___| ___/ ___| ___| | _ | | | | ___/ ___|_ _| / __ \\
        // |\n" +
        // "| |\\_. | | / \\/| |_| || |__ | |__ \\ `--.| |__ | | | | | | | |__ \\ `--. |
        // | `' / /' |\n" +
        // "|\\| | /| | | | _ || __|| __| `--. \\ __| | | | | | | | __| `--. \\ | | / /
        // |\n" +
        // "| `---' | | \\__/\\| | | || |___| |___/\\__/ / |___ \\ \\/' / |_| |
        // |___/\\__/ / | | ./ /___ |\n" +
        // "| | \\____/\\_| |_/\\____/\\____/\\____/\\____/
        // \\_/\\_\\\\___/\\____/\\____/ \\_/ \\_____/ |\n" +
        // "| | _____ _ ___ _ __ |\n" +
        // "| | |_ _|| |_ ___ | _ \\| | __ _ __ _ _ _ ___ ___ / _| |\n" +
        // "| | | | | ' \\ / -_) | _/| |/ _` |/ _` || || |/ -_) / _ \\| _| |\n" +
        // "| | |_| |_||_|\\___| |_| |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_| |\n" +
        // "| | _ __ _ _ |___/ _ |\n" +
        // "| | | |/ / __ _ ___| |_ | |__ __ _ __ __ __ _ | | |\n" +
        // "| | | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || | |\n" +
        // "| | |_|\\_\\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_| |\n" +
        // "| | /\n" +
        // "|
        // |----------------------------------------------------------------------------------'\n"
        // +
        // "\\ |\n" +
        // " \\ /");
        // Cheese Quest
//        printLocation("                                                                               .---.\n"
//                + "                                                                              /  .  \\ \n"
//                + "                                                                             |\\_/|   |\n"
//                + "                                                                             |   |  /|\n"
//                + "  .--------------------------------------------------------------------------------' |\n"
//                + " /  .-.     _____  _   _  _____ _____ _____ _____   _____ _   _ _____ _____ _____    |\n"
//                + "|  /   \\   /  __ \\| | | ||  ___|  ___/  ___|  ___| |  _  | | | |  ___/  ___|_   _|   |\n"
//                + "| |\\_.  |  | /  \\/| |_| || |__ | |__ \\ `--.| |__   | | | | | | | |__ \\ `--.  | |     |\n"
//                + "|\\|  | /|  | |    |  _  ||  __||  __| `--. \\  __|  | | | | | | |  __| `--. \\ | |     |\n"
//                + "| `---' |  | \\__/\\| | | || |___| |___/\\__/ / |___  \\ \\/' / |_| | |___/\\__/ / | |     |\n"
//                + "|       |   \\____/\\_| |_/\\____/\\____/\\____/\\____/   \\_/\\_\\\\___/\\____/\\____/  \\_/     |\n"
//                + "|       |       _____  _           ___  _                                __          |\n"
//                + "|       |      |_   _|| |_   ___  | _ \\| | __ _  __ _  _  _  ___   ___  / _|         |\n"
//                + "|       |        | |  | ' \\ / -_) |  _/| |/ _` |/ _` || || |/ -_) / _ \\|  _|         |\n"
//                + "|       |        |_|  |_||_|\\___| |_|  |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_|           |\n"
//                + "|       |               _  __           _     _ |___/               _                |\n"
//                + "|       |              | |/ / __ _  ___| |_  | |__ __ _ __ __ __ _ | |               |\n"
//                + "|       |              | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || |               |\n"
//                + "|       |              |_|\\_\\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_|               |\n"
//                + "|       |                                                                           /\n"
//                + "|       |--------------------------------------------------------------------------'\n"
//                + "\\       |\n" + " \\     /");
//        println("                          A Text Adventure Game");
//        printLocationln("  `---'");
    }

    /**
     * Process input that applies to inheritance
     */
    public void preProcessInput() {
//        if (inputEquals(EMPTY)) {
//            println("I beg your pardon?");
//            outputOptions();
//        } else {
//            processInput();
//        }
    }

    /**
     * Traverse main menu
     */
    public void processInput() {
//        if (inputEquals(startGameVerbs)) {
//            changeToLoadMenu();
//            // changeToTestMenu();
//        } else if (inputEquals(howToPlayVerbs)) {
//            outputHowToPlay();
//            outputOptions();
//        } else if (inputEquals(aboutThisGameVerbs)) {
//            outputAboutThisGame();
//            outputOptions();
//        } else {
//            outputInvalid();
//            outputOptions();
//        }
    }

}
