package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Output of game text
 * Has a scroll pane
 */
public class OutputPanel extends GridBagPanel {
    public static final int TEXT_AREA_HEIGHT = 70;
    public static final int TEXT_AREA_WIDTH = 50;
    public static final int TEXT_X = 0;
    public static final int TEXT_Y = 0;
    public static final int TEXT_WIDTH = 1;
    public static final int TEXT_HEIGHT = 1;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public OutputPanel() {
        textArea = new JTextArea(TEXT_AREA_WIDTH,TEXT_AREA_HEIGHT);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textArea);
        setFill(GridBagConstraints.BOTH);
        setWeightX(1.0);
        setWeightY(1.0);
        addComponent(scrollPane,TEXT_X,TEXT_Y,TEXT_WIDTH,TEXT_HEIGHT);
    }

    public void append(String s) {
        textArea.setText(textArea.getText() + s);
    }
}
