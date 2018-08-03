package test;

import static org.junit.Assert.assertTrue;

import game.system.output.ConsolePrintBuffer;
import game.system.output.IPrintBuffer;
import game.system.output.SemanticColor;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Checks that JUnit 4 is working
 *
 * @author Evan Quan
 *
 */
public class TestTest {

    @Test
    public void testPass() {
        assertTrue(true);
    }

    public static void main(String[] args) {
        ConsolePrintBuffer c = new ConsolePrintBuffer();
        String output = "\\" + System.lineSeparator();
        c.appendln(output);

        // Looks like line separator connects to the end of the previous element
        // add to its visual size, without increasing the token size
        c.appendln("\\");
        c.appendln(System.lineSeparator());
        c.appendln("1 2 3");
    }
}
