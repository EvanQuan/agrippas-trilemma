package game.system;

/**
 * You can do standard output in color! That's pretty neat! Credited to:
 * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 *
 * @author Evan Quan
 *
 */
public class ConsoleColors {

    // Reset
    public static final String RESET = "\033[0m"; // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";
    public static final String RED_UNDERLINED = "\033[4;31m";
    public static final String GREEN_UNDERLINED = "\033[4;32m";
    public static final String YELLOW_UNDERLINED = "\033[4;33m";
    public static final String BLUE_UNDERLINED = "\033[4;34m";
    public static final String PURPLE_UNDERLINED = "\033[4;35m";
    public static final String CYAN_UNDERLINED = "\033[4;36m";
    public static final String WHITE_UNDERLINED = "\033[4;37m";

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String PURPLE_BACKGROUND = "\033[45m";
    public static final String CYAN_BACKGROUND = "\033[46m";
    public static final String WHITE_BACKGROUND = "\033[47m";

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";
    public static final String RED_BRIGHT = "\033[0;91m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String PURPLE_BRIGHT = "\033[0;95m";
    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String WHITE_BRIGHT = "\033[0;97m";

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";

    /**
     * Test print colors
     */
    public static void printTest() {
        System.out.println("Normal");
        System.out.println(BLACK + "Black" + RESET);
        System.out.println(BLACK_BOLD + "Black bold" + RESET);
        System.out.println(BLACK_UNDERLINED + "Black underlined" + RESET);
        System.out.println(BLACK_BACKGROUND + "Black background" + RESET);
        System.out.println(BLACK_BRIGHT + "Black bright" + RESET);
        System.out.println(BLACK_BOLD_BRIGHT + "Black bold bright" + RESET);
        System.out.println(BLACK_BACKGROUND_BRIGHT + "Black background bright" + RESET);

        System.out.println(RED + "Red" + RESET);
        System.out.println(RED_BOLD + "Red bold" + RESET);
        System.out.println(RED_UNDERLINED + "Red underlined" + RESET);
        System.out.println(RED_BACKGROUND + "Red background" + RESET);
        System.out.println(RED_BRIGHT + "Red bright" + RESET);
        System.out.println(RED_BOLD_BRIGHT + "Red bold bright" + RESET);
        System.out.println(RED_BACKGROUND_BRIGHT + "Red background bright" + RESET);

        System.out.println(GREEN + "Green" + RESET);
        System.out.println(GREEN_BOLD + "Green bold" + RESET);
        System.out.println(GREEN_UNDERLINED + "Green underlined" + RESET);
        System.out.println(GREEN_BACKGROUND + "Green background" + RESET);
        System.out.println(GREEN_BRIGHT + "Green bright" + RESET);
        System.out.println(GREEN_BOLD_BRIGHT + "Green bold bright" + RESET);
        System.out.println(GREEN_BACKGROUND_BRIGHT + "Green background bright" + RESET);

        System.out.println(YELLOW + "Yellow" + RESET);
        System.out.println(YELLOW_BOLD + "Yellow bold" + RESET);
        System.out.println(YELLOW_UNDERLINED + "Yellow underlined" + RESET);
        System.out.println(YELLOW_BACKGROUND + "Yellow background" + RESET);
        System.out.println(YELLOW_BRIGHT + "Yellow bright" + RESET);
        System.out.println(YELLOW_BOLD_BRIGHT + "Yellow bold bright" + RESET);
        System.out.println(YELLOW_BACKGROUND_BRIGHT + "Yellow background bright" + RESET);

        System.out.println(BLUE + "Blue" + RESET);
        System.out.println(BLUE_BOLD + "Blue bold" + RESET);
        System.out.println(BLUE_UNDERLINED + "Blue underlined" + RESET);
        System.out.println(BLUE_BACKGROUND + "Blue background" + RESET);
        System.out.println(BLUE_BRIGHT + "Blue bright" + RESET);
        System.out.println(BLUE_BOLD_BRIGHT + "Blue bold bright" + RESET);
        System.out.println(BLUE_BACKGROUND_BRIGHT + "Blue background bright" + RESET);

        System.out.println(PURPLE + "Purple" + RESET);
        System.out.println(PURPLE_BOLD + "Purple bold" + RESET);
        System.out.println(PURPLE_UNDERLINED + "Purple underlined" + RESET);
        System.out.println(PURPLE_BACKGROUND + "Purple background" + RESET);
        System.out.println(PURPLE_BRIGHT + "Purple bright" + RESET);
        System.out.println(PURPLE_BOLD_BRIGHT + "Purple bold bright" + RESET);
        System.out.println(PURPLE_BACKGROUND_BRIGHT + "Purple background bright" + RESET);

        System.out.println(CYAN + "Cyan" + RESET);
        System.out.println(CYAN_BOLD + "Cyan bold" + RESET);
        System.out.println(CYAN_UNDERLINED + "Cyan underlined" + RESET);
        System.out.println(CYAN_BACKGROUND + "Cyan background" + RESET);
        System.out.println(CYAN_BRIGHT + "Cyan bright" + RESET);
        System.out.println(CYAN_BOLD_BRIGHT + "Cyan bold bright" + RESET);
        System.out.println(CYAN_BACKGROUND_BRIGHT + "Cyan background bright" + RESET);

        System.out.println(WHITE + "White" + RESET);
        System.out.println(WHITE_BOLD + "White bold" + RESET);
        System.out.println(WHITE_UNDERLINED + "White underlined" + RESET);
        System.out.println(WHITE_BACKGROUND + "White background" + RESET);
        System.out.println(WHITE_BRIGHT + "White bright" + RESET);
        System.out.println(WHITE_BOLD_BRIGHT + "White bold bright" + RESET);
        System.out.println(WHITE_BACKGROUND_BRIGHT + "White background bright" + RESET);

    }
}
