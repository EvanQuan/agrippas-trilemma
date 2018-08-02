package game.system.gui;

import game.menu.MainMenu;
import game.menu.MenuStack;
import game.system.input.PlayerCommand;
import game.system.input.PlayerInputParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Gets receiveInput from the user and outputs it to game Consists of JTextField for
 * receiveInput text Can send text with enter key
 *
 * Saves previous receiveInput, which can be acquired through up arrow like in terminal
 */
public class InputPanel extends GridBagPanel {

    private static final long serialVersionUID = 1L;

    public static final int TEXT_MAX_WIDTH = 50;
    public static final int TEXT_X = 0;
    public static final int TEXT_Y = 0;
    public static final int TEXT_WIDTH = 0;
    public static final int TEXT_HEIGHT = 0;
    public static final String EMPTY = "";

    // Font
    public static final String NAME = "Consolas";
    public static final int STYLE = Font.PLAIN;
    public static final int SIZE = 14;
    private Font font;

    /**
     * The text field where the user inputs a command.
     */
    private JTextField inputTextField;

    /**
     * Instead of pressing ENTER to send a command, the user can alternatively press this button with the mouse.
     * TODO: Currently not implemented.
     */
    private JButton button;

    /**
     * The history of all previous inputs from the current session.
     */
    private ArrayList<String> previousInput;
    /**
     * The index in previousIndex to retrieve receiveInput history.
     */
    private int inputHistoryIndex;

    /**
     * Sends text of text area when ENTER key is pressed. Works alongside manually
     * pressing inputButton (which is yet to be implemented).
     */
    private AbstractAction action = new AbstractAction() {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputTextField.getText();                    // Retrieve raw receiveInput
            PlayerCommand command = PlayerInputParser.parse(input);     // Parse receiveInput to command
            MenuStack.receiveInput(command);                                 // Send command to game logic
            previousInput.add(0, input);                            // Add receiveInput to history
            inputTextField.setText(EMPTY);                              // Clear receiveInput field
            inputHistoryIndex = 0;
        }
    };
    private KeyAdapter key = new KeyAdapter() {
        private void area(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                // Up - Scroll back from most previous
                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_KP_LEFT:
                    try {
                        String store = previousInput.get(inputHistoryIndex);
                        replaceText(store);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    // System.out.append("UP: Index: " + inputHistoryIndex + " -> ");
                    inputHistoryIndex = (inputHistoryIndex + 1) % (previousInput.size()); // Move up
                    // System.out.println(inputHistoryIndex);
                    e.consume();
                    break;
                // Down - Scroll forward from first receiveInput
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_KP_RIGHT:
                    // System.out.append("DOWN: Index: " + inputHistoryIndex + " -> ");
                    inputHistoryIndex = (inputHistoryIndex - 1) % (previousInput.size()); // Move down
                    if (inputHistoryIndex < 0) { // Ensures positive
                        inputHistoryIndex = previousInput.size() - 1;
                    }
                    try {
                        String store = previousInput.get(inputHistoryIndex);
                        replaceText(store);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    // System.out.println(inputHistoryIndex);
                    e.consume();
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            area(e);
            // debug
            // System.out.println(previousInput);
        }
    };

    /**
     * Default InputPanel constructor
     */
    public InputPanel() {
        previousInput = new ArrayList<String>();
        inputHistoryIndex = 0;
        this.inputTextField = new JTextField(TEXT_MAX_WIDTH);
        font = new Font(NAME, STYLE, SIZE);
        this.inputTextField.setFont(font);
        button = new JButton("Send");

        MenuStack.pushCurrentMenu(MainMenu.getInstance());

        this.inputTextField.addActionListener(action);

        this.inputTextField.addKeyListener(key);

        setAnchor(GridBagConstraints.WEST);
        setFill(GridBagConstraints.HORIZONTAL);
        setWidth(GridBagConstraints.RELATIVE);
        setWeightX(1.0);
        addComponent(inputTextField);
        setAnchor(GridBagConstraints.EAST);
        setFill(GridBagConstraints.NONE);
        setWeightX(0.0);
        addComponent(button);

        // requestFocus();
    }

    /**
     * Returns value of button
     *
     * @return
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Returns value of inputTextField
     *
     * @return
     */
    public JTextField getInputTextField() {
        return this.inputTextField;
    }

    /**
     * Replaces inputTextField text with output string and places caret to end of
     * output string
     *
     * @param output The string to replace inputTextField text
     */
    public void replaceText(String output) {
        inputTextField.setText(output);
        inputTextField.setCaretPosition(output.length());
    }

    /**
     * Sets new value of button
     *
     * @param
     */
    public void setButton(JButton button) {
        this.button = button;
    }

    /**
     * Sets new value of inputTextField
     *
     * @param
     */
    public void setInputTextField(JTextField inputTextField) {
        this.inputTextField = inputTextField;
    }

}
