package game.system.output;

import game.system.gui.OutputPanel;

/**
 * Prints text to {@link OutputPanel} GUI. Does not wrap text as wrapping is done automatically (and dynamically as
 * window size changes.
 *
 * @deprecated {@link OutputPanel} now directly implements {@link IPrintBuffer} and no longer needs this intermediate
 * class.
 * @author Evan Quan
 *
 */
@Deprecated
public class GUIPrintBuffer implements IPrintBuffer {

    protected OutputPanel outputPanel;

    /**
     * Default constructor. This transfers text to {@link OutputPanel}.
     */
    public GUIPrintBuffer() {
        outputPanel = OutputPanel.getInstance();
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(double output) {
        append(Double.toString(output));
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void append(double output, SemanticColor semanticColor) {
        append(Double.toString(output), semanticColor);
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(int output) {
        append(Integer.toString(output));
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void append(int output, SemanticColor semanticColor) {
        append(Integer.toString(output), semanticColor);
    }

    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(Object output) {
        append(output.toString());
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void append(Object output, SemanticColor semanticColor) {
        append(output.toString(), semanticColor);
    }


    /**
     *
     * @param output to append to output buffer in default color.
     */
    @Override
    public void append(String output) {
        outputPanel.append(output);
    }

    /**
     *
     * @param output to append to output buffer
     * @param semanticColor  of output
     */
    @Override
    public void append(String output, SemanticColor semanticColor) {
        outputPanel.append(output, semanticColor);
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
        appendln(output, SemanticColor.DEFAULT);
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
}
