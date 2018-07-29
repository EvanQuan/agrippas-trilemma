package game.system.output;

import game.system.gui.OutputPanel;

/**
 * Prints text to OutputPanel GUI. Does not wrap text as wrapping is done automatically (and dynamically as window
 * size changes.
 *
 * @author Evan Quan
 *
 */
public class GUIPrintBuffer implements IPrintBuffer {

    protected OutputPanel outputPanel;

    public GUIPrintBuffer() {
        outputPanel = OutputPanel.getInstance();
    }

    @Override
    public void append(double output) {
        append(Double.toString(output));
    }

    @Override
    public void append(int output) {
        append(Integer.toString(output));
    }

    @Override
    public void append(Object output) {
        append(output.toString());
    }

    @Override
    public void append(String output) {
        outputPanel.append(output);
    }
}
