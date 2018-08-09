package test.game.system.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import game.system.input.PlayerAction;
import org.junit.Test;

import game.system.input.PlayerCommand;
import game.system.input.PlayerInputParser;

/**
 *
 * @author Evan Quan
 *
 */
public class PlayerInputParser_parse_Test {

    public static PlayerCommand command;
    public static PlayerAction playerAction;
    public static ArrayList<PlayerAction> playerActions;

    public void testParse(String string) {
        command = PlayerInputParser.parse(string);
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
     * Check that a single determiner skips the verb receiveInput.
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
     * receiveInput.
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
     * Check that a single non-verb word counts as a noun. Other playerAction
     * components are null.
     */
    @Test
    public void word1_noun() {
        testParse("b");

        assertFalse(playerAction.hasVerbPhrase());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertEquals("b", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word1_indirectTransitiveVerb_verb() {
        testParse("give");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("give", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word1_nonIndirectTransitiveVerb_verb() {
        testParse("eat");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("eat", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word1_optionallyIndirectTransitiveVerb_verb() {
        testParse("use");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("use", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word2_directAdjectiveNoun() {
        testParse("b c");

        assertFalse(playerAction.hasVerbPhrase());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertEquals(1, playerAction.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("b", playerAction.getDirectObjectPhrase().getAdjectives().get(0));
        assertEquals("c", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Test that a noun can be parsed
     */
    @Test
    public void word2_nonIndirectTransitiveVerb_directNoun() {
        testParse("eat b");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("eat", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertFalse(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertFalse(playerAction.getDirectObjectPhrase().hasAdjectives());
        assertEquals("b", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word2_optionallyIndirectTransitiveVerb_preposition() {
        testParse("go to");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("go", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertTrue(playerAction.hasPreposition());
        assertEquals("to", playerAction.getPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Test that 1 adjective can be parsed
     */
    @Test
    public void word3_nonIndirectTransitiveVerb_directAdjective1Noun() {
        testParse("eat c d");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("eat", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertFalse(playerAction.getDirectObjectPhrase().hasDeterminer());
        assertEquals(1, playerAction.getDirectObjectPhrase().getAdjectives().size());
        assertEquals("c", playerAction.getDirectObjectPhrase().getAdjectives().get(0));
        assertEquals("d", playerAction.getDirectObjectPhrase().getNoun());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word3_indirectTransitiveVerb_preposition_indirectNoun() {
        testParse("give to c");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("give", playerAction.getVerbPhrase().getVerb());

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
    public void word4_nonIndirectTransitiveVerb_directAdjective2Noun() {
        testParse("eat c d e ");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("eat", playerAction.getVerbPhrase().getVerb());

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
    public void word4_nonIndirectTransitiveVerb_directDeterminerAdjectiveNoun() {
        testParse("eat a c d");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("eat", playerAction.getVerbPhrase().getVerb());

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
    public void word4_indirectTransitiveVerb_directNoun_preposition_indirectNoun() {
        testParse("give c to d");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("give", playerAction.getVerbPhrase().getVerb());

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

    /**
     * TODO rename this
     */
    @Test
    public void word8_indirectTransitiveVerb_complete() {
        testParse("give all of my delicious funnel cakes of deliciousness to " +
                "the big angry demon of doom");

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("give", playerAction.getVerbPhrase().getVerb());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertEquals("all",
                playerAction.getDirectObjectPhrase().getDeterminer());
        assertFalse(playerAction.getDirectObjectPhrase().hasNoun());

        assertEquals("of", playerAction.getDirectObjectPhrase().getBelongingPreposition());

        assertTrue(playerAction.getDirectObjectPhrase().hasOwner());
        assertEquals("my",
                playerAction.getDirectObjectPhrase().getOwner().getDeterminer());
        assertTrue(playerAction.getDirectObjectPhrase().getOwner().hasAdjectives());
        assertEquals(2,
                playerAction.getDirectObjectPhrase().getOwner().getAdjectives().size());
        assertEquals("delicious",
                playerAction.getDirectObjectPhrase().getOwner().getAdjectives().get(0));
        assertEquals("funnel",
                playerAction.getDirectObjectPhrase().getOwner().getAdjectives().get(1));
        assertEquals("cakes",
                playerAction.getDirectObjectPhrase().getOwner().getNoun());


        assertEquals("of",
                playerAction.getDirectObjectPhrase().getOwner().getBelongingPreposition());

        assertTrue(playerAction.getDirectObjectPhrase().getOwner().hasOwner());
        assertEquals("deliciousness",
                playerAction.getDirectObjectPhrase().getOwner().getOwner().getNoun());

        assertTrue(playerAction.hasPreposition());
        assertEquals("to", playerAction.getPreposition());

        assertTrue(playerAction.hasIndirectObjectPhrase());
        assertEquals("the", playerAction.getIndirectObjectPhrase().getDeterminer());
        assertEquals(2,
                playerAction.getIndirectObjectPhrase().getAdjectives().size());
        assertEquals("big",
                playerAction.getIndirectObjectPhrase().getAdjectives().get(0));
        assertEquals("angry",
                playerAction.getIndirectObjectPhrase().getAdjectives().get(1));
        assertEquals("demon",
                playerAction.getIndirectObjectPhrase().getNoun());

        assertTrue(playerAction.getIndirectObjectPhrase().hasBelongingPreposition());
        assertEquals("of", playerAction.getIndirectObjectPhrase().getBelongingPreposition());

        assertTrue(playerAction.getIndirectObjectPhrase().hasOwner());
        assertEquals("doom",
                playerAction.getIndirectObjectPhrase().getOwner().getNoun());
    }

    /**
     * Test that the verb "eat" is copied all the way through.
     */
    @Test
    public void multiAction_fixSyntaxForward_singleTransitiveVerbToAll() {
        testParse("eat b, c, d");

        assertEquals(3, playerActions.size());

        assertEquals("eat", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());

        assertEquals("eat", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());

        assertEquals("eat", playerActions.get(2).getVerbPhrase().getVerb());
        assertEquals("d", playerActions.get(2).getDirectObjectPhrase().getNoun());
    }

    /**
     * Test that the verb "look" and preposition "at" is copied all the way
     * through.
     */
    @Test
    public void multiAction_fixSyntaxForward_singleIntransitiveVerbToAll() {
        testParse("look at b, c, d");

        assertEquals(3, playerActions.size());

        assertEquals("look", playerActions.get(0).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(0).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(0).getPreposition());
        assertEquals("b", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(1).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(1).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(1).getPreposition());
        assertEquals("c", playerActions.get(1).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(2).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(2).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(2).getPreposition());
        assertEquals("d", playerActions.get(2).getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that the verb "look" is copied all the way through and the
     * preposition to copy is changed from "at" to "through".
     */
    @Test
    public void multiAction_fixSyntaxForward_changePrepositions() {
        testParse("look at b, c, through d, e");

        assertEquals(4, playerActions.size());

        assertEquals("look", playerActions.get(0).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(0).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(0).getPreposition());
        assertEquals("b", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(1).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(1).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(1).getPreposition());
        assertEquals("c", playerActions.get(1).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(2).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(2).hasDirectObjectPhrase());
        assertEquals("through", playerActions.get(2).getPreposition());
        assertEquals("d", playerActions.get(2).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(3).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(3).hasDirectObjectPhrase());
        assertEquals("through", playerActions.get(3).getPreposition());
        assertEquals("e", playerActions.get(3).getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that "walk e" does not turn into "walk through e".
     */
    @Test
    public void multiAction_fixSyntaxForward_changeVerbAndPreposition() {
        testParse("look at b, c, through d, walk e");

        assertEquals(4, playerActions.size());

        assertEquals("look", playerActions.get(0).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(0).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(0).getPreposition());
        assertEquals("b", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(1).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(1).hasDirectObjectPhrase());
        assertEquals("at", playerActions.get(1).getPreposition());
        assertEquals("c", playerActions.get(1).getIndirectObjectPhrase().getNoun());

        assertEquals("look", playerActions.get(2).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(2).hasDirectObjectPhrase());
        assertEquals("through", playerActions.get(2).getPreposition());
        assertEquals("d", playerActions.get(2).getIndirectObjectPhrase().getNoun());

        assertEquals("walk", playerActions.get(3).getVerbPhrase().getVerb());
        assertEquals("e", playerActions.get(3).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(3).hasPreposition());
        assertFalse(playerActions.get(3).hasIndirectObjectPhrase());
    }

    /**
     * Test that the verb to copy "eat" changes from "eat" to "go" and
     * continues to copy all the way through.
     */
    @Test
    public void multiAction_fixSyntaxForward_twoVerbsChangeVerbToAll() {
        testParse("eat b, go c, d");

        assertEquals(3, playerActions.size());

        assertEquals("eat", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());

        assertEquals("go", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c",
                playerActions.get(1).getDirectObjectPhrase().getNoun());

        assertEquals("go", playerActions.get(2).getVerbPhrase().getVerb());
        assertEquals("d",
                playerActions.get(2).getDirectObjectPhrase().getNoun());
    }

    /**
     * Once stopping intransitive verb "look" is arrived at, stop copying
     * verbs. Action "d" should be unaltered.
     */
    @Test
    public void multiAction_fixSyntaxForward_stopCopyingAtStoppingIntransitiveVerb() {
        testParse("eat b, c, look, d");
        // TODO
    }

    /**
     * Test that preposition "to" and indirect object phrase "e" of direct
     * object "c" to direct object "b".
     */
    @Test
    public void multiAction_fixSyntaxBackwards_prepositionIndirectToAll() {
        testParse("give b, c to e");

        assertEquals(2, playerActions.size());

        assertEquals("give",playerActions.get(0).getVerbPhrase());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(0).getPreposition());
        assertEquals("e", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("give",playerActions.get(1).getVerbPhrase());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(1).getPreposition());
        assertEquals("e", playerActions.get(1).getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that the preposition "at" and indirect object phrase "h" to copy
     * change to "to" and "e" respectively.
     */
    @Test
    public void multiAction_fixSyntaxBackwards_prepositionIndirectChange() {
        testParse("give b, c to e, f, g at h");

        assertEquals(2, playerActions.size());

        assertEquals("give",playerActions.get(0).getVerbPhrase());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(0).getPreposition());
        assertEquals("e", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("give",playerActions.get(1).getVerbPhrase());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(1).getPreposition());
        assertEquals("e", playerActions.get(1).getIndirectObjectPhrase().getNoun());
    }
}
