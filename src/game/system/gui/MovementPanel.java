package game.system.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Allows ease of access for player movement during game.
 */
class MovementPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    // Label

    // Buttons
    private JButton northButton;
    private JButton eastButton;
    private JButton westButton;
    private JButton southButton;
    private JButton upButton;
    private JButton downButton;
    private JButton waitButton;

    /**
     * Default MovementPanel constructor
     */
    public MovementPanel() {
        // super(new BorderLayout(2,2));
        northButton = new JButton("N");
        eastButton = new JButton("E");
        westButton = new JButton("W");
        southButton = new JButton("S");
        upButton = new JButton("Up");
        downButton = new JButton("Down");
        waitButton = new JButton("Wait");
        setBorder(new TitledBorder("Movement"));
        addButtons();
    }

    /**
     * Add all buttons to pane
     */
    private void addButtons() {
        add(northButton);
        add(eastButton);
        add(westButton);
        add(southButton);
        add(upButton);
        add(downButton);
        add(waitButton);
    }
}
