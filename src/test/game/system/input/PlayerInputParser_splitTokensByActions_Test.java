package test.game.system.input;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import game.system.input.PlayerCommand;
import game.system.input.PlayerInputParser;
import util.ArrayUtils;

/**
 * Tests {@link PlayerCommand}.splitTokensByActions()
 *
 * @author Evan Quan
 *
 */
public class PlayerInputParser_splitTokensByActions_Test {

    public static ArrayList<String> tokens;
    public static ArrayList<ArrayList<String>> expected;

    /**
     * Add an playerAction (in token form) to expected results.
     *
     * @param tokens
     */
    public static void addToExpected(String[] tokens) {
        expected.add(ArrayUtils.getArrayList(tokens));
    }

    public static void testSplitTokensByActions(String[] tokens) {
        ArrayList<ArrayList<String>> results = PlayerInputParser
                .splitTokensByActions(ArrayUtils.getArrayList(tokens));
        assertEquals(expected, results);
    }

    @Test
    public void comma() {
        addToExpected(new String[] {});
        addToExpected(new String[] {});
        testSplitTokensByActions(new String[] { "," });
    }

    @Test
    public void comma_a() {
        addToExpected(new String[] {});
        addToExpected(new String[] {"a"});
        testSplitTokensByActions(new String[] { ",", "a" });
    }

    @Test
    public void a_comma() {
        addToExpected(new String[] {"a"});
        addToExpected(new String[] {});
        testSplitTokensByActions(new String[] { "a", "," });
    }

    @Test
    public void comma2() {
        addToExpected(new String[] {});
        addToExpected(new String[] {});
        addToExpected(new String[] {});
        testSplitTokensByActions(new String[] { ",", "," });
    }

    @Test
    public void a_1() {
        addToExpected(new String[] { "a" });
        testSplitTokensByActions(new String[] { "a" });
    }

    @Test
    public void empty_comma_empty_2() {
        addToExpected(new String[] { "a" });
        addToExpected(new String[] { "b" });
        testSplitTokensByActions(new String[] { "a", ",", "b" });
    }

    @Test
    public void empty_then_empty_2() {
        addToExpected(new String[] { "a" });
        addToExpected(new String[] { "b" });
        testSplitTokensByActions(new String[] { "a", "then", "b" });
    }

    @Before
    public void setUp() {
        expected = new ArrayList<>();
    }

    @Test
    public void word_comma_word2_2() {
        addToExpected(new String[] { "a" });
        addToExpected(new String[] { "b", "c" });
        testSplitTokensByActions(new String[] { "a", ",", "b", "c" });
    }

    @Test
    public void word2_comma_word_2() {
        addToExpected(new String[] { "a", "b" });
        addToExpected(new String[] { "c" });
        testSplitTokensByActions(new String[] { "a", "b", ",", "c" });
    }
}
