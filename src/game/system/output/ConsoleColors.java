package game.system.output;

/**
 * You can do standard output in color! That's pretty neat! Credited to:
 * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 *
 * @author Evan Quan
 *
 */
public enum ConsoleColors {

    // Reset
    RESET("\033[0m"), // Text reset to default color

    // Regular Colors
    BLACK("\033[0;30m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m"),

    // Bold
    BLACK_BOLD("\033[1;30m"),
    RED_BOLD("\033[1;31m"),
    GREEN_BOLD("\033[1;32m"),
    YELLOW_BOLD("\033[1;33m"),
    BLUE_BOLD("\033[1;34m"),
    PURPLE_BOLD("\033[1;35m"),
    CYAN_BOLD("\033[1;36m"),
    WHITE_BOLD("\033[1;37m"),

    // Underline
    BLACK_UNDERLINED("\033[4;30m"),
    RED_UNDERLINED("\033[4;31m"),
    GREEN_UNDERLINED("\033[4;32m"),
    YELLOW_UNDERLINED("\033[4;33m"),
    BLUE_UNDERLINED("\033[4;34m"),
    PURPLE_UNDERLINED("\033[4;35m"),
    CYAN_UNDERLINED("\033[4;36m"),
    WHITE_UNDERLINED("\033[4;37m"),

    // Background
    BLACK_BACKGROUND("\033[40m"),
    RED_BACKGROUND("\033[41m"),
    GREEN_BACKGROUND("\033[42m"),
    YELLOW_BACKGROUND("\033[43m"),
    BLUE_BACKGROUND("\033[44m"),
    PURPLE_BACKGROUND("\033[45m"),
    CYAN_BACKGROUND("\033[46m"),
    WHITE_BACKGROUND("\033[47m"),

    // High Intensity
    BLACK_BRIGHT("\033[0;90m"),
    RED_BRIGHT("\033[0;91m"),
    GREEN_BRIGHT("\033[0;92m"),
    YELLOW_BRIGHT("\033[0;93m"),
    BLUE_BRIGHT("\033[0;94m"),
    PURPLE_BRIGHT("\033[0;95m"),
    CYAN_BRIGHT("\033[0;96m"),
    WHITE_BRIGHT("\033[0;97m"),

    // Bold High Intensity
    BLACK_BOLD_BRIGHT("\033[1;90m"),
    RED_BOLD_BRIGHT("\033[1;91m"),
    GREEN_BOLD_BRIGHT("\033[1;92m"),
    YELLOW_BOLD_BRIGHT("\033[1;93m"),
    BLUE_BOLD_BRIGHT("\033[1;94m"),
    PURPLE_BOLD_BRIGHT("\033[1;95m"),
    CYAN_BOLD_BRIGHT("\033[1;96m"),
    WHITE_BOLD_BRIGHT("\033[1;97m"),

    // High Intensity backgrounds
    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),
    RED_BACKGROUND_BRIGHT("\033[0;101m"),
    GREEN_BACKGROUND_BRIGHT("\033[0;102m"),
    YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),
    BLUE_BACKGROUND_BRIGHT("\033[0;104m"),
    PURPLE_BACKGROUND_BRIGHT("\033[0;105m"),
    CYAN_BACKGROUND_BRIGHT("\033[0;106m"),
    WHITE_BACKGROUND_BRIGHT("\033[0;107m");

    private final String text;

    ConsoleColors(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static void main(String[] args) {
        printFor();
//        printEach();
//        printAll();
    }

    /**
     * Iteratively append all colors
     */
    public static void printEach() {
        for (ConsoleColors color : ConsoleColors.values()) {
            System.out.println(color + color.name() + RESET);
        }
    }

    public static void printFor() {
        ConsoleColors[] colors = ConsoleColors.values();
        for (int i = 0; i < colors.length; i++) {
            System.out.println(i + "." + colors[i] + colors[i].name() + RESET);
        }
    }
    /**
     * Hardcode append all colors
     */
    public static void printAll() {
        System.out.println("Black");
        System.out.println(BLACK + "Black" + RESET);
        System.out.println(BLACK_BOLD + "Black bold" + RESET);
        System.out.println(BLACK_UNDERLINED + "Black underlined" + RESET);
        System.out.println(BLACK_BACKGROUND + "Black background" + RESET);
        System.out.println(BLACK_BRIGHT + "Black bright" + RESET);
        System.out.println(BLACK_BOLD_BRIGHT + "Black bold bright" + RESET);
        System.out.println(BLACK_BACKGROUND_BRIGHT + "Black background bright" + RESET);

        System.out.println("Red");
        System.out.println(RED + "Red" + RESET);
        System.out.println(RED_BOLD + "Red bold" + RESET);
        System.out.println(RED_UNDERLINED + "Red underlined" + RESET);
        System.out.println(RED_BACKGROUND + "Red background" + RESET);
        System.out.println(RED_BRIGHT + "Red bright" + RESET);
        System.out.println(RED_BOLD_BRIGHT + "Red bold bright" + RESET);
        System.out.println(RED_BACKGROUND_BRIGHT + "Red background bright" + RESET);

        System.out.println("Green");
        System.out.println(GREEN + "Green" + RESET);
        System.out.println(GREEN_BOLD + "Green bold" + RESET);
        System.out.println(GREEN_UNDERLINED + "Green underlined" + RESET);
        System.out.println(GREEN_BACKGROUND + "Green background" + RESET);
        System.out.println(GREEN_BRIGHT + "Green bright" + RESET);
        System.out.println(GREEN_BOLD_BRIGHT + "Green bold bright" + RESET);
        System.out.println(GREEN_BACKGROUND_BRIGHT + "Green background bright" + RESET);

        System.out.println("Yellow");
        System.out.println(YELLOW + "Yellow" + RESET);
        System.out.println(YELLOW_BOLD + "Yellow bold" + RESET);
        System.out.println(YELLOW_UNDERLINED + "Yellow underlined" + RESET);
        System.out.println(YELLOW_BACKGROUND + "Yellow background" + RESET);
        System.out.println(YELLOW_BRIGHT + "Yellow bright" + RESET);
        System.out.println(YELLOW_BOLD_BRIGHT + "Yellow bold bright" + RESET);
        System.out.println(YELLOW_BACKGROUND_BRIGHT + "Yellow background bright" + RESET);

        System.out.println("Blue");
        System.out.println(BLUE + "Blue" + RESET);
        System.out.println(BLUE_BOLD + "Blue bold" + RESET);
        System.out.println(BLUE_UNDERLINED + "Blue underlined" + RESET);
        System.out.println(BLUE_BACKGROUND + "Blue background" + RESET);
        System.out.println(BLUE_BRIGHT + "Blue bright" + RESET);
        System.out.println(BLUE_BOLD_BRIGHT + "Blue bold bright" + RESET);
        System.out.println(BLUE_BACKGROUND_BRIGHT + "Blue background bright" + RESET);

        System.out.println("Purple");
        System.out.println(PURPLE + "Purple" + RESET);
        System.out.println(PURPLE_BOLD + "Purple bold" + RESET);
        System.out.println(PURPLE_UNDERLINED + "Purple underlined" + RESET);
        System.out.println(PURPLE_BACKGROUND + "Purple background" + RESET);
        System.out.println(PURPLE_BRIGHT + "Purple bright" + RESET);
        System.out.println(PURPLE_BOLD_BRIGHT + "Purple bold bright" + RESET);
        System.out.println(PURPLE_BACKGROUND_BRIGHT + "Purple background bright" + RESET);

        System.out.println("Cyan");
        System.out.println(CYAN + "Cyan" + RESET);
        System.out.println(CYAN_BOLD + "Cyan bold" + RESET);
        System.out.println(CYAN_UNDERLINED + "Cyan underlined" + RESET);
        System.out.println(CYAN_BACKGROUND + "Cyan background" + RESET);
        System.out.println(CYAN_BRIGHT + "Cyan bright" + RESET);
        System.out.println(CYAN_BOLD_BRIGHT + "Cyan bold bright" + RESET);
        System.out.println(CYAN_BACKGROUND_BRIGHT + "Cyan background bright" + RESET);

        System.out.println("White");
        System.out.println(WHITE + "White" + RESET);
        System.out.println(WHITE_BOLD + "White bold" + RESET);
        System.out.println(WHITE_UNDERLINED + "White underlined" + RESET);
        System.out.println(WHITE_BACKGROUND + "White background" + RESET);
        System.out.println(WHITE_BRIGHT + "White bright" + RESET);
        System.out.println(WHITE_BOLD_BRIGHT + "White bold bright" + RESET);
        System.out.println(WHITE_BACKGROUND_BRIGHT + "White background bright" + RESET);
    }
}
