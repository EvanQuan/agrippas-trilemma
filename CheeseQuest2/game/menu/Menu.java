package game.menu;

import java.lang.StringBuilder;
import java.util.*;
import gui.OutputPanel;

/**
 * Receives input string
 * Outputs corresponding output string
 */
public abstract class Menu {
    public static final int INPUT_SPACING = 4;
    public static final int VERB = 0;
    private static final String INPUT_MARKER = "> ";
    private String inputString;
    private String[] inputWords;
    private OutputPanel outputPanel;
    private MenuManager menuManager;

    public Menu() {
        outputPanel = OutputPanel.getInstance();
        menuManager = MenuManager.getInstance();
    }

    /**
     * Print prompt for user input
     */
    public void outputPrompt() {
    }

    /**
     * Get input from user and stores the line in inputLine
     * and individual words in inputWords
     */
    public void input(String input) {
        this.inputString = input;
        this.inputWords = inputString.split(" ");
        outputPlayerInput();
        processInput();
    }

    /**
     * With inputString and inputWords, determine output
     */
    public void processInput() {

    }

    /**
     * Appends to outputString
     */
    public void output(String output) {
        outputPanel.append(output);
    }
    public void outputln(String output) {
        output(output + "\n");
    }
    public void outputln() {
        outputln("");
    }
    public void outputln(int spacing) {
        for (int i = 0; i < spacing; i++) {
            outputln();
        }
    }
    /**
     * Output player input with input markern and
     */
    public void outputPlayerInput() {
        outputln(INPUT_SPACING);
        outputPanel.append(INPUT_MARKER);
        outputPanel.appendInput(inputString + "\n");
    }
    public void outputPlayerInput(String output) {
        outputln(INPUT_SPACING);
        outputPlayerInputNoSpace(output);
    }
    public void outputPlayerInputNoSpace(String output) {
        outputPanel.append(INPUT_MARKER);
        outputPanel.appendInput(output + "\n");
    }
    public void outputlnPlayer(String output) {
        outputPlayer(output + "\n");
    }
    public void outputPlayer(String output) {
        outputPanel.appendInput(output);
    }
    public void outputItem(String output) {
        outputPanel.appendItem(output);
    }
    public void outputlnItem(String output) {
        outputItem(output + "\n");
    }
    public void outputPerson(String output) {
        outputPanel.appendPerson(output);
    }
    public void outputlnPerson(String output) {
        outputPerson(output + "\n");
    }
    public void outputRoom(String output) {
        outputPanel.appendRoom(output);
    }
    public void outputlnRoom(String output) {
        outputRoom(output + "\n");
    }

    public boolean wordEquals(String word, String[] array) {
        return Arrays.asList(array).contains(word.toLowerCase());
    }
    public boolean inputEquals(String[] array) {
        return wordEquals(getInputString(),array);
    }
    public boolean verbEquals(String[] array) {
        return wordEquals(getVerb(),array);
    }
    public boolean wordEquals(String word, ArrayList<String> arrayList) {
        return arrayList.contains(word.toLowerCase());
    }
    public boolean inputEquals(ArrayList<String> arrayList) {
        return wordEquals(getInputString(),arrayList);
    }
    public boolean verbEquals(ArrayList<String> arrayList) {
        return wordEquals(getVerb(),arrayList);
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
     * Change from current menu to next menu
     */
    public void changeToMainMenu() {
        menuManager.setMenu(MainMenu.getInstance());
    }
    public void changeToGameMenu() {
        menuManager.setMenu(GameMenu.getInstance());
    }
    public void changeToRestartMenu() {
        menuManager.setMenu(RestartMenu.getInstance());
    }
    public void changeToSaveMenu() {
        menuManager.setMenu(SaveMenu.getInstance());
    }
    public void changeToLoadMenu() {
        menuManager.setMenu(LoadMenu.getInstance());
    }
    public void changeToTestMenu() {
        menuManager.setMenu(TestMenu.getInstance());
    }

}
