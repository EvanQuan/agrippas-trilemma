package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Output of game text
 * Has a scroll pane
 */
public class OutputPanel extends GridBagPanel {
    public static final int TEXT_AREA_HEIGHT = 0;
    public static final int TEXT_AREA_WIDTH = 0;
    public static final int TEXT_X = 0;
    public static final int TEXT_Y = 0;
    public static final int TEXT_WIDTH = 1;
    public static final int TEXT_HEIGHT = 1;

    // Default font
    public static final String DEFAULT_NAME = "Consolas";
    public static final int DEFAULT_STYLE = Font.PLAIN;
    public static final int DEFAULT_SIZE = 16;
    public static final Color DEFAULT_COLOR = Color.BLACK;

    // Special font
    public static final String SPECIAL_NAME = "Consolas";
    public static final int SPECIAL_STYLE = Font.ITALIC;
    public static final int SPECIAL_SIZE = 16;
    public static final Color ITEM_COLOR = Color.RED;
    public static final Color PERSON_COLOR = Color.GREEN;
    public static final Color ROOM_COLOR = Color.BLUE;

    // Border font
    public static final String BORDER_NAME = "SansSerif";
    public static final int BORDER_STYLE = Font.BOLD;
    public static final int BORDER_SIZE = 24;
    public static final Color BORDER_COLOR = Color.RED;


    private JTextPane textPane;
    // private JTextArea textArea;
    private JScrollPane scrollPane;
    private TitledBorder border;
    public static Font DEFAULT_FONT;
    public static Font SPECIAL_FONT;
    public static Font BORDER_FONT;

    public OutputPanel() {
        initFont();

        initTextPane();
        // initTextArea();
        initBorder();
    }

    private void initFont() {
        DEFAULT_FONT = new Font(DEFAULT_NAME,DEFAULT_STYLE,DEFAULT_SIZE);
        SPECIAL_FONT = new Font(SPECIAL_NAME,SPECIAL_STYLE,SPECIAL_SIZE);
        BORDER_FONT = new Font(BORDER_NAME,BORDER_STYLE,BORDER_SIZE);
    }


    private void initTextPane() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        scrollPane = new JScrollPane(textPane);
        setFill(GridBagConstraints.BOTH);
        setWeightX(1.0);
        setWeightY(1.0);
        addComponent(scrollPane,TEXT_X,TEXT_Y,TEXT_WIDTH,TEXT_HEIGHT);
    }
    // private void initTextArea() {
    //     textArea = new JTextArea(TEXT_AREA_HEIGHT,TEXT_AREA_WIDTH);
    //     textArea.setEditable(false);
    //     textArea.setLineWrap(true);
    //     textArea.setWrapStyleWord(true);
    //     textArea.setFont(font);
    //     scrollPane = new JScrollPane(textArea);
    //     setFill(GridBagConstraints.BOTH);
    //     setWeightX(1.0);
    //     setWeightY(1.0);
    //     addComponent(scrollPane,TEXT_X,TEXT_Y,TEXT_WIDTH,TEXT_HEIGHT);
    // }

    private void initBorder() {
        border = new TitledBorder("Main Menu");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleFont(BORDER_FONT);
        border.setTitleColor(BORDER_COLOR);
        setBorder(border);
    }


    /**
     * Append text to textArea
     * @param String s to output
     */
    // public void append(String s) {
    //     textArea.setText(textArea.getText() + s);
    // }

    /**
     * Append text to textPane with given font and color
     * @param String string to append to outputPanel
     * @param Font   font   of string
     * @param Color  color  of string
     */
    public void append(String string, Font font, Color color) {
        StyledDocument doc = textPane.getStyledDocument();



        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, color);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, font.getName());
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontSize, font.getSize());
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Bold, font.isBold());
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Italic, font.isItalic());

        // if (font.isBold()) {
        //     StyleConstants.setBold(attributeSet, true);
        // } else if (font.isItalic()) {
        //     StyleConstants.setItalic(attributeSet, true);
        // }

        int length = textPane.getDocument().getLength();
        textPane.setCaretPosition(length);
        textPane.setCharacterAttributes(attributeSet, false);
        // textPane.replaceSelection(string);

        // Print all output to command line
        // System.out.println(string);

        System.out.println(attributeSet);

        try {
        doc.insertString(length, string, attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }
    public void append(String string) {
        append(string,DEFAULT_FONT,DEFAULT_COLOR);
    }
    public void appendItem(String string) {
        append(string,SPECIAL_FONT,ITEM_COLOR);
    }
    public void appendPerson(String string) {
        append(string,SPECIAL_FONT,PERSON_COLOR);
    }
    public void appendRoom(String string) {
        append(string,SPECIAL_FONT,ROOM_COLOR);
    }

    /**
     * Utility method for setting the font and color of a JTextPane. The
     * result is roughly equivalent to calling setFont(...) and
     * setForeground(...) on an AWT TextArea.
     *
     * Source
     * http://javatechniques.com/blog/setting-jtextpane-font-and-color/
     */
    // public void setJTextPaneFont(Font font, Color c) {
    //     // Start with the current input attributes for the JTextPane. This
    //     // should ensure that we do not wipe out any existing attributes
    //     // (such as alignment or other paragraph attributes) currently
    //     // set on the text area.
    //     MutableAttributeSet attrs = this.textPane.getInputAttributes();
    //
    //     // Set the font family, size, and style, based on properties of
    //     // the Font object. Note that JTextPane supports a number of
    //     // character attributes beyond those supported by the Font class.
    //     // For example, underline, strike-through, super- and sub-script.
    //     StyleConstants.setFontFamily(attrs, font.getFamily());
    //     StyleConstants.setFontSize(attrs, font.getSize());
    //     StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
    //     StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
    //
    //     // Set the font color
    //     StyleConstants.setForeground(attrs, c);
    //
    //     // Retrieve the pane's document object
    //     StyledDocument doc = this.textPane.getStyledDocument();
    //
    //     // Replace the style for the entire document. We exceed the length
    //     // of the document by 1 so that text entered at the end of the
    //     // document uses the attributes.
    //     doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    // }
}
