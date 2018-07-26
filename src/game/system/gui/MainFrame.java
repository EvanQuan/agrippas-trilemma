package game.system.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * The main window of the game. Contains all GUI components
 *
 * @author Evan Quan
 *
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 900;
    public static final int WINDOW_PADDING = 5;

    public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

    public static final int LEFT_X = 0;
    public static final int RIGHT_X = 1;
    public static final int LOGO_Y = 0;
    public static final int LABEL_Y = 1;
    public static final int FIELD_Y = 2;
    public static final int BUTTON_Y = 3;
    public static final int STANDARD_WIDTH = 1;
    public static final int DOUBLE_WIDTH = 2;
    public static final int STANDARD_HEIGHT = 1;
    public static final String GAME_ICON = "../assets/cheese.png";
    public static final String DEFAULT_TITLE = "Cheese Quest:  The Plague of Kashkaval";
    public static final String SAVE_TITLE = "Saving game...";
    public static final String LOAD_TITLE = "Loading game...";
    public static final String PAUSE_ERROR_MESSAGE = "ERROR: Pause was interrupted.";
    public static final String WRITING_ERROR_MESSAGE = "ERROR: Unable to write to file.";
    public static final String ACTION_PERFORMED_ERROR_MESSAGE = "ERROR: Unknown event source. No playerAction performed.";
    // Listeners
    private MainWindowListener windowListener;
    // private MyDocumentListener documentListener;
    // Panels
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    // private MovementPanel MovementPanel;
    // private InventoryPanel inventoryPanel;
    // private GoalPanel GoalPanel;
    // Layout
    private GridBagLayout layout;
    private GridBagConstraints constraint;

    /**
     * Default constructor
     */
    public MainFrame() {
        setGameIcon();
        windowListener = new MainWindowListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                inputPanel.getInputTextField().requestFocus();
            }

            @Override
            public void windowOpened(WindowEvent e) {
                inputPanel.getInputTextField().requestFocus();
            }
        };
        addWindowListener(windowListener); // Applies to only windowOpened()
        addWindowFocusListener(windowListener); // Applies to windowGainedFocus()

        getContentPane().setBackground(BACKGROUND_COLOR);

        outputPanel = OutputPanel.getInstance();
        inputPanel = new InputPanel();
        // documentListener = new DocumentListener();
        // saveButton = new JButton(SAVE_LABEL);
        // clearButton = new JButton(CLEAR_LABEL);
        // nameTextField = new JTextField(NAME_FIELD, NAME_WIDTH);
        // addressTextArea = new JTextArea(ADDRESS_FIELD, ADDRESS_HEIGHT,
        // ADDRESS_WIDTH);
        // addressScrollPane = new JScrollPane(addressTextArea);
        // nameText = null;
        // addressText = null;

        layout = new GridBagLayout();
        setLayout(layout);

        constraint = new GridBagConstraints();
        constraint.insets = new Insets(WINDOW_PADDING, WINDOW_PADDING, WINDOW_PADDING, WINDOW_PADDING);
        // nameLabel = new JLabel(NAME_LABEL);
        // addressLabel = new JLabel(ADDRESS_LABEL);
        // logoLabel = new JLabel(logoIcon);
        // saveButton.setIcon(saveIcon);
        // saveButton.addActionListener(this);
        // clearButton.setIcon(clearIcon);
        // clearButton.addActionListener(this);
        // nameTextField.getDocument().addDocumentListener(aDocumentListener);
        // addressTextArea.setWrapStyleWord(true);
        // addressTextArea.setLineWrap(true);
        setTitle(DEFAULT_TITLE);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        createComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void addComponent(Component aComponent) {
        layout.setConstraints(aComponent, constraint);
        add(aComponent, constraint);
    }

    // /**
    // * Type of playerAction performed is determined by the source of event
    // * @param ActionEvent anEvent determines playerAction to perform
    // */
    // public void actionPerformed(ActionEvent anEvent) {
    // Object actionSource = anEvent.getSource();
    // if (actionSource == saveButton) {
    // pauseWindow(SAVE_TITLE);
    // outputTextToFile();
    // } else if (actionSource == clearButton) {
    // pauseWindow(CLEAR_TITLE);
    // clearTextFields();
    // } else {
    // System.out.println(ACTION_PERFORMED_ERROR_MESSAGE);
    // }
    // }
    // Add a component to inputed coordinates with inputed dimensions using
    // GridBagLayout and GridBagConstraints
    /**
     * Add a component to inputed coordinates with inputed dimensions using
     * GridBagLayout and GridBagConstraints
     *
     * @param Component
     *            aComponent to add to frame
     * @param int
     *            xCoordinate of aComponent
     * @param int
     *            yCoordinate of aComponent
     * @param int
     *            width of aComponent
     * @param int
     *            height of aComponent
     */
    public void addComponent(Component aComponent, int xCoordinate, int yCoordinate, int width, int height) {
        constraint.gridx = xCoordinate;
        constraint.gridy = yCoordinate;
        constraint.gridwidth = width;
        constraint.gridheight = height;
        addComponent(aComponent);
    }

    /**
     * Adds all components to frame to respective locations
     */
    public void createComponents() {
        // addComponent(logoLabel,LEFT_X,LOGO_Y,DOUBLE_WIDTH,STANDARD_HEIGHT);
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 1.0;
        constraint.weighty = 0.0;
        // addComponent(inputPanel);
        addComponent(inputPanel, 0, 1, 1, 1);
        constraint.weighty = 1.0;
        addComponent(outputPanel, 0, 0, 1, 1);
    }
    /**
     * Writes name and address to order file. Takes the current information in the
     * name and address and saves it to a file called 'order.txt' in the same
     * directory
     */
    // public void outputTextToFile() {
    // PrintWriter aPrintWriter;
    // FileWriter aFileWriter;
    // nameText = nameTextField.getText();
    // addressText = addressTextArea.getText();
    // try {
    // aFileWriter = new FileWriter(ORDER_FILE);
    // aPrintWriter = new PrintWriter(aFileWriter);
    // aPrintWriter.println(nameText);
    // aPrintWriter.println(addressText);
    // aFileWriter.close();
    // } catch (IOException anException) {
    // System.out.println(WRITING_ERROR_MESSAGE);
    // }
    // }
    /**
     * Pauses window and sets title to inputed value Resets back to default title
     * after pause
     *
     * @param String
     *            aTitle set while window is paused
     */
    // public void pauseWindow(String aTitle) {
    // try {
    // setTitle(aTitle);
    // Thread.sleep(DELAY_TIME);
    // } catch (InterruptedException anException) {
    // System.out.println(PAUSE_ERROR_MESSAGE);
    // }
    // setTitle(DEFAULT_TITLE);
    // }
    /**
     * Clears title, and name/address fields to be empty
     */

    // public void clearTextFields() {
    // nameTextField.setText(null);
    // addressTextArea.setText(null);
    // }

    /**
     * [setGameIcon description]
     */
    public void setGameIcon() {
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(GAME_ICON)));
        } catch (Exception e) {
            e.printStackTrace();
            // something else wrong
        }
    }

}
