package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Receives a {@link PlayerCommand}. Directly outputs to its static {@link IPrintBuffer}.
 */
public abstract class Menu {

    /**
     * Output
     */
    public static IPrintBuffer out;

    /**
     * Individual inputs correspond to runnable actions for the menu to do. Each runnable should call a menu method.
     */
    protected HashMap<String, Runnable> commands;

    protected ArrayList<ArrayList> validVerbs;

    // Input options
    protected String[] yes;
    protected String[] no;
    protected String[] returnToPreviousMenu;

    /**
     *
     * @throws RuntimeException
     */
    protected Menu() throws RuntimeException {
        if (out == null) {
            throw new RuntimeException("Cannot instantiate any menus until an IPrintBuffer is set for all menus via " +
                    "Menu.setOut().");
        }
        commands = new HashMap<>();
        initializeCommands();
        // validVerbs = new ArrayList<ArrayList<String>>();
        yes = new String[] { "yes", "y", "yeah", "yee", "yup" };
        no = new String[] { "no", "n", "nay", "nope" };
        returnToPreviousMenu = new String[] { "return", "r", "return to previous menu", "return menu",
                "return to menu" };
    }

    /**
     * Set the {@link IPrintBuffer} for all menus to output to.
     *
     * @param printBuffer
     */
    public static void setOut(IPrintBuffer printBuffer) {
        out = printBuffer;
    }

    /**
     * Set the {@link MenuManager}s current menu. This will immediately change the menu after the current menu's
     * receiveInput is done processing.
     *
     * @param menu
     */
    public void changeTo(Menu menu) {
        MenuManager.pushCurrentMenu(menu);
    }

    /**
     * Change the {@link MenuManager}s current menu to the previous menu.
     */
    public void changeToPreviousMenu() {
        MenuManager.pushCurrentMenu(MenuManager.getPreviousMenu());
    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some corresponding output to this menu's currently
     * set {@link game.system.output.IPrintBuffer}. This is occurs after preprocessInput() is called and succeeds.
     *
     * @param playerCommand to processInput
     */
    protected abstract void processInput(PlayerCommand playerCommand);

    /**
     * Create all valid commands for this menu. Use addCommand().
     */
    protected abstract void initializeCommands();

    /**
     * Every command is composed of a list of possible inputs options that correspond with Menu method for the Menu to
     * execute. As a result, be careful not to have commands share options, or an option will be overridden
     *
     * @param options all strings that corresponding to method. All are converted to lower case.
     * @param method with no parameters and Menu method in body
     */
    public void addCommand(String[] options, Runnable method) {
        for (String option : options) {
            commands.put(option.toLowerCase(), method);
        }
    }

    /**
     * Retrieve and process a player command.
     *
     * @param playerCommand
     */
    public final void receiveInput(PlayerCommand playerCommand) {
        if (preprocessInput(playerCommand)) {
            processInput(playerCommand);
            postprocessInput(playerCommand);
        }
    }

    /**
     * Checks player command before processing. By default, checks if the command is empty. If so, output the main
     * prompt and skip processInput() and postprocessInput().
     *
     * @param playerCommand
     * @return true if pre-process was successful.
     */
    protected boolean preprocessInput(PlayerCommand playerCommand) {
        if (playerCommand.isEmpty()) {
            appendPrompt();
        }
        return !playerCommand.isEmpty();
    }

    /**
     * Retrieves information about the playerCommand after it has been process. This may influence how future
     * commands are processed. This is only ran if preprocessInput() is successful. By default this does nothing.
     *
     * @param playerCommand
     */
    protected void postprocessInput(PlayerCommand playerCommand) {

    }

    /**
     * Appends prompt to output {@link IPrintBuffer}. This should be called every time a {@link Menu}is changed in
     * {@link MenuManager} to signify to the user that the menu has changed, and what input is appropriate for the
     * given menu.
     */
    public abstract void appendPrompt();

    // /**
    // * Returns value of inputString
    // *
    // * @return
    // */
    // public String getInputString() {
    // return this.inputString;
    // }
    //
    // /**
    // * Returns value of inputWords
    // *
    // * @return
    // */
    // public String[] getInputWords() {
    // return this.inputWords;
    // }
    //
    // /**
    // * Returns value of originalInputWords
    // *
    // * @return
    // */
    // public String[] getOriginalInputWords() {
    // return this.originalInputWords;
    // }
    //
    // // public String[] getRemainingWords() {
    // // return this.remainingWords;
    // // }
    // //
    // public String getRemainingString() {
    // return String.join(" ", inputWords);
    // }

    // /**
    // * Returns verb of inputString
    // *
    // * @return
    // */
    // public String getVerb() {
    // return this.verb;
    // }
    //
    // private boolean inputStartsWithDoubleChoice(ArrayList<Double> arrayList,
    // boolean strip) {
    // return
    // inputStartsWithChoice(ArrayUtils.getStringArrayListFromDouble(arrayList),
    // strip);
    // }
    //
    // /**
    // * Different names to prevent name clash with inputStartsWithChoice()
    // *
    // * @param ArrayList<Integer>
    // * arrayList
    // * @param boolean
    // * strip
    // * @return
    // */
    // private boolean inputStartsWithIntegerChoice(ArrayList<Integer> arrayList,
    // boolean strip) {
    // return inputStartsWithChoice(ArrayUtils.getStringArrayList(arrayList),
    // strip);
    // }

    // /**
    // * Get receiveInput from user and stores the line in inputLine and individual words
    // in
    // * inputWords
    // */
    // public void receiveInput(String receiveInput) {
    // this.inputString = receiveInput;
    // this.originalInputString = inputString;
    // this.inputWords = inputString.split(" ");
    // this.originalInputWords = inputWords;
    // this.verb = EMPTY;
    // outputPlayerInput();
    // preProcessInput();
    // }

    // public boolean inputEquals(ArrayList arrayList) {
    // return wordEquals(getInputString(),
    // ArrayUtils.getStringArrayList(arrayList));
    // }
    //
    // public boolean inputEquals(double word) {
    // return wordEquals(getInputString(), Double.toString(word));
    // }
    //
    // public boolean inputEquals(double[] array) {
    // return wordEquals(getInputString(), ArrayUtils.getStringArrayList(array));
    // }
    //
    // public boolean inputEquals(int word) {
    // return wordEquals(getInputString(), Integer.toString(word));
    // }
    //
    // public boolean inputEquals(int[] array) {
    // return wordEquals(getInputString(), ArrayUtils.getStringArrayList(array));
    // }
    //
    // public boolean inputEquals(String word) {
    // return wordEquals(getInputString(), word);
    // }
    //
    // public boolean inputEquals(String[] array) {
    // return wordEquals(getInputString(), array);
    // }
    //
    // /**
    // * Checks if there exists remaining words in inputWords to parse
    // *
    // * @return true if inputWords.length > 0
    // */
    // public boolean inputRemains() {
    // return inputWords.length > 0;
    // }
    //
    // // No strip versions
    // public boolean inputStartsWith(ArrayList arrayList) {
    // return inputStartsWithChoice(arrayList, false, false);
    // }
    //
    // public boolean inputStartsWith(double word) {
    // return inputStartsWith(new double[] { word });
    // }
    //
    // // public boolean inputStartsWithDouble(ArrayList<Double> arrayList) {
    // // return inputStartsWithDoubleChoice(arrayList, false);
    // // }
    // public boolean inputStartsWith(double[] array) {
    // return inputStartsWith(ArrayUtils.getDoubleArrayList(array));
    // }
    //
    // public boolean inputStartsWith(int word) {
    // return inputStartsWith(new int[] { word });
    // }
    //
    // // public boolean inputStartsWithInteger(ArrayList<Integer> arrayList) {
    // // return inputStartsWithIntegerChoice(arrayList, false);
    // // }
    // public boolean inputStartsWith(int[] array) {
    // return inputStartsWith(ArrayUtils.getIntegerArrayList(array));
    // }
    //
    // public boolean inputStartsWith(String word) {
    // return inputStartsWith(new String[] { word });
    // }
    //
    // public boolean inputStartsWith(String[] array) {
    // return inputStartsWith(new ArrayList<String>(Arrays.asList(array)));
    // }
    //
    // // TODO: Make stripping pick the longest element of array to strip, not the
    // // first one
    // // Input processing
    // // Words are processed one by one from the start and are stripped away
    // // Verb can be manually set or is automatically defined upon new player receiveInput
    // /**
    // * Checks if receiveInput starts with an element of arrayList
    // *
    // * @param ArrayList
    // * arrayList
    // * @param boolean
    // * strip to determine if element is stripped from inputWords
    // * @return true if receiveInput starts with element of arrayList
    // */
    // private boolean inputStartsWithChoice(ArrayList arrayList, boolean strip,
    // boolean setVerb) {
    // // debug
    // // System.out.println("inputString: " + inputString);
    // // System.out.println("inputWords: " + Arrays.asList(inputWords));
    // // System.out.println("verb: " + verb);
    // // System.out.println("arrayList: " + arrayList);
    // // System.out.println("strip: " + strip);
    // // System.out.println("setVerb: " + setVerb);
    // if (arrayList.size() == 0) {
    // arrayList.add(EMPTY);
    // }
    // // int longestWord = 0;
    // String[] words;
    // String word;
    // String tempVerb = EMPTY;
    // Object testElement = arrayList.get(0);
    // if (testElement instanceof String) {
    // sortByLongestFirst(arrayList);
    // for (int i = 0; i < arrayList.size(); i++) { // search for entry with most
    // words
    // words = ((String) arrayList.get(i)).split(" "); // entries in array may have
    // multiple words
    // // if (words.length > longestWord) { // update longest word
    // // longestWord = words.length;
    // word = String.join(" ", Arrays.copyOfRange(inputWords, 0, words.length)); //
    // make start comparison
    // // multiple words if need be
    // if (wordEquals(word, arrayList)) {
    // if (strip) {
    // tempVerb = stripInput(words.length);
    // }
    // if (setVerb || this.verb.equals(EMPTY)) {
    // this.verb = tempVerb;
    // }
    // return true;
    // }
    // // }
    // }
    // } else if (testElement instanceof Number) {
    // if (testElement instanceof Integer) {
    // for (int i = 0; i < arrayList.size(); i++) {
    // if (getStringArrayList(inputWords).contains(Integer.toString((int)
    // arrayList.get(i)))) {
    // return true;
    // }
    // }
    // } else if (testElement instanceof Double) {
    // for (int i = 0; i < arrayList.size(); i++) {
    // if (getStringArrayList(inputWords).contains(Double.toString((double)
    // arrayList.get(i)))) {
    // return true;
    // }
    // }
    // }
    // }
    // return false;
    // }
    //
    // /**
    // * Checks if receiveInput starts with an element of arrayList If so, strips away
    // * starting words for inputWords and returns true
    // *
    // * @param ArrayList<String>
    // * arrayList
    // * @return
    // */
    // public boolean inputStartsWithStrip(ArrayList arrayList) {
    // return inputStartsWithChoice(arrayList, true, false);
    // }
    //
    // public boolean inputStartsWithStrip(double word) {
    // return inputStartsWithStrip(getStringArrayList(word));
    // }
    //
    // // public boolean inputStartsWithDoubleStrip(ArrayList<Double> arrayList) {
    // // return inputStartsWithDoubleChoice(arrayList, true);
    // // }
    // public boolean inputStartsWithStrip(double[] array) {
    // return inputStartsWithStrip(getStringArrayList(array));
    // }
    //
    // public boolean inputStartsWithStrip(int word) {
    // return inputStartsWithStrip(getStringArrayList(word));
    // }
    //
    // // public boolean inputStartsWithIntegerStrip(ArrayList<Integer> arrayList) {
    // // return inputStartsWithIntegerChoice(arrayList, true);
    // // }
    // public boolean inputStartsWithStrip(int[] array) {
    // return inputStartsWithStrip(getStringArrayList(array));
    // }
    //
    // public boolean inputStartsWithStrip(String word) {
    // return inputStartsWithStrip(getStringArrayList(word));
    // }
    //
    // public boolean inputStartsWithStrip(String[] array) {
    // return inputStartsWithStrip(getStringArrayList(array));
    // }
    //
    // public boolean inputStartsWithStripVerb(ArrayList arrayList) {
    // return inputStartsWithChoice(arrayList, true, true);
    // }
    //
    // public boolean inputStartsWithStripVerb(double[] array) {
    // return inputStartsWithStripVerb(getStringArrayList(array));
    // }
    //
    // public boolean inputStartsWithStripVerb(int word) {
    // return inputStartsWithStripVerb(getStringArrayList(word));
    // }
    //
    // public boolean inputStartsWithStripVerb(int[] array) {
    // return inputStartsWithStripVerb(getStringArrayList(array));
    // }
    //
    // public boolean inputStartsWithStripVerb(String word) {
    // return inputStartsWithStripVerb(getStringArrayList(word));
    // }
    //
    // public boolean inputStartsWithStripVerb(String[] array) {
    // return inputStartsWithStripVerb(getStringArrayList(array));
    // }
    //
    // public boolean inputStartsWithVerb(ArrayList arrayList) {
    // return inputStartsWithChoice(arrayList, false, true);
    // }

    // /**
    // * If the use has too many words at the end of a command
    // */
    // public void outputExcessCommand() {
    // append("I only understand as far as you wanting to \"");
    // printPlayer(getVerb());
    // println(".\"");
    // }
    //
    // /**
    // * If the user has an incomplete command, prompt to complete command
    // */
    // public void outputIncompleteCommand() {
    // append(toTitleCase(verb));
    // println(" what?");
    // }
    //
    // public void outputIncompleteCommandAndReprompt() {
    // outputIncompleteCommand();
    // println();
    // appendPrompt();
    // }
    //
    // /**
    // * Output player receiveInput with receiveInput marker
    // */
    // public void outputPlayerInput() {
    // printlns(INPUT_SPACING);
    // outputPanel.append(INPUT_MARKER);
    // outputPanel.appendInput(inputString + "\n");
    // }
    //


    // public boolean remainingWordsEquals(){}
    // public boolean remainingEquals(String[] array) {
    // return wordEquals(getRemainingString(),array);
    // }
    // public boolean remainingEquals(ArrayList<String> arrayList) {
    // return wordEquals(getRemainingString(),arrayList);
    // }
    // public boolean remainingEquals(String word) {
    // return wordEquals(getRemainingString(),word);
    // }

    //
    // /**
    // * Strips away starting words by index from inputWords and inputString If more
    // * words are to be stripped than exist, then all remaining words are stripped
    // *
    // * @param int
    // * index of new beginning inputWords
    // * @return words stripped away from receiveInput
    // */
    // public String stripInput(int index) {
    // String output = "";
    // if (inputWords.length < index) {
    // index = inputWords.length;
    // }
    // output = String.join(" ", Arrays.copyOfRange(inputWords, 0, index));
    // inputWords = Arrays.copyOfRange(inputWords, index, inputWords.length);
    // inputString = String.join(" ", inputWords);
    // return output;
    //
    // }

    // /**
    // * TODO Checks if unknown word is used
    // *
    // * @return true if
    // */
    // public boolean validInput() {
    // return true; // TODO
    // }
    //
    // /**
    // * Checks if receiveInput starts with an element of arrayList If so, sets starting
    // * word(s) to verb and rest to remainingWords
    // *
    // * @param ArrayList<String>
    // * arrayList
    // * @return
    // */
    // public boolean verbEquals(ArrayList<String> arrayList) {
    // return wordEquals(verb, arrayList);
    // }
    // /**
    // * From verb, set remaining words from inputWords to remainingWords
    // */
    // public void makeRemainingWords(int start) {
    // remainingWords = Arrays.copyOfRange(inputWords,start,inputWords.length);
    // remainingString = String.join(" ",remainingWords);
    // }

    // /**
    // * Checks if inputString starts with an element of array set remaining words
    // of
    // * inputString to remainingWords
    // *
    // * @param String[]
    // * array [description]
    // * @return [description]
    // */
    // public boolean verbEquals(String[] array) {
    // return wordEquals(verb, array);
    // }
    //
    // public boolean wordEquals(String word, ArrayList<String> arrayList) {
    // for (int i = 0; i < arrayList.size(); i++) {
    // if (word.toLowerCase().equals(arrayList.get(i).toLowerCase())) {
    // return true;
    // }
    // }
    // return false;
    // // return arrayList.contains(word.toLowerCase());
    // }
    //
    // public boolean wordEquals(String word1, double word2) {
    // return wordEquals(word1, Double.toString(word2));
    // }
    //
    // public boolean wordEquals(String word, double[] array) {
    // return wordEqualsDouble(word, getDoubleArrayList(array));
    // }
    //
    // public boolean wordEquals(String word1, int word2) {
    // return wordEquals(word1, Integer.toString(word2));
    // }
    //
    // public boolean wordEquals(String word, int[] array) {
    // return wordEqualsInteger(word, getIntegerArrayList(array));
    // }
    //
    // public boolean wordEquals(String word1, String word2) {
    // return word1.equalsIgnoreCase(word2);
    // }
    //
    // public boolean wordEquals(String word, String[] array) {
    // return wordEquals(word, new ArrayList<String>(Arrays.asList(array)));
    // }
    //
    // public boolean wordEqualsDouble(String word, ArrayList<Double> arrayList) {
    // return wordEquals(word, getStringArrayListFromDouble(arrayList));
    // }
    //
    // public boolean wordEqualsInteger(String word, ArrayList<Integer> arrayList) {
    // return wordEquals(word, getStringArrayList(arrayList));
    // }

}
