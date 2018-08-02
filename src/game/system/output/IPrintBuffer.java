package game.system.output;

/**
 * Game text output. Unlike {@link java.io.PrintStream}, which directly prints to the output, this appends output in a
 * buffer, and finally prints and flushes the text contained buffer together. While this change has little to no
 * impact for {@link GUIPrintBuffer}, this helps {@link ConsolePrintBuffer} accumulate colored-text segments
 * and hard-wrap them as the output text is built. Also, as a slight performance boost, this allows the output to be
 * built with {@link StringBuilder} instead of {@link String} and making only a single call to output for every player
 * receiveInput.
 *
 * @author Evan Quan
 *
 */
public interface IPrintBuffer {

    /**
     * Number of lines to separate the player receiveInput text line and its proceeding prompt. Visually, this groups each
     * player receiveInput line with its resulting text prompt, so that when scrolling back, the player can see their inputs
     * and the corresponding results of those inputs.
     */
    int INPUT_SPACING = 4;

    /**
     * This symbol is placed immediately left of the player receiveInput to mark it separate from the rest of the text.
     */
    String INPUT_MARKER = "> ";

    /**
     * @param output to append to output buffer in default color.
     */
    void append(double output);

    /**
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    void append(double output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void append(int output);

    /**
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    void append(int output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void append(Object output);

    /**
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    void append(Object output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void append(String output);

    /**
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    void append(String output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void appendln(double output);

    /**
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    void appendln(double output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void appendln(int output);

    /**
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    void appendln(int output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void appendln(Object output);

    /**
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    void appendln(Object output, SemanticColor semanticColor);

    /**
     * @param output to append to output buffer in default color.
     */
    void appendln(String output);

    /**
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    void appendln(String output, SemanticColor semanticColor);
}
