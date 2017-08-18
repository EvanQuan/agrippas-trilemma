package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import game.menu.MenuManager;

/**
 * Gets input from the user and outputs it to game
 * Consists of JTextField for input text
 * Can send text with enter key
 *
 * Saves previous input, which can be acquired through uparrow like in terminal
 */
public class InputPanel extends GridBagPanel {
    public static final int TEXT_MAX_WIDTH = 50;
    public static final int TEXT_X = 0;
    public static final int TEXT_Y = 0;
    public static final int TEXT_WIDTH = 0;
    public static final int TEXT_HEIGHT = 0;
    public static final String EMPTY = "";

    // Font
    public static final String NAME = "Consolas";
    public static final int STYLE = Font.BOLD;
    public static final int SIZE = 14;
    public static final Color COLOR = new Color(0, 125, 255);
    private Font font;

    private JTextField inputTextField;
    private OutputPanel outputPanel;
    private JButton button;
    private MenuManager menuManager;

    private ArrayList<String> previousInput;
    private int inputIndex;
    /**
     * Sends text of text area when ENTER key is pressed
     * Works alongside manually pressing inputButton
     */
    private AbstractAction action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputTextField.getText();
            menuManager.input(input);
            previousInput.add(0,input);
            inputTextField.setText(EMPTY);
            inputIndex = 0;
        }
    };
    private KeyAdapter key = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            area(e);
            // debug
            // System.out.println(previousInput);
        }

        private void area(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                // Up - Scrollback from most previous
                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_KP_LEFT:
                    try {
                        String store = previousInput.get(inputIndex);
                        replaceText(store);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    // System.out.print("UP: Index: " + inputIndex + " -> ");
                    inputIndex = (inputIndex + 1) % (previousInput.size()); // Move up
                    // System.out.println(inputIndex);
                    e.consume();
                    break;
                // Down - Scrollforward from first input
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_KP_RIGHT:
                    // System.out.print("DOWN: Index: " + inputIndex + " -> ");
                    inputIndex = (inputIndex - 1) % (previousInput.size()); // Move down
                    if (inputIndex < 0) { // Ensures positive
                        inputIndex = previousInput.size() - 1;
                    }
                    try {
                        String store = previousInput.get(inputIndex);
                        replaceText(store);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    // System.out.println(inputIndex);
                    e.consume();
                    break;
            }
        }
    };
    private String input;

    /**
     * Default InputPanel constructor
     */
    public InputPanel() {
        previousInput = new ArrayList<String>();
        inputIndex = 0;
        this.inputTextField = new JTextField(TEXT_MAX_WIDTH);
        font = new Font(NAME,STYLE,SIZE);
        this.inputTextField.setFont(font);
        button = new JButton("Send");
        menuManager = MenuManager.getInstance();
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

        // outputPanel.append(textManager.output());

        // requestFocus();
    }

    /**
     * Replaces inputTextField text with output string and places caret
     * to end of output string
     * @param String output The string to replace inputTextField text
     */
    public void replaceText(String output) {
        inputTextField.setText(output);
        inputTextField.setCaretPosition(output.length());
    }

	/**
	* Returns value of inputTextField
	* @return
	*/
	public JTextField getInputTextField() {
		return this.inputTextField;
	}


	/**
	* Returns value of button
	* @return
	*/
	public JButton getButton() {
		return this.button;
	}

	/**
	* Sets new value of inputTextField
	* @param
	*/
	public void setInputTextField(JTextField inputTextField) {
		this.inputTextField = inputTextField;
	}

	/**
	* Sets new value of button
	* @param
	*/
	public void setButton(JButton button) {
		this.button = button;
	}

}
