package test;

import game.system.output.ConsolePrintBuffer;

/**
 * Not a formal JUnit test. Just to see the colors
 *
 * @author Evan Quan
 *
 */
public class StandardPrinterTest {

    public static void main(String[] args) {
        ConsolePrintBuffer out = new ConsolePrintBuffer();
        out.println("Normal");
        out.appendCharacterln("Character");
        out.printItemln("Item");
        out.printDirectionln("Direction");
        out.printLocationln("Location");
        out.printPlayerln("Player");
        out.printSpeechln("Speech");
        out.printTitleln("Title");
    }
}
