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

    // Player input font
    public static final String INPUT_NAME = "Consolas";
    public static final int INPUT_STYLE = Font.BOLD;
    public static final int INPUT_SIZE = DEFAULT_SIZE;
    public static final Color INPUT_COLOR = new Color(0, 125, 255); // medium blue

    // Special font
    public static final String SPECIAL_NAME = "Consolas";
    public static final int SPECIAL_STYLE = Font.BOLD;
    public static final int SPECIAL_SIZE = DEFAULT_SIZE;
    public static final Color ITEM_COLOR = new Color(185, 0, 0); // red
    public static final Color PERSON_COLOR = new Color(60, 150, 130); // green
    public static final Color ROOM_COLOR = new Color(255, 153, 0); // orange
    public static final int SPEECH_STYLE = Font.ITALIC;
    public static final Color SPEECH_COLOR = new Color(120, 57, 145); // purple

    // Border font
    public static final String BORDER_NAME = "SansSerif";
    public static final int BORDER_STYLE = Font.BOLD;
    public static final int BORDER_SIZE = 24;
    public static final Color BORDER_COLOR = Color.RED;

    // Title font
    public static final String TITLE_NAME = "Consolas";
    public static final int TITLE_STYLE = Font.BOLD;
    public static final int TITLE_SIZE = DEFAULT_SIZE + 2;
    public static final Color TITLE_COLOR = ITEM_COLOR;

    // Speech font


    private JTextPane textPane;
    // private JTextArea textArea;
    private JScrollPane scrollPane;
    private TitledBorder border;
    private Font defaultFont;
    private Font inputFont;
    private Font specialFont;
    private Font borderFont;
    private Font titleFont;
    private Font speechFont;

    private static OutputPanel outputPanel = new OutputPanel();

    private OutputPanel() {
        initFont();
        initTextPane();
        // initBorder(); // text pane does not have a border?
    }

    public static OutputPanel getInstance() {
        return outputPanel;
    }

    private void initFont() {
        defaultFont = new Font(DEFAULT_NAME,DEFAULT_STYLE,DEFAULT_SIZE);
        inputFont = new Font(INPUT_NAME,INPUT_STYLE,INPUT_SIZE);
        specialFont = new Font(SPECIAL_NAME,SPECIAL_STYLE,SPECIAL_SIZE);
        borderFont = new Font(BORDER_NAME,BORDER_STYLE,BORDER_SIZE);
        titleFont = new Font(TITLE_NAME,TITLE_SIZE,TITLE_SIZE);
        speechFont = new Font(SPECIAL_NAME,SPEECH_STYLE,SPECIAL_SIZE);
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
        border.setTitleFont(borderFont);
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

        // debug
        // System.out.println(attributeSet);

        try {
        doc.insertString(length, string, attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }
    public void append(String string) {
        append(string,defaultFont,DEFAULT_COLOR);
    }
    public void appendInput(String string) {
        append(string,inputFont,INPUT_COLOR);
    }
    public void appendItem(String string) {
        append(string,specialFont,ITEM_COLOR);
    }
    public void appendPerson(String string) {
        append(string,specialFont,PERSON_COLOR);
    }
    public void appendRoom(String string) {
        append(string,specialFont,ROOM_COLOR);
    }
    public void appendTitle(String string) {
        append(string,titleFont,TITLE_COLOR);
    }
    public void appendSpeech(String string) {
        append(string,speechFont,SPEECH_COLOR);
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

	/**
	* Returns value of textPane
	* @return
	*/
	public JTextPane getTextPane() {
		return this.textPane;
	}

	/**
	* Returns value of scrollPane
	* @return
	*/
	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}

	/**
	* Returns value of border
	* @return
	*/
	public TitledBorder getBorder() {
		return this.border;
	}

	/**
	* Returns value of defaultFont
	* @return
	*/
	public Font getDefaultFont() {
		return this.defaultFont;
	}

	/**
	* Returns value of inputFont
	* @return
	*/
	public Font getInputFont() {
		return this.inputFont;
	}

	/**
	* Returns value of specialFont
	* @return
	*/
	public Font getSpecialFont() {
		return this.specialFont;
	}

	/**
	* Returns value of borderFont
	* @return
	*/
	public Font getBorderFont() {
		return this.borderFont;
	}

	/**
	* Returns value of outputPanel
	* @return
	*/
	public static OutputPanel getOutputPanel() {
		return outputPanel;
	}

	/**
	* Sets new value of textPane
	* @param
	*/
	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}

	/**
	* Sets new value of scrollPane
	* @param
	*/
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	* Sets new value of border
	* @param
	*/
	public void setBorder(TitledBorder border) {
		this.border = border;
	}

	/**
	* Sets new value of defaultFont
	* @param
	*/
	public void setDefaultFont(Font defaultFont) {
		this.defaultFont = defaultFont;
	}

	/**
	* Sets new value of inputFont
	* @param
	*/
	public void setInputFont(Font inputFont) {
		this.inputFont = inputFont;
	}

	/**
	* Sets new value of specialFont
	* @param
	*/
	public void setSpecialFont(Font specialFont) {
		this.specialFont = specialFont;
	}

	/**
	* Sets new value of borderFont
	* @param
	*/
	public void setBorderFont(Font borderFont) {
		this.borderFont = borderFont;
	}

	/**
	* Sets new value of outputPanel
	* @param
	*/
	public static void setOutputPanel(OutputPanel outputPanel) {
		OutputPanel.outputPanel = outputPanel;
	}
}
