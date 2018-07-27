package game.system.output;

import game.system.gui.OutputPanel;
import util.TextUtils;

/**
 * Prints text to OutputPanel GUI
 */
public class GUIPrinter implements IPrinter {

    protected OutputPanel outputPanel;

    public GUIPrinter() {
        outputPanel = OutputPanel.getInstance();
    }

    @Override
    public void print(double output) {
        print(Double.toString(output));
    }

    @Override
    public void print(int output) {
        print(Integer.toString(output));
    }

    @Override
    public void print(Object output) {
        print(output.toString());
    }

    @Override
    public void print(String output) {
        outputPanel.append(output);
    }

    @Override
    public void printCharacter(double output) {
        printCharacter(Double.toString(output));
    }

    @Override
    public void printCharacter(int output) {
        printCharacter(Integer.toString(output));
    }

    @Override
    public void printCharacter(Object output) {
        printCharacter(output.toString());
    }

    @Override
    public void printCharacter(String output) {
        outputPanel.appendCharacter(output);
    }

    @Override
    public void printCharacterln(double output) {
        printCharacterln(Double.toString(output));
    }

    @Override
    public void printCharacterln(int output) {
        printCharacterln(Integer.toString(output));
    }

    @Override
    public void printCharacterln(Object output) {
        printCharacterln(output.toString());
    }

    @Override
    public void printCharacterln(String output) {
        printCharacter(output + "\n");
    }

    @Override
    public void printDirection(double output) {
        printDirection(Double.toString(output));
    }

    @Override
    public void printDirection(int output) {
        printDirection(Integer.toString(output));
    }

    @Override
    public void printDirection(Object output) {
        printDirection(output.toString());
    }

    @Override
    public void printDirection(String output) {
        outputPanel.appendDirection(output);
    }

    @Override
    public void printDirectionln(double output) {
        printDirectionln(Double.toString(output));
    }

    @Override
    public void printDirectionln(int output) {
        printDirectionln(Integer.toString(output));
    }

    @Override
    public void printDirectionln(Object output) {
        printDirectionln(output.toString());
    }

    @Override
    public void printDirectionln(String output) {
        printDirectionln(output + "\n");
    }

    @Override
    public void printItem(double output) {
        printItem(Double.toString(output));
    }

    @Override
    public void printItem(int output) {
        printItem(Integer.toString(output));
    }

    @Override
    public void printItem(Object output) {
        printItem(output.toString());
    }

    @Override
    public void printItem(String output) {
        outputPanel.appendItem(output);
    }

    @Override
    public void printItemln(double output) {
        printItemln(Double.toString(output));
    }

    @Override
    public void printItemln(int output) {
        printItemln(Integer.toString(output));
    }

    @Override
    public void printItemln(Object output) {
        printItemln(output.toString());
    }

    @Override
    public void printItemln(String output) {
        printItem(output + "\n");
    }

    @Override
    public void println() {
        println("");
    }

    @Override
    public void println(double output) {
        println(Double.toString(output));
    }

    @Override
    public void println(int output) {
        println(Integer.toString(output));
    }

    @Override
    public void println(Object output) {
        println(output.toString());
    }

    @Override
    public void println(String output) {
        print(output + "\n");
    }

    @Override
    public void printlns(int spacing) {
        for (int i = 0; i < spacing; i++) {
            println();
        }
    }

    @Override
    public void printLocation(double output) {
        printLocation(Double.toString(output));
    }

    @Override
    public void printLocation(int output) {
        printLocation(Integer.toString(output));
    }

    @Override
    public void printLocation(Object output) {
        printLocation(output.toString());
    }

    @Override
    public void printLocation(String output) {
        outputPanel.appendLocation(output);
    }

    @Override
    public void printLocationln(double output) {
        printLocationln(Double.toString(output));
    }

    @Override
    public void printLocationln(int output) {
        printLocationln(Integer.toString(output));
    }

    @Override
    public void printLocationln(Object output) {
        printLocationln(output.toString());
    }

    @Override
    public void printLocationln(String output) {
        printLocation(output + "\n");
    }

    @Override
    public void printPlayer(double output) {
        printPlayer(Double.toString(output));
    }

    @Override
    public void printPlayer(int output) {
        printPlayer(Integer.toString(output));
    }

    @Override
    public void printPlayer(Object output) {
        printPlayer(output.toString());
    }

    @Override
    public void printPlayer(String output) {
        outputPanel.appendInput(output);
    }

    @Override
    public void printPlayerInput(double output) {
        printPlayerInput(Double.toString(output));
    }

    @Override
    public void printPlayerInput(int output) {
        printPlayerInput(Integer.toString(output));
    }

    @Override
    public void printPlayerInput(Object output) {
        printPlayerInput(output.toString());

    }

    @Override
    public void printPlayerInput(String output) {
        printlns(INPUT_SPACING);
        printPlayerInputNoSpace(output);
    }

    @Override
    public void printPlayerInputNoSpace(double output) {
        printPlayerInputNoSpace(Double.toString(output));
    }

    @Override
    public void printPlayerInputNoSpace(int output) {
        printPlayerInputNoSpace(Integer.toString(output));
    }

    @Override
    public void printPlayerInputNoSpace(Object output) {
        printPlayerInputNoSpace(output.toString());
    }

    @Override
    public void printPlayerInputNoSpace(String output) {
        print(INPUT_MARKER);
        printPlayer(output + "\n");
    }

    @Override
    public void printPlayerln(double output) {
        printPlayerln(Double.toString(output));
    }

    @Override
    public void printPlayerln(int output) {
        printPlayerln(Integer.toString(output));
    }

    @Override
    public void printPlayerln(Object output) {
        printPlayerln(output.toString());
    }

    @Override
    public void printPlayerln(String output) {
        printPlayer(output + "\n");
    }

    @Override
    public void printSpeech(double output) {
        printSpeech(Double.toString(output));
    }

    @Override
    public void printSpeech(int output) {
        printSpeech(Integer.toString(output));
    }

    @Override
    public void printSpeech(Object output) {
        printSpeech(output.toString());
    }

    @Override
    public void printSpeech(String output) {
        outputPanel.appendSpeech(output);
    }

    @Override
    public void printSpeechln(double output) {
        printSpeechln(Double.toString(output));
    }

    @Override
    public void printSpeechln(int output) {
        printSpeechln(Integer.toString(output));
    }

    @Override
    public void printSpeechln(Object output) {
        printSpeechln(output.toString());
    }

    @Override
    public void printSpeechln(String output) {
        printSpeech(output + "\n");
    }

    @Override
    public void printTitle(double output) {
        printTitle(Double.toString(output));
    }

    @Override
    public void printTitle(int output) {
        printTitle(Integer.toString(output));
    }

    @Override
    public void printTitle(Object output) {
        printTitle(output.toString());
    }

    @Override
    public void printTitle(String output) {
        outputPanel.appendTitle(TextUtils.toTitleCase(output));
    }

    @Override
    public void printTitleln(double output) {
        printTitleln(Double.toString(output));
    }

    @Override
    public void printTitleln(int output) {
        printTitleln(Integer.toString(output));
    }

    @Override
    public void printTitleln(Object output) {
        printTitleln(output.toString());
    }

    @Override
    public void printTitleln(String output) {
        printTitle(output + "\n");
    }

}