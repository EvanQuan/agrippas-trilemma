package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import game.system.parsing.VerbAgnosticPlayerCommandParser;

/**
 *
 * @author Evan Quan
 *
 */
public class VerbAgnosticPlayerInputParser_addToken_Test {

    public static ArrayList<String> tokens;

    public static void testAddToken(String token, String[] expected) {
        VerbAgnosticPlayerCommandParser.addToken(tokens, token);
        assertEquals(new ArrayList<>(Arrays.asList(expected)), tokens);
    }

    @Test
    public void endComma_split() {
        testAddToken("a,", new String[] { "a", "," });
    }

    @Test
    public void noPunctuation_noSplit() {
        testAddToken("a", new String[] { "a" });
    }

    @Before
    public void setUp() {
        tokens = new ArrayList<>();
    }

    @Test
    public void startComma_noSplit() {
        testAddToken(",a", new String[] { ",a" });
    }

    @Test
    public void startEndComma_split() {
        testAddToken(",a,", new String[] { ",a", "," });
    }
}
