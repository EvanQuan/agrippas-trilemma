package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.system.parsing.PlayerCommand;
import game.system.parsing.PlayerCommandParser;

/**
 *
 * @author Evan Quan
 *
 */
public class PlayerCommandParser_parse_Test {

    public static PlayerCommand command;

    @Test
    public void word0_empty() {
        command = PlayerCommandParser.parse("");

        assertTrue(command.isEmpty());
    }

    /**
     * Check that a single determiner skips the verb parsing.
     */
    @Test
    public void word1_directDeterminer() {
        command = PlayerCommandParser.parse("the");

        assertFalse(command.hasVerbPhrase());

        assertTrue(command.hasDirectObjectPhrase());
        assertEquals("the", command.getDirectObjectPhrase().getDeterminer());
    }

    /**
     * Check that a single preposition skips the verb and direct object phrase
     * parsing.
     */
    @Test
    public void word1_preposition() {
        command = PlayerCommandParser.parse("to");

        assertFalse(command.hasVerbPhrase());

        assertFalse(command.hasDirectObjectPhrase());

        assertTrue(command.hasPreposition());
        assertEquals("to", command.getPreposition());

        assertFalse(command.hasIndirectObjectPhrase());
    }

    /**
     * Check that a single non-preposition word counts as a verb. Other command
     * components are null.
     */
    @Test
    public void word1_verb() {
        command = PlayerCommandParser.parse("b");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertFalse(command.hasDirectObjectPhrase());

        assertFalse(command.hasPreposition());

        assertFalse(command.hasIndirectObjectPhrase());
    }

    /**
     * Test that a noun can be parsed
     */
    @Test
    public void word2_verb_directNoun() {
        command = PlayerCommandParser.parse("b c");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertTrue(command.hasDirectObjectPhrase());
        assertFalse(command.getDirectObjectPhrase().hasDeterminer());
        assertFalse(command.getDirectObjectPhrase().hasAdjectives());
        assertEquals("c", command.getDirectObjectPhrase().getNoun());

        assertFalse(command.hasPreposition());

        assertFalse(command.hasIndirectObjectPhrase());
    }

    @Test
    public void word2_verb_preposition() {
        command = PlayerCommandParser.parse("b to");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertFalse(command.hasDirectObjectPhrase());

        assertTrue(command.hasPreposition());
        assertEquals("to", command.getPreposition());

        assertFalse(command.hasIndirectObjectPhrase());
    }

    /**
     * Test that 1 adjective can be parsed
     */
    @Test
    public void word3_verb_directAdjective1Noun() {
        command = PlayerCommandParser.parse("b c d");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertTrue(command.hasDirectObjectPhrase());
        assertFalse(command.getDirectObjectPhrase().hasDeterminer());
        assertEquals(1, command.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", command.getDirectObjectPhrase().getAdjectives().get(0));
        assertEquals("d", command.getDirectObjectPhrase().getNoun());

        assertFalse(command.hasPreposition());

        assertFalse(command.hasIndirectObjectPhrase());
    }

    @Test
    public void word3_verb_preposition_indirectNoun() {
        command = PlayerCommandParser.parse("b to c");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertFalse(command.hasDirectObjectPhrase());

        assertTrue(command.hasPreposition());
        assertEquals("to", command.getPreposition());

        assertTrue(command.hasIndirectObjectPhrase());
        assertFalse(command.getIndirectObjectPhrase().hasDeterminer());
        assertFalse(command.getIndirectObjectPhrase().hasAdjectives());
        assertTrue(command.getIndirectObjectPhrase().hasNoun());
        assertEquals("c", command.getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that multiple adjectives can be parsed
     */
    @Test
    public void word4_verb_directAdjective2Noun() {
        command = PlayerCommandParser.parse("b c d e");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertTrue(command.hasDirectObjectPhrase());
        assertFalse(command.getDirectObjectPhrase().hasDeterminer());
        assertEquals(2, command.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", command.getDirectObjectPhrase().getAdjectives().get(0));
        assertEquals("d", command.getDirectObjectPhrase().getAdjectives().get(1));
        assertEquals("e", command.getDirectObjectPhrase().getNoun());

        assertFalse(command.hasPreposition());

        assertFalse(command.hasIndirectObjectPhrase());
    }

    @Test
    public void word4_verb_directDeterminerAdjectiveNoun() {
        command = PlayerCommandParser.parse("b a c d");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertTrue(command.hasDirectObjectPhrase());
        assertTrue(command.getDirectObjectPhrase().hasDeterminer());
        assertEquals("a", command.getDirectObjectPhrase().getDeterminer());
        assertTrue(command.getDirectObjectPhrase().hasAdjectives());
        assertEquals(1, command.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", command.getDirectObjectPhrase().getAdjectives().get(0));
        assertTrue(command.getDirectObjectPhrase().hasNoun());
        assertEquals("d", command.getDirectObjectPhrase().getNoun());
    }

    @Test
    public void word4_verb_directNoun_preposition_indirectNoun() {
        command = PlayerCommandParser.parse("b c to d");

        assertTrue(command.hasVerbPhrase());
        assertEquals("b", command.getVerbPhrase());

        assertTrue(command.hasDirectObjectPhrase());
        assertFalse(command.getDirectObjectPhrase().hasDeterminer());
        assertFalse(command.getDirectObjectPhrase().hasAdjectives());
        assertTrue(command.getDirectObjectPhrase().hasNoun());
        assertEquals("c", command.getDirectObjectPhrase().getNoun());

        assertTrue(command.hasPreposition());
        assertEquals("to", command.getPreposition());

        assertTrue(command.hasIndirectObjectPhrase());
        assertFalse(command.getIndirectObjectPhrase().hasDeterminer());
        assertEquals("d", command.getIndirectObjectPhrase().getNoun());
        assertFalse(command.getIndirectObjectPhrase().hasAdjectives());
    }

}
