package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.system.parsing.PlayerAction;
import game.system.parsing.PlayerCommand;
import game.system.parsing.VerbAgnosticPlayerCommandParser;

/**
 *
 * @author Evan Quan
 *
 */
public class VerbAgnosticPlayerCommandParser_parse_Test {

    public static PlayerCommand command;
    public static PlayerAction playerAction;
    public static ArrayList<PlayerAction> playerActions;

    public void testParse(String string) {
        command = VerbAgnosticPlayerCommandParser.parse(string);
        playerActions = command.getPlayerActions();
        if (playerActions.isEmpty()) {
            playerAction = null;
        } else {
            playerAction = playerActions.get(0);
        }
    }

    @Test
    public void word0_empty() {
        testParse("");

        assertFalse(command.hasActions());
    }

    /**
     * Check that a single determiner skips the verb parsing.
     */
    @Test
    public void word1_directDeterminer() {
        testParse("the");

        assertFalse(playerAction.hasVerbPhrase());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertEquals("the", playerAction.getDirectObjectPhrase().getDeterminer());
    }

    /**
     * Check that a single preposition skips the verb and direct object phrase
     * parsing.
     */
    @Test
    public void word1_preposition() {
        testParse("to");

        assertFalse(playerAction.hasVerbPhrase());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertTrue(playerAction.hasPreposition());
        assertEquals("to", playerAction.getPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Check that a single non-preposition word counts as a verb. Other playerAction
     * components are null.
     */
    @Test
    public void word1_verb() {
        testParse("b");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Test that a noun can be parsed
     */
    @Test
    public void word2_verb_directNoun() {
        testParse("b c");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertFalse(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertFalse(playerAction.getDirectObjectPhrase().hasAdjectives());
        assertEquals("c", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word2_verb_preposition() {
        testParse("b to");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertTrue(playerAction.hasPreposition());
        assertEquals("to", playerAction.getPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Test that 1 adjective can be parsed
     */
    @Test
    public void word3_verb_directAdjective1Noun() {
        testParse("b c d");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertFalse(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertEquals(1, playerAction.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", playerAction.getDirectObjectPhrase().getAdjectives().get(0));
        assertEquals("d", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word3_verb_preposition_indirectNoun() {
        testParse("b to c");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertTrue(playerAction.hasPreposition());
        assertEquals("to", playerAction.getPreposition());

        assertTrue(playerAction.hasIndirectObjectPhrase());
        assertFalse(playerAction.getIndirectObjectPhrase().hasDeterminer());
        assertFalse(playerAction.getIndirectObjectPhrase().hasAdjectives());
        assertTrue(playerAction.getIndirectObjectPhrase().hasNoun());
        assertEquals("c", playerAction.getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that multiple adjectives can be parsed
     */
    @Test
    public void word4_verb_directAdjective2Noun() {
        testParse("b c d e ");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertFalse(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertEquals(2, playerAction.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", playerAction.getDirectObjectPhrase().getAdjectives().get(0));
        assertEquals("d", playerAction.getDirectObjectPhrase().getAdjectives().get(1));
        assertEquals("e", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word4_verb_directDeterminerAdjectiveNoun() {
        testParse("b a c d");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertTrue(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertEquals("a", playerAction.getDirectObjectPhrase().getDeterminer());
        assertTrue(playerAction.getDirectObjectPhrase().hasAdjectives());
        assertEquals(1, playerAction.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", playerAction.getDirectObjectPhrase().getAdjectives().get(0));
        assertTrue(playerAction.getDirectObjectPhrase().hasNoun());
        assertEquals("d", playerAction.getDirectObjectPhrase().getNoun());
    }

    @Test
    public void word4_verb_directNoun_preposition_indirectNoun() {
        testParse("b c to d");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("b", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertFalse(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertFalse(playerAction.getDirectObjectPhrase().hasAdjectives());
        assertTrue(playerAction.getDirectObjectPhrase().hasNoun());
        assertEquals("c", playerAction.getDirectObjectPhrase().getNoun());

        assertTrue(playerAction.hasPreposition());
        assertEquals("to", playerAction.getPreposition());

        assertTrue(playerAction.hasIndirectObjectPhrase());
        assertFalse(playerAction.getIndirectObjectPhrase().hasDeterminer());
        assertEquals("d", playerAction.getIndirectObjectPhrase().getNoun());
        assertFalse(playerAction.getIndirectObjectPhrase().hasAdjectives());
    }

}
