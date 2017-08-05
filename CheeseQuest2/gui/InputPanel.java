package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Gets input from the user and outputs it to OutputPanel
 */
public class InputPanel extends JPanel implements ActionListener {

	public static final int TEXT_FIELD_WIDTH =
	private JButton inputButton;
	private JTextField inputTextField;
	private OutputPanel outputPanel;

	private String input;
	/**
	 * Sends text of text area when ENTER key is pressed
	 * Works alongside manually pressing inputButton
	 */
	private Action action = AbstractAction(){
		@Override
		public void actionPerformed(ActionEvent e) {
			input = inputTextField.getText();

			outputPanel.getText()
		}
	};

	/**
	 * Default InputPanel constructor
	 */
	public InputPanel(OutputPanel outputPanel) {
		this.inputButton = new JButton("Send");
		this.inputTextField = new JTextField();
		this.inputTextField.addActionListener(action);
		this.outputPanel = outputPanel;
	}


}
