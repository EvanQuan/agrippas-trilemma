package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import game.system.parsing.PlayerCommand;
import game.system.parsing.PlayerCommandParser;

public class PlayerCommandParser_syntacticalAnalysis_Test {

    public static ArrayList<String> tokens;
    public static PlayerCommand expected;
    public static PlayerCommand actual;

    public static void testSyntacticalAnalysis(String input, PlayerCommand expected) {
        actual = new PlayerCommand(input);
        tokens = PlayerCommandParser.lexicalAnalysis(input);
        assertEquals(expected, actual);
    }

    @Test
    public void empty_empty() {
        expected = new PlayerCommand("");
        testSyntacticalAnalysis("", expected);
    }

    @Before
    public void setUp() {
        expected = new PlayerCommand("");
    }

    @Test
    public void verb_verb() {
        expected = new PlayerCommand("verb");
        expected.setVerbPhrase("verb");
        testSyntacticalAnalysis("verb", expected);
    }
}
