package test;

import game.system.input.PlayerCommand;
import game.system.input.PlayerInputParser;
import game.system.output.ConsolePrintBuffer;
import game.system.output.IPrintBuffer;
import game.system.output.SemanticColor;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

/**
 * Checks that JUnit 4 is working
 *
 * @author Evan Quan
 */
public class TestTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        PlayerCommand command;
        ConsolePrintBuffer out = new ConsolePrintBuffer();

        out.print(System.getProperty("java.version"));
        do {
            out.setCurrentColor(SemanticColor.PLAYER);
            out.print(IPrintBuffer.INPUT_MARKER);
            input = in.nextLine();
            command = PlayerInputParser.parse(input);
            out.setCurrentColor(SemanticColor.DEFAULT);

            out.println(command, SemanticColor.DEFAULT);
        } while (!input.isEmpty());
    }

    @Test
    public void testPass() {
        assertTrue(true);
    }
}
