package game.system.output;

/**
 * Game text output
 *
 * @author Evan Quan
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public interface IPrinter {

    public static final int INPUT_SPACING = 4;
    public static final String INPUT_MARKER = "> ";

    /**
     * Appends to outputString
     *
     * @param String
     *            output
     */
    public void print(double output);

    public void print(int output);

    public void print(Object output);

    public void print(String output);

    public void printCharacter(double output);

    public void printCharacter(int output);

    public void printCharacter(Object output);

    public void printCharacter(String output);

    public void printCharacterln(double output);

    public void printCharacterln(int output);

    public void printCharacterln(Object output);

    public void printCharacterln(String output);

    public void printDirection(double output);

    public void printDirection(int output);

    public void printDirection(Object output);

    public void printDirection(String output);

    public void printDirectionln(double output);

    public void printDirectionln(int output);

    public void printDirectionln(Object output);

    public void printDirectionln(String output);

    public void printItem(double output);

    public void printItem(int output);

    public void printItem(Object output);

    public void printItem(String output);

    public void printItemln(double output);

    public void printItemln(int output);

    public void printItemln(Object output);

    public void printItemln(String output);

    public void println();

    public void println(double output);

    public void println(int output);

    public void println(Object output);

    public void println(String output);

    public void printlns(int output);

    public void printLocation(double output);

    public void printLocation(int output);

    public void printLocation(Object output);

    public void printLocation(String output);

    public void printLocationln(double output);

    public void printLocationln(int output);

    public void printLocationln(Object output);

    public void printLocationln(String output);

    public void printPlayer(double output);

    public void printPlayer(int output);

    public void printPlayer(Object output);

    public void printPlayer(String output);

    public void printPlayerInput(double output);

    public void printPlayerInput(int output);

    public void printPlayerInput(Object output);

    /**
     * Output player input with input marker
     */
    public void printPlayerInput(String output);

    public void printPlayerInputNoSpace(double output);

    public void printPlayerInputNoSpace(int output);

    public void printPlayerInputNoSpace(Object output);

    public void printPlayerInputNoSpace(String output);

    public void printPlayerln(double output);

    public void printPlayerln(int output);

    public void printPlayerln(Object output);

    public void printPlayerln(String output);

    public void printSpeech(double output);

    public void printSpeech(int output);

    public void printSpeech(Object output);

    public void printSpeech(String output);

    public void printSpeechln(double output);

    public void printSpeechln(int output);

    public void printSpeechln(Object output);

    public void printSpeechln(String output);

    public void printTitle(double output);

    public void printTitle(int output);

    public void printTitle(Object output);

    public void printTitle(String output);

    public void printTitleln(double output);

    public void printTitleln(int output);

    public void printTitleln(Object output);

    public void printTitleln(String output);
}
