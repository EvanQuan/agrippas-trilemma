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
     * Append the output of a specified color to the outputBuffer. The hard-wrapping is applied here instead of at
     * the end because we have information on the character width of text color, which needs to be ignored for
     * determining the cursorColumn.
     *
     * @param output to append to output buffer
     * @param color of output
     */
    @Override
    public void append(String output, ConsoleColors color) {
        String[] outputSplit = {"", output};
        do {// Iterate through each line of output, deliminted by System.lineSeparator()
            // Find the first new line character and split the string at that point so that the:
            //      first segment is the first "line"
            //      second segment is the remaining string
            outputSplit = outputSplit[1].split(System.lineSeparator(), 2);
            // Since the color and reset characters don't actually take up any space, the length of their characters
            // should be ignored (only consider the length of output).
            // Escape sequence characters also take up twice as much space in the string than what they display as, but
            // since they are not particularly common and their effect is minimal on affecting total length, they are
            // not considered.
            while (cursorColumn + outputSplit[0].length() > wrapWidth) { // line would extend beyond wrapWidth
                // Replace spaces with new line characters such that the line becomes multiple lines of length less
                // than wrapWidth.
//                cursorColumn = outputSplit[0].lastIndexOf(" ", cursorColumn + wrapWidth);
                // TODO RETURN HERE.
            }
            // TODO append here
        } while (outputSplit.length > 1);
        // Split input by newlines
//        while (cursorColumn + wrapWidth < outputBuffer.length() && (cursorColumn = outputBuffer.lastIndexOf(" ",
//                cursorColumn + wrapWidth)) != -1) {
//            outputBuffer.replace(cursorColumn, cursorColumn + 1, System.lineSeparator());
//        }
//
//        outputBuffer.append(color + output + ConsoleColors.RESET);
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

    @Override
    public void appendCharacter(double output) {
        appendCharacter(Double.toString(output));
    }

    @Override
    public void appendCharacter(int output) {
        appendCharacter(Integer.toString(output));
    }

    @Override
    public void appendCharacter(Object output) {
        appendCharacter(output.toString());
    }

    @Override
    public void appendCharacter(String output) {
        System.out.print(characterColor + output + reset);

    }

    @Override
    public void appendCharacterln(double output) {
        appendCharacterln(Double.toString(output));

    }

    @Override
    public void appendCharacterln(int output) {
        appendCharacterln(Integer.toString(output));

    }

    @Override
    public void appendCharacterln(Object output) {
        appendCharacterln(output.toString());
    }

    @Override
    public void appendCharacterln(String output) {
        appendCharacter(output + "\n");

    }

    @Override
    public void printDirection(double output) {
        printDirection(Double.toString(output));

    }

    @Override
    public void printDirection(int output) {
        printDirection(Integer.toString(output));

    }

    @Override
    public void printDirection(Object output) {
        printDirection(output.toString());

    }

    @Override
    public void printDirection(String output) {
        System.out.print(directionColor + output + reset);

    }

    @Override
    public void printDirectionln(double output) {
        printDirectionln(Double.toString(output));

    }

    @Override
    public void printDirectionln(int output) {
        printDirectionln(Integer.toString(output));

    }

    @Override
    public void printDirectionln(Object output) {
        printDirectionln(output.toString());
    }

    @Override
    public void printDirectionln(String output) {
        printDirection(output + "\n");

    }

    @Override
    public void printItem(double output) {
        printItem(Double.toString(output));

    }

    @Override
    public void printItem(int output) {
        printItem(Integer.toString(output));

    }

    @Override
    public void printItem(Object output) {
        printItem(output.toString());
    }

    @Override
    public void printItem(String output) {
        System.out.print(itemColor + output + reset);

    }

    @Override
    public void printItemln(double output) {
        printItemln(Double.toString(output));
    }

    @Override
    public void printItemln(int output) {
        printItemln(Integer.toBinaryString(output));
    }

    @Override
    public void printItemln(Object output) {
        printItemln(output.toString());
    }

    @Override
    public void printItemln(String output) {
        printItem(output + "\n");
    }

    @Override
    public void println() {
        System.out.println();

    }

    @Override
    public void println(double output) {
        System.out.println(output);
    }

    @Override
    public void println(int output) {
        System.out.println(output);
    }

    @Override
    public void println(Object output) {
        println(output.toString());
    }

    @Override
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    public void printlns(int spacing) {
        for (int i = 0; i < spacing; i++) {
            println();
        }
    }

    @Override
    public void printLocation(double output) {
        printLocation(Double.toString(output));
    }

    @Override
    public void printLocation(int output) {
        printLocation(Integer.toString(output));

    }

    @Override
    public void printLocation(Object output) {
        printLocation(output.toString());
    }

    @Override
    public void printLocation(String output) {
        System.out.print(locationColor + output + reset);
    }

    @Override
    public void printLocationln(double output) {
        printLocation(Double.toString(output));
    }

    @Override
    public void printLocationln(int output) {
        printLocation(Integer.toString(output));
    }

    @Override
    public void printLocationln(Object output) {
        printLocationln(output.toString());
    }

    @Override
    public void printLocationln(String output) {
        printLocation(output + "\n");
    }

    @Override
    public void printPlayer(double output) {
        printPlayer(Double.toString(output));
    }

    @Override
    public void printPlayer(int output) {
        printPlayer(Integer.toString(output));
    }

    @Override
    public void printPlayer(Object output) {
        printPlayer(output.toString());
    }

    @Override
    public void printPlayer(String output) {
        System.out.print(playerColor + output + reset);
    }

    @Override
    public void printPlayerInput(double output) {
        printPlayerInput(Double.toString(output));
    }

    @Override
    public void printPlayerInput(int output) {
        printPlayerInput(Integer.toString(output));
    }

    @Override
    public void printPlayerInput(Object output) {
        printPlayerInput(output.toString());
    }

    @Override
    public void printPlayerInput(String output) {
        printlns(INPUT_SPACING);
        printPlayerInputNoSpace(output);
    }

    @Override
    public void printPlayerInputNoSpace(double output) {
        printPlayerInputNoSpace(Double.toString(output));
    }

    @Override
    public void printPlayerInputNoSpace(int output) {
        printPlayerInputNoSpace(Integer.toString(output));
    }

    @Override
    public void printPlayerInputNoSpace(Object output) {
        printPlayerInputNoSpace(output.toString());
    }

    @Override
    public void printPlayerInputNoSpace(String output) {
        append(INPUT_MARKER);
        printPlayer(output + "\n");
    }

    @Override
    public void printPlayerln(double output) {
        printPlayerln(Double.toString(output));
    }

    @Override
    public void printPlayerln(int output) {
        printPlayerln(Integer.toString(output));
    }

    @Override
    public void printPlayerln(Object output) {
        printPlayerln(output.toString());
    }

    @Override
    public void printPlayerln(String output) {
        printPlayer(output + "\n");
    }

    @Override
    public void printSpeech(double output) {
        printPlayer(Double.toString(output));
    }

    @Override
    public void printSpeech(int output) {
        printPlayer(Integer.toString(output));
    }

    @Override
    public void printSpeech(Object output) {
        printSpeech(output.toString());
    }

    @Override
    public void printSpeech(String output) {
        System.out.print(speechColor + output);
    }

    @Override
    public void printSpeechln(double output) {
        printSpeechln(Double.toString(output));
    }

    @Override
    public void printSpeechln(int output) {
        printSpeechln(Integer.toString(output));
    }

    @Override
    public void printSpeechln(Object output) {
        printSpeechln(output.toString());
    }

    @Override
    public void printSpeechln(String output) {
        printSpeech(output + "\n");
    }

    @Override
    public void printTitle(double output) {
        printTitle(Double.toString(output));
    }

    @Override
    public void printTitle(int output) {
        printTitle(Integer.toString(output));
    }

    @Override
    public void printTitle(Object output) {
        printTitle(output.toString());
    }

    @Override
    public void printTitle(String output) {
        System.out.print(titleColor + output + "\n");
    }

    @Override
    public void printTitleln(double output) {
        printTitleln(Double.toString(output));
    }

    @Override
    public void printTitleln(int output) {
        printTitleln(Integer.toString(output));
    }

    @Override
    public void printTitleln(Object output) {
        printTitleln(output.toString());
    }

    @Override
    public void printTitleln(String output) {
        printTitle(output + "\n");
    }

}
