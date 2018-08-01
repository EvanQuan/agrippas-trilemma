package game.system.output;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Prints hard-wrapped colored text to console.
 *
 * @author Evan Quan
 *
 */
public class ConsolePrintBuffer implements IPrintBuffer {

    public static final int DEFAULT_WRAP_WIDTH = 79;

    public static final PrintStream DEFAULT_OUT = System.out;
    /**
     * The maximum column before the text is hard-wrapped to the next line.
     */
    private int wrapWidth;
    /**
     * Where the text is outputted to. This may need to be changed for debugging purposes.
     */
    private PrintStream out;

    /**
     * Output text is built and stored here until it is finally printed.
     */
    private StringBuilder outputBuffer;

    /**
     * Current column location of the cursor in the outputBuffer. This is necessary to track as the text is
     * hard-wrapped.
     */
    private int cursorColumn;

    public ConsolePrintBuffer() {
        wrapWidth = DEFAULT_WRAP_WIDTH;
        out = DEFAULT_OUT;

        outputBuffer = new StringBuilder();
    }

    /**
     * Set the number of console columns to hard wrap the output text. If set to 0, then wrap is disabled. Otherwise,
     * must be positive.
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
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(double output) {
        append(Double.toString(output));
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    @Override
    public void append(double output, SemanticColor semanticColor) {
        append(Double.toString(output), semanticColor);
    }

    /**
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(int output) {
        append(Integer.toString(output));
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    @Override
    public void append(int output, SemanticColor semanticColor) {
        append(Integer.toString(output), semanticColor);
    }

    /**
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(Object output) {
        append(output.toString());
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    @Override
    public void append(Object output, SemanticColor semanticColor) {
        append(output.toString(), semanticColor);
    }

    /**
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(String output) {
        append(output, SemanticColor.DEFAULT);
    }

    /**
     *
     * Append the output of a specified semanticColor to the outputBuffer. The hard-wrapping is applied here instead of at
     * the end because we have information on the character width of text semanticColor, which needs to be ignored for
     * determining the cursorColumn.
     * <br><br>
     * TODO. Treating first word different not implemented yet. Seems very expensive to do it O(n).
     *
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    @Override
    public void append(String output, SemanticColor semanticColor) {
        ConsoleColor consoleColor = SemanticColor.toConsoleColor(semanticColor);
        // Split output into an array of lines, delimited by System.lineSeparator()
        String[] lines = output.split(System.lineSeparator(), 2);
        String[] words;
        // Iterate through each line to check if further splitting is needed (to hard-wrap)
        for (int i = 0; i < lines.length; i++) {
            if (cursorColumn + lines[i].length() > wrapWidth) {
                // Split line into words and add them to conform to wrapWidth
                words = lines[i].split(" ");
                for (String word : words) {
                    if (cursorColumn + word.length() + 1 > wrapWidth && cursorColumn > 0) {
                        // +1 is for the space before word
                        // Check cursorColumn is not at start, since a word longer than wrapWidth will then create
                        // an extra new line if the cursor is already at the start.
                        outputBuffer.append(System.lineSeparator());
                        cursorColumn = 0;
                    } else if (cursorColumn > 0) {
                        // Add space before word if it is not the start of the column
                        // and not first word of line (since spaces separated words only within the line).
                        // TODO. Treating first word different not implemented yet. Seems expensive to do it O(n).
                        outputBuffer.append(" ");
                        cursorColumn++;
                    }
                    outputBuffer.append(consoleColor + word + ConsoleColor.RESET);
                    cursorColumn += word.length();
                }
            } else if (lines[i].length() > 0) {
                // Add line directly as it is less than wrap width
                // If the line is blank, which is due to how the line is split when there was a new line character,
                // then don't add a new line, as that would double the new lines.
                outputBuffer.append(consoleColor + lines[i] + ConsoleColor.RESET + (lines[i].length() > 1 ?
                        System.lineSeparator() :
                        ""));
                // Apply semanticColor and add new line that was removed
                cursorColumn = 0;   // Move cursor back to the start
            }
        }
        if (lines.length > 1 && lines[lines.length -1].isEmpty()) {
            // If there are multiple lines and we are processing the final line which is blank, that means there was
            // a final new line that was removed and needs to re-added.
            // The reason why this is only true for multiple lines is that if there is a single blank line, then the
            // user simply inputed an empty string.
            outputBuffer.append(System.lineSeparator());
            cursorColumn = 0;
        }
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void appendln(double output) {
        appendln(Double.toString(output));
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void appendln(double output, SemanticColor semanticColor) {
        appendln(Double.toString(output), semanticColor);
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void appendln(int output) {
        appendln(Integer.toString(output));
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void appendln(int output, SemanticColor semanticColor) {
        appendln(Integer.toString(output), semanticColor);
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void appendln(Object output)  {
        appendln(output.toString());
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void appendln(Object output, SemanticColor semanticColor) {
        appendln(output.toString(), semanticColor);
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void appendln(String output) {
        append(output, SemanticColor.DEFAULT);
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor of output
     */
    @Override
    public void appendln(String output, SemanticColor semanticColor) {
        append(output + System.lineSeparator(), semanticColor);
    }

    /**
     * Send output buffer contents to output and clear output buffer.
     */
    public void print() {
        out.print(outputBuffer);        // Print output
        outputBuffer.setLength(0);      // Empty outputBuffer
    }
}
