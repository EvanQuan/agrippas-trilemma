package game.system.output;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;

/**
 * Prints hard-wrapped colored text to console.
 *
 * @author Evan Quan
 *
 */
public class ConsolePrintBuffer implements IPrintBuffer {

    public static final int DEFAULT_WRAP_WIDTH = 79;

    public static final PrintStream DEFAULT_OUT = System.out;

    private ConsoleColor currentColor;

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
//    private StringBuilder outputBuffer;

    /**
     * Current column location of the cursor in the outputBuffer. This is necessary to track as the text is
     * hard-wrapped.
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
        append(output, currentColor);
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
     * @param consoleColor of output
     */
    public void append(String output, ConsoleColor consoleColor) {
        // VERSION 1 -- complicated, but covers more edge cases for extra/missing spaces (maybe)
//        if (lastAppendEndedWithSpace || output.startsWith(" ")) {
//            out.print(" ");
//            cursorColumn++;
//        }
//        // Split output into an array of lines, delimited by System.lineSeparator()
//        String[] lines = output.split(System.lineSeparator(), 2);
//        String[] words;
//        // Iterate through each line to check if further splitting is needed (to hard-wrap)
//        for (int i = 0; i < lines.length; i++) {
//            if (cursorColumn + lines[i].length() > wrapWidth) {
//                // Split line into words and add them to conform to wrapWidth
//                words = lines[i].split(" ");
//                for (String word : words) {
//                    if (cursorColumn + word.length() + 1 > wrapWidth && cursorColumn > 0) {
//                        // +1 is for the space before word
//                        // Check cursorColumn is not at start, since a word longer than wrapWidth will then create
//                        // an extra new line if the cursor is already at the start.
////                            outputBuffer.append(System.lineSeparator());
//                        out.println();
//                        cursorColumn = 0;
//                    } else if (cursorColumn > 0) {
//                        // Add space before word if it is not the start of the column
//                        // and not first word of line (since spaces separated words only within the line).
//                        // TODO. Treating first word different not implemented yet. Seems expensive to do it O(n).
////                            outputBuffer.append(" ");
//                        out.print(" ");
//                        cursorColumn++;
//                    }
//                    append(word, consoleColor);
//                    cursorColumn += word.length();
//                }
//            } else if (!lines[i].isEmpty()) {
//                // else if the line is not empty
//                // Add line directly as it is less than wrap width
//                // strip space at the end and re-add it for next append so there is no trailing whitespace
//                append(lines[i].trim(), consoleColor);
//                // Apply semanticColor and add new line that was removed
//                cursorColumn += lines[i].length();   // Move cursor back to the start
//            } else {
//                // If the line is blank, which is due to how the line is split when there was a new line character,
//                // then add a new line.
//                out.println();
//                cursorColumn = 0;
//            }
//        }
//        if (lines.length > 1 && lines[lines.length -1].isEmpty()) {
//            // If there are multiple lines and we are processing the final line which is blank, that means there was
//            // a final new line that was removed and needs to re-added.
//            // The reason why this is only true for multiple lines is that if there is a single blank line, then the
//            // user simply inputted an empty string.
//            out.println();
//            cursorColumn = 0;
//        }
//
//        lastAppendEndedWithSpace = output.endsWith(" ");

        // VERSION 2 -- cleaner, but wrapping is slightly different, potentially doesn't work
        // for multiple line input text
        String[] words = output.split(" ", -1);
        // DEBUG START
//        out.println("[ Before" + cursorColumn + "]");
//        ArrayList<String> a = new ArrayList(Arrays.asList(words));
//        out.println("[Length: " + output.length() + "]");
//        out.println(a);
//        int visualLength = 0;
//        out.print("Visual length: ");
//        for (int i = 0; i < a.size(); i++) {
//            visualLength += a.get(i).length();
//            out.print("(" + i + ") length:" + a.get(i).length() + "[" + a.get(i) + "], ");
//        }
//        out.println("[Visual length: " + visualLength + "]");
//        out.println("[Tokens: " + words.length + "]");
        // DEBUG END
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean wordEndsWithNewline = word.endsWith(System.lineSeparator());
            if (wordEndsWithNewline) {
                word = word.substring(0, word.length() - System.lineSeparator().length());
            }

            if (cursorColumn + word.length() > wrapWidth) {
                printDirect(System.lineSeparator() + word, consoleColor);
                cursorColumn = System.lineSeparator().length() + word.length();
            }else { // Add space before word, if word is not itself a space
                boolean addSpace = i == 0 || output.isEmpty();
                printDirect((addSpace ? "" : " ") + word, consoleColor);
                cursorColumn += (addSpace ? 0 : 1)  + word.length();
            }

            if (wordEndsWithNewline) {
                out.println();
                cursorColumn = 0;
            }
        }
        if (output.endsWith(System.lineSeparator())) {
            cursorColumn = 0;
        }

//        out.print("[ After" + cursorColumn + "]");
    }

    /**
     * Directly print an output with a specified color without wrapping. Does not affect cursorColumn.
     *
     * @param output
     * @param consoleColor
     */
    public void printDirect(String output, ConsoleColor consoleColor) {
        out.print(consoleColor + output + currentColor);
    }

    /**
     * Directly print an output with a specified color without wrapping. Does not affect cursorColumn.
     *
     * @param output
     * @param semanticColor
     */
    public void printDirect(String output, SemanticColor semanticColor) {
        printDirect(output, SemanticColor.toConsoleColor(semanticColor));
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
        append(output + System.lineSeparator(), currentColor);
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
     * Append a number of System.lineseparator() to output buffer.
     *
     * @param lines to append
     */
    @Override
    public void appendlns(int lines) {
        for (int i = 0; i < lines; i++) {
            out.println();
        }
    }

    /**
     * Append a System.lineseparator to output buffer.
     */
    @Override
    public void appendln() {
        out.println();
    }

    /**
     * Append the output with the specified {@link SemanticColor} color.
     *
     * @param output
     * @param semanticColor
     */
    @Override
    public void append(String output, SemanticColor semanticColor) {
        append(output, SemanticColor.toConsoleColor(semanticColor));
    }
}
