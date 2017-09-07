package game.system;

import gui.*;
/**
 * Can output to OutputPanel
 */
public abstract class Outputable {

    public static final int INPUT_SPACING = 4;
    public static final String INPUT_MARKER = "> ";
    public static final int VERB = 0;


    protected OutputPanel outputPanel;

    public Outputable() {
        outputPanel = OutputPanel.getInstance();
    }

    /**
     * Appends to outputString
     * @param String output
     */
    public void output(String output) {
        outputPanel.append(output);
    }
    public void output(int output) {
        output(Integer.toString(output));
    }
    public void output(double output) {
        output(Double.toString(output));
    }
    /**
     * Appends to outputString with new line
     * @param String output
     */
    public void outputln(String output) {
        output(output + "\n");
    }
    public void outputln(int output) {
        outputln(Integer.toString(output));
    }
    public void outputln(double output) {
        outputln(Double.toString(output));
    }
    /**
     * Output a new line
     */
    public void outputln() {
        outputln("");
    }
    /**
     * Output spacing number of new lines
     * @param int spacing number of new lines
     */
    public void outputlns(int spacing) {
        for (int i = 0; i < spacing; i++) {
            outputln();
        }
    }

    public void outputItem(String output) {
        outputPanel.appendItem(output);
    }
    public void outputItem(int output) {
        outputItem(Integer.toString(output));
    }
    public void outputItem(double output) {
        outputItem(Double.toString(output));
    }
    public void outputlnItem(String output) {
        outputItem(output + "\n");
    }
    public void outputlnItem(int output) {
        outputlnItem(Integer.toString(output));
    }
    public void outputlnItem(double output) {
        outputlnItem(Double.toString(output));
    }
    public void outputPerson(String output) {
        outputPanel.appendPerson(output);
    }
    public void outputPerson(int output) {
        outputPerson(Integer.toString(output));
    }
    public void outputPerson(double output) {
        outputPerson(Double.toString(output));
    }
    public void outputlnPerson(String output) {
        outputPerson(output + "\n");
    }
    public void outputlnPerson(int output) {
        outputlnPerson(Integer.toString(output));
    }
    public void outputlnPerson(double output) {
        outputlnPerson(Double.toString(output));
    }
    public void outputRoom(String output) {
        outputPanel.appendRoom(output);
    }
    public void outputRoom(int output) {
        outputRoom(Integer.toString(output));
    }
    public void outputRoom(double output) {
        outputRoom(Double.toString(output));
    }
    public void outputlnRoom(String output) {
        outputRoom(output + "\n");
    }
    public void outputlnRoom(int output) {
        outputlnRoom(Integer.toString(output));
    }
    public void outputlnRoom(double output) {
        outputlnRoom(Double.toString(output));
    }
    public void outputTitle(String output) {
        outputPanel.appendTitle(toTitleCase(output));
    }
    public void outputTitle(int output) {
        outputTitle(Integer.toString(output));
    }
    public void outputTitle(double output) {
        outputTitle(Double.toString(output));
    }
    public void outputlnTitle(String output) {
        outputTitle(output + "\n");
    }
    public void outputlnTitle(int output) {
        outputlnTitle(Integer.toString(output));
    }
    public void outputlnTitle(double output) {
        outputlnTitle(Double.toString(output));
    }
    public void outputSpeech(String output) {
        outputPanel.appendSpeech(output);
    }
    public void outputSpeech(int output) {
        outputSpeech(Integer.toString(output));
    }
    public void outputSpeech(double output) {
        outputSpeech(Double.toString(output));
    }
    public void outputlnSpeech(String output) {
        outputSpeech(output + "\n");
    }
    public void outputlnSpeech(int output) {
        outputlnSpeech(Integer.toString(output));
    }
    public void outputlnSpeech(double output) {
        outputlnSpeech(Double.toString(output));
    }
    public void outputDirection(String output) {
        outputPanel.appendDirection(output);
    }
    public void outputDirection(int output) {
        outputDirection(Integer.toString(output));
    }
    public void outputDirection(double output) {
        outputDirection(Double.toString(output));
    }
    public void outputlnDirection(String output) {
        outputlnDirection(output + "\n");
    }
    public void outputlnDirection(int output) {
        outputlnDirection(Integer.toString(output));
    }
    public void outputlnDirection(double output) {
        outputlnDirection(Double.toString(output));
    }


    /**
     * Output player input with input marker
     */
    // public void outputPlayerInput() {
    //     outputlns(INPUT_SPACING);
    //     outputPanel.append(INPUT_MARKER);
    //     outputPanel.appendInput(inputString + "\n");
    // }
    public void outputPlayerInput(String output) {
        outputlns(INPUT_SPACING);
        outputPlayerInputNoSpace(output);
    }
    public void outputPlayerInput(int output) {
        outputPlayerInput(Integer.toString(output));
    }
    public void outputPlayerInput(double output) {
        outputPlayerInput(Double.toString(output));
    }
    public void outputPlayerInputNoSpace(String output) {
        outputPanel.append(Integer.toString(INPUT_SPACING));
        outputPanel.appendInput(output + "\n");
    }
    public void outputPlayerInputNoSpace(int output) {
        outputPlayerInputNoSpace(Integer.toString(output));
    }
    public void outputPlayerInputNoSpace(double output) {
        outputPlayerInputNoSpace(Double.toString(output));
    }
    public void outputlnPlayer(String output) {
        outputPlayer(output + "\n");
    }
    public void outputlnPlayer(int output) {
        outputlnPlayer(Integer.toString(output));
    }
    public void outputlnPlayer(double output) {
        outputlnPlayer(Double.toString(output));
    }
    public void outputPlayer(String output) {
        outputPanel.appendInput(output);
    }
    public void outputPlayer(int output) {
        outputPlayer(Integer.toString(output));
    }
    public void outputPlayer(double output) {
        outputPlayer(Double.toString(output));
    }


    /**
     * Converts beginning of each word in string to upper case
     * @param  String string        to be converted
     * @return        title case string
     */
    public String toTitleCase(String string) {
        String[] words = string.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
        }
        return String.join(" ",words);
    }

    public boolean startsWithVowel(String string) {
        switch(Character.toLowerCase(string.charAt(0))) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }
}
