package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import game.menu.MenuManager;

/**
 * Gets input from the user and outputs it to game
 * Consists of JTextField for input text
 * Can send text with enter key
 */
public class InputPanel extends GridBagPanel {
    public static final int TEXT_MAX_WIDTH = 50;
    public static final int TEXT_X = 0;
    public static final int TEXT_Y = 0;
    public static final int TEXT_WIDTH = 0;
    public static final int TEXT_HEIGHT = 0;
    public static final String EMPTY = "";

    private JTextField inputTextField;
    private OutputPanel outputPanel;
    private JButton button;
    private MenuManager menuManager;
/**
     * Sends text of text area when ENTER key is pressed
     * Works alongside manually pressing inputButton
     */
    private AbstractAction action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputTextField.getText();
            menuManager.input(input);
            inputTextField.setText(EMPTY);
        }
    };
    private String input;

    /**
     * Default InputPanel constructor
     */
    public InputPanel() {
        this.inputTextField = new JTextField(TEXT_MAX_WIDTH);
        button = new JButton("Send");
        menuManager = MenuManager.getInstance();
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

        // outputPanel.append(textManager.output());

        // requestFocus();
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
