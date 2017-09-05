package game.menu;

public class MainMenu extends Menu {
    private static MainMenu instance;
    /**
     * ASCII text: http://patorjk.com/software/taag/
     * Title
     *     Font: Doom
     *     Character Width: Default
     *     Character Height: Default
     * Subtitle
     *     Font: Small
     *     Character Width: Default
     *     Character Height: Default
     * Scroll border: http://www.chris.com/ascii/index.php?art=art%20and%20design/borders
     */
    public static final int OPTIONS_SPACING = 2;
    public static final String[] START_GAME_OPTIONS = {"1","1.","start game","start","s"};
    public static final String[] HOW_TO_PLAY_OPTIONS = {"2","2.","how to play","how","h"};
    public static final String[] ABOUT_THIS_GAME_OPTIONS = {"3","3.","about this game","about","about game","a"};

    private MainMenu() {

    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    @Override
    public void outputPrompt() {
        outputTitleScreen();
        outputOptions();
    }
    /**
     * Traverse main menu
     */
    @Override
    public void processInput() {
        if (inputEquals(START_GAME_OPTIONS)) {
            changeToLoadMenu();
            // changeToTestMenu();
        } else if (inputEquals(HOW_TO_PLAY_OPTIONS)) {
            outputHowToPlay();
            outputOptions();
        } else if (inputEquals(ABOUT_THIS_GAME_OPTIONS)) {
            outputAboutThisGame();
            outputOptions();
        } else {
            outputInvalid();
            outputOptions();
        }
    }
    public void outputTitleScreen() {
        // Cheese Quest 2
        // outputRoom("                                                                                       .---.\n" +
        // "                                                                                      /  .  \\ \n" +
        // "                                                                                     |\\_/|   |\n" +
        // "                                                                                     |   |  /|\n" +
        // "  .----------------------------------------------------------------------------------------' |\n" +
        // " /  .-.     _____  _   _  _____ _____ _____ _____   _____ _   _ _____ _____ _____    _____   |\n" +
        // "|  /   \\   /  __ \\| | | ||  ___|  ___/  ___|  ___| |  _  | | | |  ___/  ___|_   _|  / __  \\  |\n" +
        // "| |\\_.  |  | /  \\/| |_| || |__ | |__ \\ `--.| |__   | | | | | | | |__ \\ `--.  | |    `' / /'  |\n" +
        // "|\\|  | /|  | |    |  _  ||  __||  __| `--. \\  __|  | | | | | | |  __| `--. \\ | |      / /    |\n" +
        // "| `---' |  | \\__/\\| | | || |___| |___/\\__/ / |___  \\ \\/' / |_| | |___/\\__/ / | |    ./ /___  |\n" +
        // "|       |   \\____/\\_| |_/\\____/\\____/\\____/\\____/   \\_/\\_\\\\___/\\____/\\____/  \\_/    \\_____/  |\n" +
        // "|       |            _____  _           ___  _                                __             |\n" +
        // "|       |           |_   _|| |_   ___  | _ \\| | __ _  __ _  _  _  ___   ___  / _|            |\n" +
        // "|       |             | |  | ' \\ / -_) |  _/| |/ _` |/ _` || || |/ -_) / _ \\|  _|            |\n" +
        // "|       |             |_|  |_||_|\\___| |_|  |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_|              |\n" +
        // "|       |                    _  __           _     _ |___/               _                   |\n" +
        // "|       |                   | |/ / __ _  ___| |_  | |__ __ _ __ __ __ _ | |                  |\n" +
        // "|       |                   | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || |                  |\n" +
        // "|       |                   |_|\\_\\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_|                  |\n" +
        // "|       |                                                                                   /\n" +
        // "|       |----------------------------------------------------------------------------------'\n" +
        // "\\       |\n" +
        // " \\     /");
        // Cheese Quest
        outputRoom("                                                                               .---.\n" +
            "                                                                              /  .  \\ \n" +
            "                                                                             |\\_/|   |\n" +
            "                                                                             |   |  /|\n" +
            "  .--------------------------------------------------------------------------------' |\n" +
            " /  .-.     _____  _   _  _____ _____ _____ _____   _____ _   _ _____ _____ _____    |\n" +
            "|  /   \\   /  __ \\| | | ||  ___|  ___/  ___|  ___| |  _  | | | |  ___/  ___|_   _|   |\n" +
            "| |\\_.  |  | /  \\/| |_| || |__ | |__ \\ `--.| |__   | | | | | | | |__ \\ `--.  | |     |\n" +
            "|\\|  | /|  | |    |  _  ||  __||  __| `--. \\  __|  | | | | | | |  __| `--. \\ | |     |\n" +
            "| `---' |  | \\__/\\| | | || |___| |___/\\__/ / |___  \\ \\/' / |_| | |___/\\__/ / | |     |\n" +
            "|       |   \\____/\\_| |_/\\____/\\____/\\____/\\____/   \\_/\\_\\\\___/\\____/\\____/  \\_/     |\n" +
            "|       |       _____  _           ___  _                                __          |\n" +
            "|       |      |_   _|| |_   ___  | _ \\| | __ _  __ _  _  _  ___   ___  / _|         |\n" +
            "|       |        | |  | ' \\ / -_) |  _/| |/ _` |/ _` || || |/ -_) / _ \\|  _|         |\n" +
            "|       |        |_|  |_||_|\\___| |_|  |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_|           |\n" +
            "|       |               _  __           _     _ |___/               _                |\n" +
            "|       |              | |/ / __ _  ___| |_  | |__ __ _ __ __ __ _ | |               |\n" +
            "|       |              | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || |               |\n" +
            "|       |              |_|\\_\\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_|               |\n" +
            "|       |                                                                           /\n" +
            "|       |--------------------------------------------------------------------------'\n" +
            "\\       |\n" +
            " \\     /");
        outputln("                          A Text Adventure Game");
        outputlnRoom("  `---'");
    }
    public void outputOptions() {
        outputln(OPTIONS_SPACING);
        output("                     ");
        outputPlayer("1");
        output(". Start Game        ");
        outputPlayer("2");
        output(". How to Play        ");
        outputPlayer("3");
        outputln(". About this Game");
    }
    public void outputHowToPlay() {
        outputln("Type in commands to do stuff. See what works and what doesn't.");
        outputln();
        outputlnItem("Examples:");
        outputln();
        outputPlayerInputNoSpace("drink eggnog");
        outputln("You drink the eggnog. It's like Christmas morning!");
        outputln();
        outputPlayerInputNoSpace("give catnip to kitten");
        outputln("You give the catnip to the kitten, making it go crazy.");
        outputln();
        outputShortcuts();
    }
    public void outputShortcuts() {
        outputlnItem("Basic shortcut commands:");
        outputPlayer("l");
        outputln(" - Look");
        outputPlayer("x");
        outputln(" - Examine");
        outputPlayer("i");
        outputln(" - Inventory");
        outputPlayer("h");
        outputln(" - Health/Hunger (stats)");
        outputPlayer("o");
        outputln(" - Current objective");
        outputPlayer("z");
        outputln(" - Wait");
        outputPlayer("g");
        outputln(" - Again (Repeat previous action)");
        outputln();
        outputlnItem("Movement:");
        outputPlayer("n");
        outputln(" - Go North");
        outputPlayer("e");
        outputln(" - Go East");
        outputPlayer("w");
        outputln(" - Go West");
        outputPlayer("s");
        outputln(" - Go South");
        outputPlayer("u");
        outputln(" - Go Up");
        outputPlayer("d");
        outputln(" - Go Down");
    }
    public void outputAboutThisGame() {
        outputItem("Cheese Quest 2");
        output(" started development in ");
        outputItem("August of 2017");
        output(". It was inspired by ");
        outputItem("The Hitchhiker's Guide to the Galaxy");
        output(" text adventure game developed by ");
        outputItem("Infocom");
        output(" in ");
        outputItem("1984");
        outputln(".");
    }
    public void outputInvalid() {
        output("Choose ");
        outputPlayer("Start Game");
        output(", ");
        outputPlayer("How to Play");
        output(", or ");
        outputPlayer("About this Game");
        outputln(".");
    }

}
