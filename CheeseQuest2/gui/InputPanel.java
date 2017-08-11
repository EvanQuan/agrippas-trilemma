package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import game.menu.TextManager;

/**
 * Gets input from the user and outputs it to game
 * Consists of JTextField for input text
 * Can send text with enter key
 */
public class InputPanel extends GridBagPanel {
    public static final int TEXT_MAX_WIDTH = 50;
    public static final int TEXT_X = 0;
    public static final int TEXT_Y = 0;
    public static final int TEXT_WIDTH = 1;
    public static final int TEXT_HEIGHT = 1;
    public static final String EMPTY = "";
    private JTextField inputTextField;
    private OutputPanel outputPanel;
    private JButton button;
    private TextManager textManager;
/**
     * Sends text of text area when ENTER key is pressed
     * Works alongside manually pressing inputButton
     */
    private AbstractAction action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputTextField.getText();
            textManager.input(input);
            outputPanel.append(textManager.output());
            inputTextField.setText(EMPTY);
        }
    };
    private String input;

    /**
     * Default InputPanel constructor
     */
    public InputPanel(OutputPanel outputPanel) {
        this.outputPanel = outputPanel;
        this.inputTextField = new JTextField(TEXT_MAX_WIDTH);
        button = new JButton("Send");
        textManager = new TextManager();
        this.inputTextField.addActionListener(action);
        setAnchor(GridBagConstraints.WEST);
        setFill(GridBagConstraints.HORIZONTAL);
        setWidth(GridBagConstraints.RELATIVE);
        setWeightX(1.0);
        addComponent(inputTextField);
        setAnchor(GridBagConstraints.EAST);
        setFill(GridBagConstraints.NONE);
        setWeightX(0.0);
        addComponent(button);
    }



}
