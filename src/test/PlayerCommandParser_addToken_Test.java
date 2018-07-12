package test;

import static org.junit.Assert.assertEquals;

import game.system.parsing.PlayerCommandParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerCommandParser_addToken_Test {

    public static ArrayList<String> tokens;

    @Before
    public void setUp() {
        tokens = new ArrayList<>();
    }

    @Test
    public void noPunctuation_noSplit() {
        testAddToken("a", new String[] {"a"});
    }

    @Test
    public void endComma_split() {
        testAddToken("a,", new String[] {"a", ","});
    }

    @Test
    public void startComma_noSplit() {
        testAddToken(",a", new String[] {",a"});
    }

    @Test
    public void startEndComma_split() {
        testAddToken(",a,", new String[] {",a", ","});
    }

    public static void testAddToken(String token, String[] expected) {
        PlayerCommandParser.addToken(tokens, token);
        assertEquals(new ArrayList<>(Arrays.asList(expected)), tokens);
    }
}
