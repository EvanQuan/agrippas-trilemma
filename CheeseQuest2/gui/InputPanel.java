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
    public static final int TEXT_WIDTH = 0;
    public static final int TEXT_HEIGHT = 0;
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
            outputPanel.appendItem(textManager.output());
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

        outputPanel.append(textManager.output());

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
	* Returns value of outputPanel
	* @return
	*/
	public OutputPanel getOutputPanel() {
		return this.outputPanel;
	}

	/**
	* Returns value of button
	* @return
	*/
	public JButton getButton() {
		return this.button;
	}

	/**
	* Returns value of textManager
	* @return
	*/
	public TextManager getTextManager() {
		return this.textManager;
	}

	/**
	* Sets new value of inputTextField
	* @param
	*/
	public void setInputTextField(JTextField inputTextField) {
		this.inputTextField = inputTextField;
	}

	/**
	* Sets new value of outputPanel
	* @param
	*/
	public void setOutputPanel(OutputPanel outputPanel) {
		this.outputPanel = outputPanel;
	}

	/**
	* Sets new value of button
	* @param
	*/
	public void setButton(JButton button) {
		this.button = button;
	}

	/**
	* Sets new value of textManager
	* @param
	*/
	public void setTextManager(TextManager textManager) {
		this.textManager = textManager;
	}

}
