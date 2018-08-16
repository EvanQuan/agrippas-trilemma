package game.system.output;

import java.io.PrintStream;

/**
 * Prints hard-wrapped colored text to console.
 *
 * @author Evan Quan
 */
public class ConsolePrintBuffer implements IPrintBuffer {

    private static final int DEFAULT_WRAP_WIDTH = 79;

    private static final PrintStream DEFAULT_OUT = System.out;

    private ConsoleColor currentColor;

    /**
     * The maximum column before the text is hard-wrapped to the next line.
     */
    private int wrapWidth;

    /**
     * This is where all output is directed to. This can be configured with
     * setOut() if needed.
     */
    private PrintStream out;

    /**
     * Current column location of the cursor in the outputBuffer. This is
     * necessary to track as the text is hard-wrapped.
     */
    private int cursorColumn;

    public ConsolePrintBuffer() {
        wrapWidth = DEFAULT_WRAP_WIDTH;
        out = DEFAULT_OUT;
//        outputBuffer = new StringBuilder();
        currentColor = SemanticColor.toConsoleColor(SemanticColor.DEFAULT);
    }

    /**
     * Set the default {@link SemanticColor} that this buffer prints in.
     *
     * @param semanticColor
     */
    public void setCurrentColor(SemanticColor semanticColor) {
        this.currentColor = SemanticColor.toConsoleColor(semanticColor);
    }

    /**
     * Set the number of console columns to hard wrap the output text. If set to
     * 0, then wrap is disabled. Otherwise, must be positive.
     *
     * @param wrapWidth
     */
    public void setWrapWidth(int wrapWidth) {
        if (wrapWidth < 0) {
            throw new IllegalArgumentException("Can't set wrap width to negative value");
        }
        this.wrapWidth = wrapWidth;
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void print(double output) {
        print(Double.toString(output));
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void print(double output, SemanticColor semanticColor) {
        print(Double.toString(output), semanticColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void print(int output) {
        print(Integer.toString(output));
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void print(int output, SemanticColor semanticColor) {
        print(Integer.toString(output), semanticColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void print(Object output) {
        print(output.toString());
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void print(Object output, SemanticColor semanticColor) {
        print(output.toString(), semanticColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void print(String output) {
        print(output, currentColor);
    }

    /**
     * Append the output of a specified semanticColor to the outputBuffer. The
     * hard-wrapping is applied here instead of at the end because we have
     * information on the character width of text semanticColor, which needs to
     * be ignored for determining the cursorColumn.
     * <br><br>
     * TODO. Treating first word different not implemented yet. Seems very
     * expensive to do it O(n).
     *
     * @param output       to print to output buffer
     * @param consoleColor of output
     */
    public void print(String output, ConsoleColor consoleColor) {
        String[] words = output.split(" ", -1);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean wordEndsWithNewline = word.endsWith(System.lineSeparator());
            if (wordEndsWithNewline) {
                word = word.substring(0, word.length() - System.lineSeparator().length());
            }

            if (cursorColumn + word.length() > wrapWidth) {
                printDirect(System.lineSeparator() + word, consoleColor);
                cursorColumn = System.lineSeparator().length() + word.length();
            } else { // Add space before word, if word is not itself a space
                boolean addSpace = i == 0 || output.isEmpty();
                printDirect((addSpace ? "" : " ") + word, consoleColor);
                cursorColumn += (addSpace ? 0 : 1) + word.length();
            }

            if (wordEndsWithNewline) {
                out.println();
                cursorColumn = 0;
            }
        }
        if (output.endsWith(System.lineSeparator())) {
            cursorColumn = 0;
        }
    }

    /**
     * Directly print an output with a specified color without wrapping. Does
     * not affect cursorColumn.
     *
     * @param output
     * @param consoleColor
     */
    private void printDirect(String output, ConsoleColor consoleColor) {
        out.print(consoleColor + output + currentColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void println(double output) {
        println(Double.toString(output));
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void println(double output, SemanticColor semanticColor) {
        println(Double.toString(output), semanticColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void println(int output) {
        println(Integer.toString(output));
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void println(int output, SemanticColor semanticColor) {
        println(Integer.toString(output), semanticColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void println(Object output) {
        println(output.toString());
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void println(Object output, SemanticColor semanticColor) {
        println(output.toString(), semanticColor);
    }

    /**
     * @param output to print to output buffer in default color.
     */
    @Override
    public void println(String output) {
        print(output + System.lineSeparator(), currentColor);
    }

    /**
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void println(String output, SemanticColor semanticColor) {
        print(output + System.lineSeparator(), semanticColor);
    }

    /**
     * Append a number of System.lineseparator() to output buffer.
     *
     * @param lines to print
     */
    @Override
    public void printlns(int lines) {
        for (int i = 0; i < lines; i++) {
            out.println();
        }
    }

    /**
     * Append a System.lineseparator to output buffer.
     */
    @Override
    public void println() {
        out.println();
    }

    /**
     * Append the output with the specified {@link SemanticColor} color.
     *
     * @param output        to print to output buffer
     * @param semanticColor of output
     */
    @Override
    public void print(String output, SemanticColor semanticColor) {
        print(output, SemanticColor.toConsoleColor(semanticColor));
    }
}
