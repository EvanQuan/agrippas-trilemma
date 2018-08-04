package test;

import game.system.output.ConsolePrintBuffer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        c.println(output);

        // Looks like line separator connects to the end of the previous element
        // add to its visual size, without increasing the token size
        c.println("\\");
        c.println(System.lineSeparator());
        c.println("1 2 3");
    }
}
