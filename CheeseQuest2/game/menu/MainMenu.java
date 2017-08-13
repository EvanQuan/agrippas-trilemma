package game.menu;

public class MainMenu extends Menu {
    private static MainMenu menu = new MainMenu();
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
    public static final String TITLE_SCREEN =
    "                                                                                       .---.\n" +
    "                                                                                      /  .  \\ \n" +
    "                                                                                     |\\_/|   |\n" +
    "                                                                                     |   |  /|\n" +
    "  .----------------------------------------------------------------------------------------' |\n" +
    " /  .-.     _____  _   _  _____ _____ _____ _____   _____ _   _ _____ _____ _____    _____   |\n" +
    "|  /   \\   /  __ \\| | | ||  ___|  ___/  ___|  ___| |  _  | | | |  ___/  ___|_   _|  / __  \\  |\n" +
    "| |\\_.  |  | /  \\/| |_| || |__ | |__ \\ `--.| |__   | | | | | | | |__ \\ `--.  | |    `' / /'  |\n" +
    "|\\|  | /|  | |    |  _  ||  __||  __| `--. \\  __|  | | | | | | |  __| `--. \\ | |      / /    |\n" +
    "| `---' |  | \\__/\\| | | || |___| |___/\\__/ / |___  \\ \\/' / |_| | |___/\\__/ / | |    ./ /___  |\n" +
    "|       |   \\____/\\_| |_/\\____/\\____/\\____/\\____/   \\_/\\_\\\\___/\\____/\\____/  \\_/    \\_____/  |\n" +
    "|       |            _____  _           ___  _                                __             |\n" +
    "|       |           |_   _|| |_   ___  | _ \\| | __ _  __ _  _  _  ___   ___  / _|            |\n" +
    "|       |             | |  | ' \\ / -_) |  _/| |/ _` |/ _` || || |/ -_) / _ \\|  _|            |\n" +
    "|       |             |_|  |_||_|\\___| |_|  |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_|              |\n" +
    "|       |                    _  __           _     _ |___/               _                   |\n" +
    "|       |                   | |/ / __ _  ___| |_  | |__ __ _ __ __ __ _ | |                  |\n" +
    "|       |                   | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || |                  |\n" +
    "|       |                   |_|\\_\\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_|                  |\n" +
    "|       |                                                                                   /\n" +
    "|       |----------------------------------------------------------------------------------'\n" +
    "\\       |\n" +
    " \\     /                                 A Text Adventure Game\n" +
    "  `---'\n" +
    "\n";
    public static final String OPTIONS_PROMPT =
    "           1. Start Game        2. How to Play        3. About this Game        4. Quit\n";
    public static final int OPTIONS_SPACING = 2;
    public static final String SHORTCUTS =
    "l - Look" + "\n" +
    "x - Examine" + "\n" +
    "i - Inventory" + "\n" +
    "h - Health/Hunger (stats)" + "\n" +
    "o - Current objective" + "\n" +
    "z - Wait" + "\n" +
    "g - Again (Repeat previous action)" + "\n\n" +
    "Movement:" + "\n" +
    "n - Go North" + "\n" +
    "e - Go East" + "\n" +
    "w - Go West" + "\n" +
    "s - Go South" + "\n" +
    "u - Go Up" + "\n" +
    "d - Go Down";

    public static final String[] START_GAME_OPTIONS = {"1","start game","start","s"};
    public static final String[] HOW_TO_PLAY_OPTIONS = {"2","how to play","how","h"};
    public static final String HOW_TO_PLAY =
    "Type in commands to do stuff. See what works and what doesn't." + "\n\n" +
    "Examples:" + "\n\n" +
    "> drink eggnog" + "\n" +
    "You drink the eggnog. It's like Christmas morning!" + "\n\n" +
    "> give catnip to kitten" + "\n" +
    "You give the catnip to the kitten, making it go crazy." + "\n\n\n" +
    "Besides that, there are some basic shortcut commands:" + "\n\n" +
    SHORTCUTS;
    public static final String[] ABOUT_THIS_GAME_OPTIONS = {"3","about this game","about","about game","a"};
    public static final String ABOUT_THIS_GAME = "Cheese Quest 2 is in development in Java starting 2017, and attempts to correct a lot of the mistakes made in Cheese Quest 1, both in terms of code maintenance and scalability, and game mechanics/balance. Two main features to implement is the ability to save and load multiple game saves, and for it to run in a GUI using javax.swing and java.awt, with added buttons, and side inventory and goal displays for added convenience.";
    public static final String[] QUIT_OPTIONS = {"4","quit","q"};
    public static final String INVALID_PROMPT = "Choose \"Start Game\", \"How to Play\", \"About this Game\", or \"Quit\"";

    private MainMenu() {
        outputln(TITLE_SCREEN);
        outputln(OPTIONS_PROMPT);
    }

    public static MainMenu getInstance() {
        return menu;
    }

    /**
     * Traverse main menu
     */
    @Override
    public void processInput() {
        if (inputEquals(START_GAME_OPTIONS)) {
            changeToLoadMenu();
        } else if (inputEquals(HOW_TO_PLAY_OPTIONS)) {
            outputln(HOW_TO_PLAY);
            outputln(OPTIONS_SPACING);
            outputln(OPTIONS_PROMPT);
        } else if (inputEquals(ABOUT_THIS_GAME_OPTIONS)) {
            outputln(ABOUT_THIS_GAME);
            outputln(OPTIONS_SPACING);
            outputln(OPTIONS_PROMPT);
        } else if (inputEquals(QUIT_OPTIONS)) {
            exit();
        } else {
            outputln(INVALID_PROMPT);
        }
    }

}
