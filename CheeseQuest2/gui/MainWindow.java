/**
 * Main frame
 *
 * Changelog
 *
 */
import javax.swing.*; // Interface
import java.awt.*; // Listeners
import java.io.*; // File management

public class MainWindow extends JFrame implements ActionListener {
	public static int WINDOW_WIDTH = 600;
	public static int WINDOW_HEIGHT = 500;
	public static int WINDOW_PADDING = 5;
	public static int NAME_WIDTH = 12;
	public static int ADDRESS_WIDTH = 15;
	public static int ADDRESS_HEIGHT = 4;
	public static int DELAY_TIME = 1000;
	public static int LEFT_X = 0;
	public static int RIGHT_X = 1;
	public static int LOGO_Y = 0;
	public static int LABEL_Y = 1;
	public static int FIELD_Y = 2;
	public static int BUTTON_Y = 3;
	public static int STANDARD_WIDTH = 1;
	public static int DOUBLE_WIDTH = 2;
	public static int STANDARD_HEIGHT = 1;
	public static String DEFAULT_TITLE = "Cheese Quest";
	public static String SAVE_TITLE = "Saving game...";
	public static String LOAD_TITLE = "Loading game...";
	public static String SAVE_LABEL = "Save";
	public static String LOAD_LABEL = "Load";
	public static String LOGO_ICON = "logoIcon.png";
	public static String SAVE_ICON = "saveIcon.png";
	public static String CLEAR_ICON = "clearIcon.png";
	public static String ORDER_FILE = "order.txt";
	public static String PAUSE_ERROR_MESSAGE = "ERROR: Pause was interrupted.";
	public static String WRITING_ERROR_MESSAGE = "ERROR: Unable to write to file.";
	public static String ACTION_PERFORMED_ERROR_MESSAGE = "ERROR: Unknown event source. No action performed.";
	// Listener
	private MyWindowListener aWindowListener;
	private MyDocumentListener aDocumentListener;
	// Buttons
	private JButton saveButton;
	private JButton clearButton;
	// Text input fields
	private JTextField nameTextField;
	private JTextArea addressTextArea;
	private String nameText;
	private String addressText;
	private JScrollPane addressScrollPane;
	// Layout
	private GridBagLayout aLayout;
	private GridBagConstraints aConstraint;

	/**
	 * Default constructor
	 */
	public MyFrame() {
		aWindowListener = new MyWindowListener();
		aDocumentListener = new MyDocumentListener();
		saveButton = new JButton(SAVE_LABEL);
		clearButton = new JButton(CLEAR_LABEL);
		nameTextField = new JTextField(NAME_FIELD, NAME_WIDTH);
		addressTextArea = new JTextArea(ADDRESS_FIELD, ADDRESS_HEIGHT, ADDRESS_WIDTH);
		addressScrollPane = new JScrollPane(addressTextArea);
		nameText = null;
		addressText = null;
		aLayout = new GridBagLayout();
		aConstraint = new GridBagConstraints();
		logoIcon = new ImageIcon(LOGO_ICON);
		saveIcon = new ImageIcon(SAVE_ICON);
		clearIcon = new ImageIcon(CLEAR_ICON);
		nameLabel = new JLabel(NAME_LABEL);
		addressLabel = new JLabel(ADDRESS_LABEL);
		logoLabel = new JLabel(logoIcon);
		saveButton.setIcon(saveIcon);
		saveButton.addActionListener(this);
		clearButton.setIcon(clearIcon);
		clearButton.addActionListener(this);
		nameTextField.getDocument().addDocumentListener(aDocumentListener);
		addressTextArea.setWrapStyleWord(true);
		addressTextArea.setLineWrap(true);
		aConstraint.insets = new Insets(WINDOW_PADDING,WINDOW_PADDING,WINDOW_PADDING,WINDOW_PADDING);
		addWindowListener(aWindowListener);
		setTitle(DEFAULT_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(aLayout);
		createComponents();
		setVisible(true);
	}
	/**
	 * Adds all components to frame to respective locations
	 */
	public void createComponents() {
		// Logo
		addComponent(logoLabel,LEFT_X,LOGO_Y,DOUBLE_WIDTH,STANDARD_HEIGHT);
		// Text labels
		addComponent(nameLabel,LEFT_X,LABEL_Y,STANDARD_WIDTH,STANDARD_HEIGHT);
		addComponent(addressLabel,RIGHT_X,LABEL_Y,STANDARD_WIDTH,STANDARD_HEIGHT);
		// Text fields
		addComponent(nameTextField,LEFT_X,FIELD_Y,STANDARD_WIDTH,STANDARD_HEIGHT);
		addComponent(addressScrollPane,RIGHT_X,FIELD_Y,STANDARD_WIDTH,STANDARD_HEIGHT);
		// Buttons
		addComponent(saveButton,LEFT_X,BUTTON_Y,STANDARD_WIDTH,STANDARD_HEIGHT);
		addComponent(clearButton,RIGHT_X,BUTTON_Y,STANDARD_WIDTH,STANDARD_HEIGHT);
	}
	/**
	 * Type of action performed is determed by the source of event
	 * @param ActionEvent anEvent determines action to perform
	 */
	public void actionPerformed(ActionEvent anEvent) {
		Object actionSource = anEvent.getSource();
		if (actionSource == saveButton) {
			pauseWindow(SAVE_TITLE);
			outputTextToFile();
		} else if (actionSource == clearButton) {
			pauseWindow(CLEAR_TITLE);
			clearTextFields();
		} else {
			System.out.println(ACTION_PERFORMED_ERROR_MESSAGE);
		}
	}
	// Add a component to inputted coordinates with inputted dimensions using GridBagLayout and GridBagConstraints
	/**
	 * Add a component to inputted coordinates with inpuuted dimensions using GridBagLayout and GridBagConstraints
	 * @param Component aComponent  to add to frame
	 * @param int       xCoordinate of aComponent
	 * @param int       yCoordinate of aComponent
	 * @param int       width       of aComponent
	 * @param int       height      of aComponent
	 */
	public void addComponent(Component aComponent, int xCoordinate, int yCoordinate, int width, int height) {
		aConstraint.gridx = xCoordinate;
		aConstraint.gridy = yCoordinate;
		aConstraint.gridwidth = width;
		aConstraint.gridheight = height;
		aLayout.setConstraints(aComponent,aConstraint);
		add(aComponent, aConstraint);
	}
	/**
	 * Writes name and address to order file.
	 * Takes the current information in the name and address and saves it to a file called 'order.txt'  in the same directory
	 */
	public void outputTextToFile() {
		PrintWriter aPrintWriter;
		FileWriter aFileWriter;
		nameText = nameTextField.getText();
		addressText = addressTextArea.getText();
		try {
			aFileWriter = new FileWriter(ORDER_FILE);
			aPrintWriter = new PrintWriter(aFileWriter);
			aPrintWriter.println(nameText);
			aPrintWriter.println(addressText);
			aFileWriter.close();
		} catch (IOException anException) {
			System.out.println(WRITING_ERROR_MESSAGE);
		}
	}
	/**
	 * Pauses window and sets title to inputted value
	 * Resets back to default title after pause
	 * @param String aTitle set while window is paused
	 */
	public void pauseWindow(String aTitle) {
		try {
			setTitle(aTitle);
			Thread.sleep(DELAY_TIME);
		} catch (InterruptedException anException) {
			System.out.println(PAUSE_ERROR_MESSAGE);
		}
		setTitle(DEFAULT_TITLE);
	}
	/**
	 * Clears title, and name/address fields to be empty
	 */
	public void clearTextFields() {
		nameTextField.setText(null);
		addressTextArea.setText(null);
	}
}
