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
public class VerbAgnosticPlayerCommandParser_lexicalAnalysis_Test {

    public static ArrayList<String> tokens;

    public static void testLexicalAnalysis(String input, String[] expected) {
        tokens = VerbAgnosticPlayerCommandParser.lexicalAnalysis(input);
        assertEquals(new ArrayList<>(Arrays.asList(expected)), tokens);
    }

    @Test
    public void empty_empty() {
        testLexicalAnalysis("", new String[] {});
    }

    @Test
    public void one_endComma_split() {
        testLexicalAnalysis("a,", new String[] { "a", "," });
    }

    @Test
    public void one_noPunctuation_noSplit() {
        testLexicalAnalysis("a", new String[] { "a" });
    }

    @Test
    public void one_startComma_noSplit() {
        testLexicalAnalysis(",a", new String[] { ",a" });
    }

    @Test
    public void one_startEndComma_split() {
        testLexicalAnalysis(",a,", new String[] { ",a", "," });
    }

    @Before
    public void setUp() {
        tokens = new ArrayList<>();
    }

    @Test
    public void two_endComma_split() {
        testLexicalAnalysis("a, b", new String[] { "a", ",", "b" });
    }

    @Test
    public void two_noPunctuation_noSplit() {
        testLexicalAnalysis("a b", new String[] { "a", "b" });
    }

    @Test
    public void two_startComma_noSplit() {
        testLexicalAnalysis("a ,b", new String[] { "a", ",b" });
    }

    @Test
    public void two_startEndComma_split() {
        testLexicalAnalysis(",a, ,b,", new String[] { ",a", ",", ",b", "," });
    }
}
