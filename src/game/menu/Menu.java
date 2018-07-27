package game.menu;

import java.util.ArrayList;

/**
 * Receives input string Outputs corresponding output string NOTE Unchecked
 * ArrayList methods imply types are String, Double, or Integer only
 */
public abstract class Menu {

    public static final String EMPTY = "";
    protected ArrayList<ArrayList> validVerbs;
    private String originalInputString;
    private String inputString;
    private String[] originalInputWords;
    private String[] inputWords;
    // private String remainingString;
    // private String[] remainingWords;
    private String verb;
    private MenuManager menuManager; // Cannot have menuManeger, or infinite recursion occurs??

    // Input options
    protected String[] yes;
    protected String[] no;
    protected String[] returnToPreviousMenu;

    public Menu() {
        menuManager = MenuManager.getInstance();
        // validVerbs = new ArrayList<ArrayList<String>>();
        yes = new String[] { "yes", "y", "yeah", "yee", "yup" };
        no = new String[] { "no", "n", "nay", "nope" };
        returnToPreviousMenu = new String[] { "return", "r", "return to previous menu", "return menu",
                "return to menu" };
    }

    public void changeToAskSaveMenu() {
        menuManager.setMenu(AskToSaveMenu.getInstance());
    }

    public void changeToCreateGameMenu() {
        menuManager.setMenu(CreateGameMenu.getInstance());
    }

    public void changeToGameMenu() {
        menuManager.setMenu(GameMenu.getInstance());
    }

    public void changeToGameOverMenu() {
        menuManager.setMenu(GameOverMenu.getInstance());
    }

    public void changeToLoadMenu() {
        menuManager.setMenu(LoadMenu.getInstance());
    }

    // Change menu
    /**
     * Change from current menu to next menu
     */
    public void changeToMainMenu() {
        menuManager.setMenu(MainMenu.getInstance());
    }

    public void changeToPreviousMenu() {
        menuManager.setMenu(menuManager.getPreviousMenu());
    }

    public void changeToRestartMenu() {
        menuManager.setMenu(RestartMenu.getInstance());
    }

    public void changeToSaveMenu() {
        menuManager.setMenu(SaveMenu.getInstance());
    }

    public void changeToTestMenu() {
        try {
            menuManager.setMenu(TestMenu.getInstance());
        } catch (Exception e) {
            System.out.println("Uh Oh...");
            e.printStackTrace();
        }
    }

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
    // * Get input from user and stores the line in inputLine and individual words
    // in
    // * inputWords
    // */
    // public void input(String input) {
    // this.inputString = input;
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
    // // Verb can be manually set or is automatically defined upon new player input
    // /**
    // * Checks if input starts with an element of arrayList
    // *
    // * @param ArrayList
    // * arrayList
    // * @param boolean
    // * strip to determine if element is stripped from inputWords
    // * @return true if input starts with element of arrayList
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
    // * Checks if input starts with an element of arrayList If so, strips away
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
    // outputPrompt();
    // }
    //
    // /**
    // * Output player input with input marker
    // */
    // public void outputPlayerInput() {
    // printlns(INPUT_SPACING);
    // outputPanel.append(INPUT_MARKER);
    // outputPanel.appendInput(inputString + "\n");
    // }
    //
    // /**
    // * Print prompt for user input
    // */
    // public void outputPrompt() {
    // }
    //
    // /**
    // * Process input that applies to inheritance
    // */
    // public void preProcessInput() {
    // if (inputEquals(EMPTY)) {
    // println("I beg your pardon?");
    // // outputPrompt();
    // // } else if (!validInput()) {
    // // outputln("I don't know the word " + getVerb());
    // } else {
    // processInput();
    // }
    // }

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

    // /**
    // * With inputString and inputWords, determine output
    // */
    // public void processInput() {
    //
    // }
    //
    // /**
    // * Strips away 1 word from inputWords
    // *
    // * @return word stripped
    // */
    // public String stripInput() {
    // return stripInput(1);
    // }
    //
    // /**
    // * Strips away starting words by index from inputWords and inputString If more
    // * words are to be stripped than exist, then all remaining words are stripped
    // *
    // * @param int
    // * index of new beginning inputWords
    // * @return words stripped away from input
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
    // * Checks if input starts with an element of arrayList If so, sets starting
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
