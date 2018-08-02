package test;

import static org.junit.Assert.assertTrue;

import game.system.output.IPrintBuffer;
import game.system.output.SemanticColor;
import org.junit.Test;

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
        Scanner in = new Scanner(System.in);
        String message = "hi";
        while (!message.equals("")) {
            System.out.print(SemanticColor.apply(IPrintBuffer.INPUT_MARKER, SemanticColor.PLAYER));
            message = in.nextLine();
            System.out.println(SemanticColor.apply("Your message was: ", SemanticColor.CHARACTER) + SemanticColor.apply("\"" + message + "\"", SemanticColor.SPEECH));
        }
    }

}
