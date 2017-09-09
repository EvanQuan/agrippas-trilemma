package game.menu;

import java.lang.StringBuilder;
import java.util.*;
import gui.OutputPanel;
import game.system.Outputable;

/**
 * Receives input string
 * Outputs corresponding output string
 * NOTE Unchecked ArrayList methods imply types are String, Double, or Integer only
 */
public abstract class Menu extends Outputable {

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

    public Menu() {
        menuManager = MenuManager.getInstance();
        // validVerbs = new ArrayList<ArrayList<String>>();
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
        this.originalInputString = inputString;
        this.inputWords = inputString.split(" ");
        this.originalInputWords = inputWords;
        this.verb = EMPTY;
        outputPlayerInput();
        preProcessInput();
    }

    /**
     * Process input that applies to inheritance
     */
    public void preProcessInput() {
        if (inputEquals(EMPTY)) {
            outputln("I beg your pardon?");
            // outputPrompt();
        // } else if (!validInput()) {
        //     outputln("I don't know the word " + getVerb());
        } else {
            processInput();
        }
    }
    /**
     * With inputString and inputWords, determine output
     */
    public void processInput() {

    }

    /**
     * Output player input with input marker
     */
    public void outputPlayerInput() {
        outputlns(INPUT_SPACING);
        outputPanel.append(INPUT_MARKER);
        outputPanel.appendInput(inputString + "\n");
    }

    /**
     * If the user has an incomplete command, prompt to complete command
     */
    public void outputIncompleteCommand() {
        output(toTitleCase(verb));
        outputln(" what?");
    }
    public void outputIncompleteCommandAndReprompt() {
        outputIncompleteCommand();
        outputln();
        outputPrompt();
    }
    /**
     * If the use has too many words at the end of a command
     */
    public void outputExcessCommand() {
        output("I only understand as far as you wanting to \"");
        outputPlayer(getVerb());
        outputln(".\"");
    }







    // TODO: Make stripping pick the longest element of array to strip, not the first one
    // Input processing
    // Words are processed one by one from the start and are stripped away
    // Verb can be manually set or is automatically defined upon new player input
    /**
     * Checks if input starts with an element of arrayList
     * @param  ArrayList arrayList
     * @param  boolean   strip         to determine if element is stripped from inputWords
     * @return           true if input starts with element of arrayList
     */
    private boolean inputStartsWithChoice(ArrayList arrayList, boolean strip, boolean setVerb) {
        // debug
        // System.out.println("inputString: " + inputString);
        // System.out.println("inputWords: " + Arrays.asList(inputWords));
        // System.out.println("verb: " + verb);
        // System.out.println("arrayList: " + arrayList);
        // System.out.println("strip: " + strip);
        // System.out.println("setVerb: " + setVerb);
        if (arrayList.size() == 0) {
            arrayList.add(EMPTY);
        }
        int longestWord = 0;
        String[] words;
        String word;
        String tempVerb = EMPTY;
        Object testElement = arrayList.get(0);
        if (testElement instanceof String) {
            for (int i = 0; i < arrayList.size(); i++) {
                words = ((String) arrayList.get(i)).split(" "); // entries in array may have multiple words
                if (words.length > longestWord) {
                    longestWord = words.length;
                    word = String.join(" ", Arrays.copyOfRange(inputWords,0,longestWord)); // make start comparison multiple words if need be
                    if (wordEquals(word,arrayList)) {
                        if (strip) {
                            tempVerb = stripInput(longestWord);
                        }
                        if (setVerb || this.verb.equals(EMPTY)) {
                            this.verb = tempVerb;
                        }
                        return true;
                    }
                }
            }
        } else if (testElement instanceof Number) {
            if (testElement instanceof Integer) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (getStringArrayList(inputWords).contains(Integer.toString((int) arrayList.get(i)))) {
                        return true;
                    }
                }
            } else if (testElement instanceof Double) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (getStringArrayList(inputWords).contains(Double.toString((double) arrayList.get(i)))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Different names to prevent name clash with inputStartsWithChoice()
     * @param  ArrayList<Integer> arrayList
     * @param  boolean            strip
     * @return
     */
    // private boolean inputStartsWithIntegerChoice(ArrayList<Integer> arrayList, boolean strip) {
    //     return inputStartsWithChoice(getStringArrayList(arrayList),strip);
    // }
    // private boolean inputStartsWithDoubleChoice(ArrayList<Double> arrayList, boolean strip) {
    //     return inputStartsWithChoice(getStringArrayListFromDouble(arrayList),strip);
    // }


    /**
     * Checks if input starts with an element of arrayList
     * If so, strips away starting words for inputWords and returns true
     * @param  ArrayList<String> arrayList
     * @return
     */
    public boolean inputStartsWithStrip(ArrayList arrayList) {
        return inputStartsWithChoice(arrayList, true, false);
    }
    public boolean inputStartsWithStripVerb(ArrayList arrayList) {
        return inputStartsWithChoice(arrayList, true, true);
    }
    public boolean inputStartsWithStrip(String[] array) {
        return inputStartsWithStrip(getStringArrayList(array));
    }
    public boolean inputStartsWithStripVerb(String[] array) {
        return inputStartsWithStripVerb(getStringArrayList(array));
    }
    public boolean inputStartsWithStrip(String word) {
        return inputStartsWithStrip(getStringArrayList(word));
    }
    public boolean inputStartsWithStripVerb(String word) {
        return inputStartsWithStripVerb(getStringArrayList(word));
    }
    // public boolean inputStartsWithIntegerStrip(ArrayList<Integer> arrayList) {
    //     return inputStartsWithIntegerChoice(arrayList, true);
    // }
    public boolean inputStartsWithStrip(int[] array) {
        return inputStartsWithStrip(getStringArrayList(array));
    }
    public boolean inputStartsWithStripVerb(int[] array) {
        return inputStartsWithStripVerb(getStringArrayList(array));
    }
    public boolean inputStartsWithStrip(int word) {
        return inputStartsWithStrip(getStringArrayList(word));
    }
    public boolean inputStartsWithStripVerb(int word) {
        return inputStartsWithStripVerb(getStringArrayList(word));
    }
    // public boolean inputStartsWithDoubleStrip(ArrayList<Double> arrayList) {
    //     return inputStartsWithDoubleChoice(arrayList, true);
    // }
    public boolean inputStartsWithStrip(double[] array) {
        return inputStartsWithStrip(getStringArrayList(array));
    }
    public boolean inputStartsWithStripVerb(double[] array) {
        return inputStartsWithStripVerb(getStringArrayList(array));
    }
    public boolean inputStartsWithStrip(double word) {
        return inputStartsWithStrip(getStringArrayList(word));
    }
    // No strip versions
    public boolean inputStartsWith(ArrayList arrayList) {
        return inputStartsWithChoice(arrayList, false, false);
    }
    public boolean inputStartsWithVerb(ArrayList arrayList) {
        return inputStartsWithChoice(arrayList, false, true);
    }
    public boolean inputStartsWith(String[] array) {
        return inputStartsWith(new ArrayList<String>(Arrays.asList(array)));
    }
    public boolean inputStartsWith(String word) {
        return inputStartsWith(new String[] {word});
    }
    // public boolean inputStartsWithInteger(ArrayList<Integer> arrayList) {
    //     return inputStartsWithIntegerChoice(arrayList, false);
    // }
    public boolean inputStartsWith(int[] array) {
        return inputStartsWith(getIntegerArrayList(array));
    }
    public boolean inputStartsWith(int word) {
        return inputStartsWith(new int[] {word});
    }
    // public boolean inputStartsWithDouble(ArrayList<Double> arrayList) {
    //     return inputStartsWithDoubleChoice(arrayList, false);
    // }
    public boolean inputStartsWith(double[] array) {
        return inputStartsWith(getDoubleArrayList(array));
    }
    public boolean inputStartsWith(double word) {
        return inputStartsWith(new double[] {word});
    }
    /**
     * Strips away starting words by index from inputWords and inputString
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
        inputString = String.join(" ", inputWords);
        return output;

    }
    /**
     * Strips away 1 word from inputWords
     * @return word stripped
     */
    public String stripInput() {
        return stripInput(1);
    }
    /**
     * Checks if there exists remaining words in inputWords to parse
     * @return true if inputWords.length > 0
     */
    public boolean inputRemains() {
        return inputWords.length > 0;
    }

    public boolean wordEquals(String word, String[] array) {
        return wordEquals(word,new ArrayList<String>(Arrays.asList(array)));
    }
    public boolean wordEquals(String word, int[] array) {
        return wordEqualsInteger(word,getIntegerArrayList(array));
    }
    public boolean wordEquals(String word, double[] array) {
        return wordEqualsDouble(word,getDoubleArrayList(array));
    }
    public boolean wordEquals(String word, ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (word.toLowerCase().equals(arrayList.get(i).toLowerCase())) {
                return true;
            }
        }
        return false;
        // return arrayList.contains(word.toLowerCase());
    }
    public boolean wordEqualsInteger(String word, ArrayList<Integer> arrayList) {
        return wordEquals(word,getStringArrayList(arrayList));
    }
    public boolean wordEqualsDouble(String word, ArrayList<Double> arrayList) {
        return wordEquals(word,getStringArrayListFromDouble(arrayList));
    }
    public boolean wordEquals(String word1, String word2) {
        return word1.equalsIgnoreCase(word2);
    }
    public boolean wordEquals(String word1, int word2) {
        return wordEquals(word1,Integer.toString(word2));
    }
    public boolean wordEquals(String word1, double word2) {
        return wordEquals(word1,Double.toString(word2));
    }

    public boolean inputEquals(String[] array) {
        return wordEquals(getInputString(),array);
    }
    public boolean inputEquals(int[] array) {
        return wordEquals(getInputString(),getStringArrayList(array));
    }
    public boolean inputEquals(double[] array) {
        return wordEquals(getInputString(),getStringArrayList(array));
    }
    public boolean inputEquals(ArrayList arrayList) {
        return wordEquals(getInputString(),getStringArrayList(arrayList));
    }
    public boolean inputEquals(String word) {
        return wordEquals(getInputString(),word);
    }
    public boolean inputEquals(int word) {
        return wordEquals(getInputString(),Integer.toString(word));
    }
    public boolean inputEquals(double word) {
        return wordEquals(getInputString(),Double.toString(word));
    }

    /**
     * TODO
     * Checks if unknown word is used
     * @return true if
     */
    public boolean validInput() {
        return true; // TODO
    }
    /**
     * Checks if inputString starts with an element of array
     * set remaining words of inputString to remainingWords
     * @param  String[] array         [description]
     * @return          [description]
     */
    public boolean verbEquals(String[] array) {
        return wordEquals(verb,array);
    }
    /**
     * Checks if input starts with an element of arrayList
     * If so, sets starting word(s) to verb and rest to remainingWords
     * @param  ArrayList<String> arrayList
     * @return
     */
    public boolean verbEquals(ArrayList<String> arrayList) {
        return wordEquals(verb,arrayList);
    }
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
     * Returns value of originalInputWords
     * @return
     */
    public String[] getOriginalInputWords() {
        return this.originalInputWords;
    }

    /**
     * Returns verb of inputString
     * @return
     */
    public String getVerb() {
        return this.verb;
    }

    // public String[] getRemainingWords() {
    //     return this.remainingWords;
    // }
    //
    public String getRemainingString() {
        return String.join(" ", inputWords);
    }



    // Change menu
    /**
     * Change from current menu to next menu
     */
    public void changeToMainMenu() {
        menuManager.setMenu(MainMenu.getInstance());
    }
    public void changeToGameMenu() {
        menuManager.setMenu(GameMenu.getInstance());
    }
    public void changeToGameOverMenu() {
        menuManager.setMenu(GameOverMenu.getInstance());
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
    public void changeToCreateGameMenu() {
        menuManager.setMenu(CreateGameMenu.getInstance());
    }
    public void changeToTestMenu() {
        try{
            menuManager.setMenu(TestMenu.getInstance());
        } catch (Exception e) {
            System.out.println("Uh Oh...");
            e.printStackTrace();
        }
    }
    public void changeToPreviousMenu() {
        menuManager.setMenu(menuManager.getPreviousMenu());
    }



    // Utility functions
    /**
     * Convert ArrayList<String> to ArrayList<Integer>
     * @param  ArrayList<String> stringList
     * @return
     */
    public ArrayList<Integer> getIntegerArrayList(ArrayList<String> stringList) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (String string : stringList) {
            try {
                intList.add(Integer.parseInt(string));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return intList;
    }
    public ArrayList<Integer> getIntegerArrayList(int[] intArray) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 0; i < intArray.length; i++) {
            intList.add(intArray[i]);
        }
        return intList;
    }
    public ArrayList<Double> getDoubleArrayList(double[] doubleArray) {
        ArrayList<Double> doubleList = new ArrayList<Double>();
        for (int i = 0; i < doubleArray.length; i++) {
            doubleList.add(doubleArray[i]);
        }
        return doubleList;
    }
    /**
     * Convert ArrayList<Integer> or ArrayList<Double> to ArrayList<String>
     * @param  ArrayList<Integer> intList
     * @return
     */
    public ArrayList<String> getStringArrayList(ArrayList inList) {
        ArrayList<String> stringList = new ArrayList<String>();
        // System.out.println("getStringArrayList(" + inList + ")");
        // if (inList == null) {
        //     System.out.println("inList is null");
        // } else if (inList.size() == 0) {
        //     System.out.println("Empty inlist");
        // }
        if (inList.isEmpty()) {
            stringList.add(EMPTY);
        } else if (inList.get(0) instanceof Integer) {
            for (int i = 0; i < inList.size(); i++) {
                try {
                    stringList.add(Integer.toString((int) inList.get(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (inList.get(0) instanceof Double) {
            for (int i = 0; i < inList.size(); i++) {
                try{
                    stringList.add(Double.toString((double) inList.get(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (inList.get(0) instanceof String) {
            stringList =  inList;
        }
        return stringList;
    }
    public ArrayList<String> getStringArrayListFromDouble(ArrayList<Double> doubleList) {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Double doub : doubleList) {
            try{
                stringList.add(Double.toString(doub));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }
    public ArrayList<String> getStringArrayList(int[] intArray) {
        return getStringArrayList(getIntegerArrayList(intArray));
    }
    public ArrayList<String> getStringArrayList(int integer) {
        return getStringArrayList(getStringArrayList(integer));
    }
    public ArrayList<String> getStringArrayList(double[] doubleArray) {
        return getStringArrayList(getDoubleArrayList(doubleArray));
    }
    public ArrayList<String> getStringArrayList(double doub) {
        return getStringArrayList(new double[] {doub});
    }
    public ArrayList<String> getStringArrayList(String[] strArray) {
        return new ArrayList<String>(Arrays.asList(strArray));
    }
    public ArrayList<String> getStringArrayList(String word) {
        return getStringArrayList(new String[] {word});
    }
}
