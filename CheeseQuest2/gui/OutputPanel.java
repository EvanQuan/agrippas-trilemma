package gui;

import javax.swing.JScrollPane;

public class OutputPanel extends JPanel {
    public static final int TEXT_AREA_WIDTH = 50;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public OutputPanel() {
        textArea = new JTextArea(TEXT_AREA_WIDTH);
        scrollPane = new JScrollPane();
    }
}
