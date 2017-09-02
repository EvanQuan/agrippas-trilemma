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
    // private String remainingString;
    // private String[] remainingWords;
    // private String verb;
    private OutputPanel outputPanel;
    private MenuManager menuManager; // Cannot have menuManeger, or infinite recursion occurs??

    public Menu() {
        // System.out.println("Menu()");
        outputPanel = OutputPanel.getInstance();
        menuManager = MenuManager.getInstance();
        // debug
        // if (menuManager == null) {
        //     System.out.println("    menuManager is NULL");
        // } else {
        //     System.out.println("    menuManager is " + menuManager);
        // }
        // menuManager = MenuManager.getInstance();
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
    public void outputTitle(String output) {
        outputPanel.appendTitle(output);
    }
    public void outputlnTitle(String output) {
        outputTitle(output + "\n");
    }



    // Input processing
    // Words are processed one by one from the start and are stripped away


    private boolean inputStartsWithChoice(ArrayList<String> arrayList, boolean strip) {
        int longestWord = 0;
        String[] words;
        String word;

        for (int i = 0; i < arrayList.size(); i++) {
            words = arrayList.get(i).split(" "); // entries in array may have multiple words
            if (words.length > longestWord) {
                longestWord = words.length;
                word = String.join(" ", Arrays.copyOfRange(inputWords,0,longestWord)); // make start comparison multiple words if need be
                if (wordEquals(word,arrayList)) {
                    if (strip) {
                        stripInput(longestWord);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks if input starts with an element of arrayList
     * If so, strips away starting words for inputWords and returns true
     * @param  ArrayList<String> arrayList
     * @return
     */
    public boolean inputStartsWithStrip(ArrayList<String> arrayList) {
        return inputStartsWithChoice(arrayList, true);
    }
    public boolean inputStartsWithStrip(String[] array) {
        return inputStartsWithStrip(new ArrayList<String>(Arrays.asList(array)));
    }
    public boolean inputStartsWithStrip(String word) {
        return inputStartsWithStrip(new String[] {word});
    }
    // No strip versions
    public boolean inputStartsWith(ArrayList<String> arrayList) {
        return inputStartsWithChoice(arrayList, false);
    }
    public boolean inputStartsWith(String[] array) {
        return inputStartsWith(new ArrayList<String>(Arrays.asList(array)));
    }
    public boolean inputStartsWith(String word) {
        return inputStartsWith(new String[] {word});
    }
    /**
     * Strips away starting words by index from inputWords
     * If more words are to be stripped than exist, then all remaining words are stripped
     * @param int index of new beginning inputWords
     * @return words stripped away from input
     */
    public String stripInput(int index) {
        String output = "";
        if (inputWords.length < index) {
            index = inputWords.length;
        }
        output = String.join(" ", Arrays.copyOfRange(inputWords,0,index));
        inputWords = Arrays.copyOfRange(inputWords,index,inputWords.length);
        return output;

    }
    /**
     * Strips away 1 word from inputWords
     * @return word stripped
     */
    public String stripInput() {
        return stripInput(1);
    }

    public boolean wordEquals(String word, String[] array) {
        return Arrays.asList(array).contains(word.toLowerCase());
    }
    public boolean wordEquals(String word, ArrayList<String> arrayList) {
        return arrayList.contains(word.toLowerCase());
    }
    public boolean wordEquals(String word1, String word2) {
        return word1.equals(word2);
    }
    public boolean inputEquals(String[] array) {
        return wordEquals(getInputString(),array);
    }
    public boolean inputEquals(ArrayList<String> arrayList) {
        return wordEquals(getInputString(),arrayList);
    }
    public boolean inputEquals(String word) {
        return wordEquals(getInputString(),word);
    }
    /**
     * Checks if inputString starts with an element of array
     * set remaining words of inputString to remainingWords
     * @param  String[] array         [description]
     * @return          [description]
     */
    // public boolean verbEquals(String[] array) {
    //     return verbEquals(new ArrayList<String>(Arrays.asList(array)));
    //     // return wordEquals(getVerb(),array);
    // }
    /**
     * Checks if input starts with an element of arrayList
     * If so, sets starting word(s) to verb and rest to remainingWords
     * @param  ArrayList<String> arrayList
     * @return
     */
    // public boolean verbEquals(ArrayList<String> arrayList) {
    //     int longestWord = 0;
    //     String[] words;
    //     String verb; // verb may be multiple words
    //     for (int i = 0; i < arrayList.size(); i++) {
    //         words = arrayList.get(i).split(" "); // entries in array may have multiple words
    //         if (words.length > longestWord) {
    //             longestWord = words.length;
    //             verb = String.join(" ", Arrays.copyOfRange(inputWords,0,longestWord)); // make verb multiple words if need be
    //             if (wordEquals(verb,arrayList)) {
    //                 this.verb = verb;
    //                 makeRemainingWords(longestWord);
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    //     // return wordEquals(getVerb(),arrayList);
    // }
    // public boolean verbEquals(String word) {
    //     return verbEquals(new String[] {word});
    // }
    /**
     * From verb, set remaining words from inputWords to remainingWords
     */
    // public void makeRemainingWords(int start) {
    //     remainingWords = Arrays.copyOfRange(inputWords,start,inputWords.length);
    //     remainingString = String.join(" ",remainingWords);
    // }

    // public boolean remainingWordsEquals(){}
    // public boolean remainingEquals(String[] array) {
    //     return wordEquals(getRemainingString(),array);
    // }
    // public boolean remainingEquals(ArrayList<String> arrayList) {
    //     return wordEquals(getRemainingString(),arrayList);
    // }
    // public boolean remainingEquals(String word) {
    //     return wordEquals(getRemainingString(),word);
    // }

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
    // public String getVerb() {
    //     return this.verb;
    // }

    // public String[] getRemainingWords() {
    //     return this.remainingWords;
    // }
    //
    public String getRemainingString() {
        return String.join(" ", inputWords);
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
        try{
            menuManager.setMenu(TestMenu.getInstance());
        } catch (Exception e) {
            System.out.println("Uh Oh...");
            e.printStackTrace();
        }
    }
    public void changeToLastMenu() {
        menuManager.setMenu(menuManager.getLastMenu());
    }
}
