package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.SemanticColor;
import main.Main;
import util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

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
        startGameVerbs = new ArrayList<>(
                ArrayUtils.getArrayList(new String[] { "1", "1.", "start game", "start", "s" }));
        howToPlayVerbs = new ArrayList<>(
                ArrayUtils.getArrayList(new String[] { "2", "2.", "how to play", "how", "h" }));
        aboutThisGameVerbs = new ArrayList<>(ArrayUtils
                .getArrayList(new String[] { "3", "3.", "about this game", "about", "about game", "a" }));
        validVerbs = new ArrayList<>(
                Arrays.asList(startGameVerbs, howToPlayVerbs, aboutThisGameVerbs));
    }

    @Override
    protected void initializeCommands() {
        addCommand(new String[] {"1", "1.", "start game", "start", "s"}, () -> startGame());
        addCommand(new String[] {"2", "2.", "how to play", "how", "h"}, () -> startHowToPlay());
        addCommand(new String[] {"3", "3.", "about this game", "about", "about game", "a"}, () -> startAboutThisGame());

    }

    /**
     * To start the game, go the load menu to choose a game to start.
     */
    private void startGame() {
        changeTo(LoadMenu.getInstance());
    }

    /**
     * Print information about how to play this game and re-prompt options.
     */
    public void startHowToPlay() {
        outputHowToPlay();
        outputOptions();
    }

    /**
     * Print information about this game and re-prompt options.
     */
    public void startAboutThisGame() {
        outputAboutThisGame();
        outputOptions();
    }

    public void outputAboutThisGame() {
        out.appendln("Current version: " + Main.getVersion(), SemanticColor.TITLE);
        out.append("Cheese Quest 2", SemanticColor.ITEM);
        out.append(" started development in ");
        out.append("August of 2017", SemanticColor.ITEM);
        out.append(". It was inspired by ");
        out.append("The Hitchhiker's Guide to the Galaxy", SemanticColor.ITEM);
        out.append(" text adventure game published by ");
        out.append("Infocom", SemanticColor.ITEM);
        out.append(" in ");
        out.append("1984", SemanticColor.ITEM);
        out.append(".");
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
        out.append("Choose ");
        out.append("Start Game", SemanticColor.PLAYER);
        out.append(", ");
        out.append("How to Play", SemanticColor.PLAYER);
        out.append(", or ");
        out.append("About this Game", SemanticColor.PLAYER);
        out.append(".");
    }

    public void outputOptions() {
        out.appendlns(OPTIONS_SPACING);
        out.append("                ");
        out.append("1", SemanticColor.PLAYER);
        out.append(". Start Game        ");
        out.append("2", SemanticColor.PLAYER);
        out.append(". How to Play        ");
        out.append("3", SemanticColor.PLAYER);
        out.appendln(". About this Game");
    }

    public void outputPrompt() {
        outputTitleScreen();
        outputOptions();
    }

    public void outputShortcuts() {
        out.appendln("Basic shortcut commands:", SemanticColor.ITEM);
        out.append("l", SemanticColor.PLAYER);
        out.appendln(" - Look");
        out.append("x", SemanticColor.PLAYER);
        out.appendln(" - Examine");
        out.append("i", SemanticColor.PLAYER);
        out.appendln(" - Inventory");
        out.append("h", SemanticColor.PLAYER);
        out.appendln(" - Health/Hunger (stats)");
        out.append("o", SemanticColor.PLAYER);
        out.appendln(" - Current objective");
        out.append("z", SemanticColor.PLAYER);
        out.appendln(" - Wait");
        out.append("g", SemanticColor.PLAYER);
        out.appendln(" - Again (Repeat previous playerAction)");
        out.appendln();
        out.appendln("Movement:", SemanticColor.ITEM);
        out.append("n", SemanticColor.PLAYER);
        out.appendln(" - Go North");
        out.append("e", SemanticColor.PLAYER);
        out.appendln(" - Go East");
        out.append("w", SemanticColor.PLAYER);
        out.appendln(" - Go West");
        out.append("s", SemanticColor.PLAYER);
        out.appendln(" - Go South");
        out.append("u", SemanticColor.PLAYER);
        out.appendln(" - Go Up");
        out.append("d", SemanticColor.PLAYER);
        out.appendln(" - Go Down");
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
//                + "|       |              |_|\\_s\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_|               |\n"
//                + "|       |                                                                           /\n"
//                + "|       |--------------------------------------------------------------------------'\n"
//                + "\\       |\n" + " \\     /");
//        println("                          A Text Adventure Game");
//        printLocationln("  `---'");
    }

    /**
     * Process receiveInput that applies to inheritance
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
     *
     * @param playerCommand to processInput
     */
    @Override
    public void processInput(PlayerCommand playerCommand) {
        // Only care about matching the total string for simplicity.
        String command = playerCommand.getString().toLowerCase();
        if (commands.containsKey(command)) {
            commands.get(command).run();
        } else {

        }
    }
}
