package game.system;

import gui.*;
/**
 * Can output to OutputPanel
 */
public abstract class Outputable {

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
    /**
     * Appends to outputString with new line
     * @param String output
     */
    public void outputln(String output) {
        output(output + "\n");
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
    public void outputln(int spacing) {
        for (int i = 0; i < spacing; i++) {
            outputln();
        }
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
        outputPanel.appendTitle(toTitleCase(output));
    }
    public void outputlnTitle(String output) {
        outputTitle(output + "\n");
    }
    public void outputSpeech(String output) {
        outputPanel.appendSpeech(output);
    }
    public void outputlnSpeech(String output) {
        outputSpeech(output + "\n");
    }
    public void outputDirection(String output) {
        outputPanel.appendDirection(output);
    }
    public void outputlnDirection(String output) {
        outputDirection(output + "\n");
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
