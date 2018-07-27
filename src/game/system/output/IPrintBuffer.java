package game.system.output;

/**
 * Game text output. Unlike {@link java.io.PrintStream}, which directly prints to the output, this appends output in a
 * buffer, and finally prints and flushes the text contained buffer together. While this change has little to no
 * impact for {@link GUIPrintBuffer}, this helps {@link ConsolePrintBuffer} accumulate colored-text segments
 * and hard-wrap them as the output text is built. Also, as a slight performance boost, this allows the output to be
 * built with {@link StringBuilder} instead of {@link String} and making only a single call to output for every player
 * input.
 *
 * @author Evan Quan
 *
 */
public interface IPrintBuffer {

    /**
     * Number of lines to separate the player input text line and its proceeding prompt. Visually, this groups each
     * player input line with its resulting text prompt, so that when scrolling back, the player can see their inputs
     * and the corresponding results of those inputs.
     */
    int INPUT_SPACING = 4;

    /**
     * This symbol is placed immediately left of the player input to mark it separate from the rest of the text.
     */
    String INPUT_MARKER = "> ";

    /**
     * @param output to append to output buffer in default color.
     */
    void append(double output);

    /**
     * @param output to append to output buffer
     * @param color  of output
     */
    void append(double output, ConsoleColors color);

    /**
     * @param output to append to output buffer in default color.
     */
    void append(int output);

    /**
     * @param output to append to output buffer
     * @param color  of output
     */
    void append(int output, ConsoleColors color);

    /**
     * @param output to append to output buffer in default color.
     */
    void append(Object output);

    /**
     * @param output to append to output buffer
     * @param color  of output
     */
    void append(Object output, ConsoleColors color);

    /**
     * @param output to append to output buffer in default color.
     */
    void append(String output);

    /**
     * @param output to append to output buffer
     * @param color  of output
     */
    void append(String output, ConsoleColors color);

    /**
     * Send output buffer contents to output and clear output buffer.
     */
    void flush();

    /**
     * @return true if output buffer is empty.
     */
    boolean isEmpty();

    @Deprecated
    void appendCharacter(double output);

    @Deprecated
    void appendCharacter(int output);

    @Deprecated
    void appendCharacter(Object output);

    @Deprecated
    void appendCharacter(String output);

    @Deprecated
    void appendCharacterln(double output);

    @Deprecated
    void appendCharacterln(int output);

    @Deprecated
    void appendCharacterln(Object output);

    @Deprecated
    void appendCharacterln(String output);

    @Deprecated
    void printDirection(double output);

    @Deprecated
    void printDirection(int output);

    @Deprecated
    void printDirection(Object output);

    @Deprecated
    void printDirection(String output);

    @Deprecated
    void printDirectionln(double output);

    @Deprecated
    void printDirectionln(int output);

    @Deprecated
    void printDirectionln(Object output);

    @Deprecated
    void printDirectionln(String output);

    @Deprecated
    void printItem(double output);

    @Deprecated
    void printItem(int output);

    @Deprecated
    void printItem(Object output);

    @Deprecated
    void printItem(String output);

    @Deprecated
    void printItemln(double output);

    @Deprecated
    void printItemln(int output);

    @Deprecated
    void printItemln(Object output);

    @Deprecated
    void printItemln(String output);

    @Deprecated
    void println();

    @Deprecated
    void println(double output);

    @Deprecated
    void println(int output);

    @Deprecated
    void println(Object output);

    @Deprecated
    void println(String output);

    @Deprecated
    void printlns(int output);

    @Deprecated
    void printLocation(double output);

    @Deprecated
    void printLocation(int output);

    @Deprecated
    void printLocation(Object output);

    @Deprecated
    void printLocation(String output);

    @Deprecated
    void printLocationln(double output);

    @Deprecated
    void printLocationln(int output);

    @Deprecated
    void printLocationln(Object output);

    @Deprecated
    void printLocationln(String output);

    @Deprecated
    void printPlayer(double output);

    @Deprecated
    void printPlayer(int output);

    @Deprecated
    void printPlayer(Object output);

    @Deprecated
    void printPlayer(String output);

    @Deprecated
    void printPlayerInput(double output);

    @Deprecated
    void printPlayerInput(int output);

    @Deprecated
    void printPlayerInput(Object output);

    /**
     * Output player input with input marker
     */
    @Deprecated
    void printPlayerInput(String output);

    @Deprecated
    void printPlayerInputNoSpace(double output);

    @Deprecated
    void printPlayerInputNoSpace(int output);

    @Deprecated
    void printPlayerInputNoSpace(Object output);

    @Deprecated
    void printPlayerInputNoSpace(String output);

    @Deprecated
    void printPlayerln(double output);

    @Deprecated
    void printPlayerln(int output);

    @Deprecated
    void printPlayerln(Object output);

    @Deprecated
    void printPlayerln(String output);

    @Deprecated
    void printSpeech(double output);

    @Deprecated
    void printSpeech(int output);

    @Deprecated
    void printSpeech(Object output);

    @Deprecated
    void printSpeech(String output);

    @Deprecated
    void printSpeechln(double output);

    @Deprecated
    void printSpeechln(int output);

    @Deprecated
    void printSpeechln(Object output);

    @Deprecated
    void printSpeechln(String output);

    @Deprecated
    void printTitle(double output);

    @Deprecated
    void printTitle(int output);

    @Deprecated
    void printTitle(Object output);

    @Deprecated
    void printTitle(String output);

    @Deprecated
    void printTitleln(double output);

    @Deprecated
    void printTitleln(int output);

    @Deprecated
    void printTitleln(Object output);

    @Deprecated
    void printTitleln(String output);
}
