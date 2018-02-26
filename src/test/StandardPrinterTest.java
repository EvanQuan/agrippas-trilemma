package test;

import game.system.StandardPrinter;

/**
 * Not a formal JUnit test. Just to see the colors
 *
 * @author Evan Quan
 *
 */
public class StandardPrinterTest {

    public static void main(String[] args) {
        StandardPrinter out = new StandardPrinter();
        out.println("Normal");
        out.printCharacterln("Character");
        out.printItemln("Item");
        out.printDirectionln("Direction");
        out.printLocationln("Location");
        out.printPlayerln("Player");
        out.printSpeechln("Speech");
        out.printTitleln("Title");
    }
}
