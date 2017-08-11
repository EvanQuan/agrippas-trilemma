package game.menu;

import java.lang.StringBuilder;
import java.util.*;

/**
 * Receives input string
 * Outputs corresponding output string
 */
public abstract class Menu {
    public static final int PROMPT_SPACING = 4;
    public static final int VERB = 0;
    private static final String INPUT_MARKER = "> ";
    private String inputString;
    private String[] inputWords;
    private StringBuilder output;
    private Menu nextMenu;
    private boolean inMenu;

    public Menu() {
        inMenu = true;
        output = new StringBuilder();
    }

    /**
     * Start menu prompt, input and process choice
     * Escapes prompt when input is validInput = true
     * Escapes menu when inMenu = false
     */
    // public Menu start() {
    //     boolean validInput;
    //     while (inMenu) {
    //         printMainPrompt();
    //         do {
    //             getInput();
    //             validInput = processInput();
    //             if (!validInput) {
    //                 printInvalidPrompt();
    //             }
    //         } while (!validInput);
    //     }
    // }


    /**
     * Print prompt for user input
     */
    public void printMainPrompt() {
    }

    /**
     * Get input from user and stores the line in inputLine
     * and individual words in inputWords
     */
    public void input(String input) {
        this.inputString = input;
        this.inputWords = inputString.split(" ");
        addInputToOutput();
        processInput();
    }

    /**
     * With inputString and inputWords, determine output
     */
    public void processInput() {
    }
    /**
     * Process input text and determines what to do
     * @return true if valid input, else false
     */
    // public boolean processInput() {
    // }

    /**
     * Appends to outputString
     */
    public void addToOutput(String output) {
        this.output.append(output);
    }
    public void addToOutputln(String output) {
        addToOutput(output + "\n");
    }
    public void addToOutputln() {
        addToOutputln("");
    }
    public void addInputToOutput() {
        for (int i = 0; i < PROMPT_SPACING; i++) {
            addToOutputln();
        }
        addToOutputln(INPUT_MARKER + inputString);
    }
    /**
     * Returns output and clears it
     * @return String output
     */
    public String output() {
        String outputString = output.toString();
        output.setLength(0);
        return outputString;
    }

    public boolean wordEquals(String word, String[] array) {
        return Arrays.asList(array).contains(word);
    }
    public boolean verbEquals(String[] array) {
        return wordEquals(getVerb,array);
    }

    /**
    * Returns value of inputString
    * @return
    */
    public String getInputString() {
        return this.inputString;
    }

    /**
    * Returns value of inputWords
    * @return
    */
    public String[] getInputWords() {
        return this.inputWords;
    }

    /**
     * Returns first word of inputString
     * @return
     */
    public String getVerb() {
        return this.inputWords[VERB];
    }

    /**
    * Returns value of nextMenu
    * @return
    */
    public Menu getNextMenu() {
        return this.nextMenu;
    }

    /**
    * Returns value of inMenu
    * @return
    */
    public boolean isInMenu() {
        return this.inMenu;
    }

    /**
    * Sets new value of nextMenu
    * @param
    */
    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    /**
    * Sets new value of inMenu
    * @param
    */
    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }
}
