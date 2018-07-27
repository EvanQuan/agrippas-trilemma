package test;

import game.system.output.ConsolePrinter;

/**
 * Not a formal JUnit test. Just to see the colors
 *
 * @author Evan Quan
 *
 */
public class StandardPrinterTest {

    public static void main(String[] args) {
        ConsolePrinter out = new ConsolePrinter();
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
