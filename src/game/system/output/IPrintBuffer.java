package game.system.output;

/**
 * Game text output interface. Unlike {@link java.io.PrintStream}, which
 * directly prints to the output, this appends output to a buffer, where the
 * output is dealt with however its implementation needs to. While this change
 * has little to no impact for {@link game.system.gui.OutputPanel} which
 * "prints" by appending, this helps {@link ConsolePrintBuffer} accumulate
 * colored-text segments and hard-wrap them as the output text is built. Also,
 * as a slight performance boost, this allows the output to be built with {@link
 * StringBuilder} instead of {@link String} and making only a single call to
 * output for every player input.
 *
 * @author Evan Quan
 */
public interface IPrintBuffer {

    /**
     * Number of lines to separate the player receiveInput text line and its
     * proceeding prompt. Visually, this groups each player receiveInput line
     * with its resulting text prompt, so that when scrolling back, the player
     * can see their inputs and the corresponding results of those inputs.
     */
    int INPUT_SPACING = 4;

    /**
     * This symbol is placed immediately left of the player receiveInput to mark
     * it separate from the rest of the text.
     */
    String INPUT_MARKER = System.lineSeparator() + "> ";

    /**
     * @param output to print to output buffer in default color.
     */
    void print(double output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void print(double output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void print(int output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void print(int output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void print(Object output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void print(Object output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void print(String output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void print(String output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void println(double output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void println(double output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void println(int output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void println(int output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void println(Object output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void println(Object output, SemanticColor semanticColor);

    /**
     * @param output to print to output buffer in default color.
     */
    void println(String output);

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    void println(String output, SemanticColor semanticColor);

    /**
     * Append a number of System.lineseparator() to output buffer.
     *
     * @param lines to print
     */
    void printlns(int lines);

    /**
     * Append a System.lineseparator() to output buffer.
     */
    void println();
}
