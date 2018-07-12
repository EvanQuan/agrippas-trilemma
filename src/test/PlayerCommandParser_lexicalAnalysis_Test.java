package test;

import game.system.parsing.PlayerCommandParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PlayerCommandParser_lexicalAnalysis_Test {

    public static ArrayList<String> tokens;

    @Before
    public void setUp() {
        tokens = new ArrayList<>();
    }

    @Test
    public void noPunctuation_noSplit() {
        testLexicalAnalysis("a", new String[] {"a"});
    }

    @Test
    public void endComma_split() {
        testLexicalAnalysis("a,", new String[] {"a", ","});
    }

    @Test
    public void startComma_noSplit() {
        testLexicalAnalysis(",a", new String[] {",a"});
    }

    @Test
    public void startEndComma_split() {
        testLexicalAnalysis(",a,", new String[] {",a", ","});
    }

    public static void testLexicalAnalysis(String input, String[] expected) {
        tokens = PlayerCommandParser.lexicalAnalysis(input);
        assertEquals(new ArrayList<>(Arrays.asList(expected)), tokens);
    }
}
