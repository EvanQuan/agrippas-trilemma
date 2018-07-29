package game.system.output;

import java.io.PrintStream;

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
    private String reset;
    private String directionColor;
    private String playerColor;
    private String characterColor;
    private String locationColor;
    private String itemColor;
    private String speechColor;
    private String titleColor;

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

        reset = ConsoleColors.RESET.toString();
        directionColor = ConsoleColors.YELLOW_BOLD.toString();
        playerColor = ConsoleColors.CYAN_UNDERLINED.toString();
        characterColor = ConsoleColors.YELLOW_BOLD.toString();
        locationColor = ConsoleColors.GREEN_BOLD.toString();
        itemColor = ConsoleColors.RED_BOLD.toString();
        speechColor = ConsoleColors.GREEN_BOLD_BRIGHT.toString();
        titleColor = ConsoleColors.PURPLE_BOLD_BRIGHT.toString();
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
     * @param color of output
     */
    @Override
    public void append(double output, ConsoleColors color) {
        append(Double.toString(output), color);
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
     * @param color of output
     */
    @Override
    public void append(int output, ConsoleColors color) {
        append(Integer.toString(output), color);
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
     * @param color of output
     */
    @Override
    public void append(Object output, ConsoleColors color) {
        append(output.toString(), color);
    }

    /**
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(String output) {
        append(output, ConsoleColors.RESET);
    }

    /**
     *
     * Append the output of a specified color to the outputBuffer. The hard-wrapping is applied here instead of at
     * the end because we have information on the character width of text color, which needs to be ignored for
     * determining the cursorColumn.
     * <br><br>
     * TODO. Treating first word different not implemented yet. Seems very expensive to do it O(n).
     *
     * @param output to append to output buffer
     * @param color of output
     */
    @Override
    public void append(String output, ConsoleColors color) {
        // Split output into an array of lines, delimited by System.lineSeparator()
        String[] lines = output.split(System.lineSeparator(), 2);
        String[] words;
        // Iterate through each line to check if further splitting is needed (to hard-wrap)
        for (String line : lines) {
            if (cursorColumn + line.length() > wrapWidth) {
                // Split line into words and add them to conform to wrapWidth
                words = line.split(" ");
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
                        // TODO. Treating first word different not implemented yet. Seems very expensive to do it O(n).
                        outputBuffer.append(" ");
                        cursorColumn++;
                    }
                    outputBuffer.append(word);
                    cursorColumn += word.length();
                }
            } else {
                // Add line directly as it is less than wrap width
                outputBuffer.append(color + line + ConsoleColors.RESET);  // Apply color and add to buffer
                cursorColumn = 0;   // Move cursor back to the start
            }
        }
    }

    /**
     * Send output buffer contents to output and clear output buffer.
     */
    @Override
    public void flush() {
        out.print(outputBuffer);        // Print output
        outputBuffer.setLength(0);      // Empty outputBuffer
    }

    /**
     * @return true if output buffer is empty.
     */
    @Override
    public boolean isEmpty() {
        return outputBuffer.toString().isEmpty();
    }
}
