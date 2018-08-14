package test.game.system.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.intellij.codeInsight.editorActions.smartEnter.PlainEnterProcessor;
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

    /**
     * Test parse by parsing an input string and conveniently saving its
     * components in global variables.
     *
     * @param string
     */
    public void testParse(String string) {
        testParse(string, false);
    }

    /**
     * Test parse by parsing an input string and conveniently saving its
     * components in global variables. Can print the command to see all the
     * components. Useful for when test case fails.
     *
     * @param string
     * @param print
     */
    public void testParse(String string, boolean print) {
        command = PlayerInputParser.parse(string);
        playerActions = command.getPlayerActions();
        if (print) {
            System.out.println(command);
        }
        if (playerActions.isEmpty()) {
            playerAction = null;
        } else {
            playerAction = playerActions.get(0);
        }
    }

    /**
     * Test that an empty string parses into an empty command with no actions.
     */
    @Test
    public void word0_empty() {
        testParse("");

        assertFalse(command.hasActions());
        assertTrue(command.isEmpty());
    }

    /**
     * Test that a single determiner skips the verb parsing.
     */
    @Test
    public void word1_directDeterminer() {
        testParse("the");

        assertFalse(playerAction.hasVerbPhrase());

        assertTrue(playerAction.hasDirectObjectPhrase());
        assertEquals("the", playerAction.getDirectObjectPhrase().getDeterminer());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Check that a single preposition skips the verb and direct object phrase
     * input.
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
     * Test that a single non-verb word counts as a noun. Other playerAction
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

    /**
     * Test that an adverb suffix is treated as an adverb.
     */
    @Test
    public void word1_adverbSuffix() {
        testParse("ly");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertTrue(playerAction.getVerbPhrase().hasAdverbs());
        assertEquals(1, playerAction.getVerbPhrase().getAdverbs().size());
        assertEquals("ly", playerAction.getVerbPhrase().getAdverbs().get(0));
        assertFalse(playerAction.getVerbPhrase().hasVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Test that an adverb is treated as an adverb.
     */
    @Test
    public void word1_adverbQuickly() {
        testParse("quickly");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertTrue(playerAction.getVerbPhrase().hasAdverbs());
        assertEquals(1, playerAction.getVerbPhrase().getAdverbs().size());
        assertEquals("quickly",
                playerAction.getVerbPhrase().getAdverbs().get(0));
        assertFalse(playerAction.getVerbPhrase().hasVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word1_indirectTransitiveVerb_verb() {
        testParse("give");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("give", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word1_nonIndirectTransitiveVerb_verb() {
        testParse("eat");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("eat", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word1_optionallyIndirectTransitiveVerb_verb() {
        testParse("use");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertEquals("use", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    @Test
    public void word2_adverbVerb() {
        testParse("quickly run");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertTrue(playerAction.getVerbPhrase().hasAdverbs());
        assertEquals(1, playerAction.getVerbPhrase().getAdverbs().size());
        assertEquals("quickly",
                playerAction.getVerbPhrase().getAdverbs().get(0));
        assertTrue(playerAction.getVerbPhrase().hasVerb());
        assertEquals("run", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertFalse(playerAction.hasPreposition());

        assertFalse(playerAction.hasIndirectObjectPhrase());
    }

    /**
     * Test that the last word is treated is a noun, and preceding word is
     * treated as an adjective.
     */
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
     * Test that a noun can be parsed have a known verb.
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

    /**
     * Test that a known preposition can be parsed have a known verb.
     */
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
     * Check that an adverb is not removed by forward or backwards syntax fix.
     */
    @Test
    public void word3_adverbVerbPrepositionIndirect() {
        testParse("quickly run behind wall");

        assertEquals(1, playerActions.size());

        assertTrue(playerAction.hasVerbPhrase());
        assertTrue(playerAction.getVerbPhrase().hasAdverbs());
        assertEquals(1, playerAction.getVerbPhrase().getAdverbs().size());
        assertEquals("quickly",
                playerAction.getVerbPhrase().getAdverbs().get(0));
        assertTrue(playerAction.getVerbPhrase().hasVerb());
        assertEquals("run", playerAction.getVerbPhrase().getVerb());

        assertFalse(playerAction.hasDirectObjectPhrase());

        assertTrue(playerAction.hasPreposition());
        assertEquals("behind", playerAction.getPreposition());

        assertTrue(playerAction.hasIndirectObjectPhrase());
        assertEquals("wall", playerAction.getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that 1 adjective can be parsed between a verb and noun
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

    /**
     * Test that the object phrase after the preposition is parsed as an
     * indirect object phrase.
     */
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
     * Test that multiple adjectives can be parsed before a noun for a direct
     * object phrase.
     */
    @Test
    public void word4_nonIndirectTransitiveVerb_directAdjective2Noun() {
        testParse("eat c d e");

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

    /**
     * Test that a known determiner is parsed not as an adjective for a
     * direct object phrase.
     */
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

    /**
     * Test that a direct and indirect object phrase is is parsed around a
     * preposition.
     */
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
        testParse("give all of my delicious funnel cakes of deliciousness"
                + " to the big angry demon of doom");

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
     * Test that the verb "eat" is copied all the way through to following
     * actions.
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
     * through to following actions.
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
     * Test that direct verb phrase "hammer" and preposition "on" copy over,
     * and that (initially) direct object phrase "wall" gets converts to an
     * indirect object phrase, such that the second action becomes "use
     * hammer on wall".
     */
    @Test
    public void multiAction_fixSyntaxForward_copyDirectAndPrepositionChangeDirect() {
        testParse("use hammer on door and wall");

        assertEquals(2, playerActions.size());

        assertEquals("use", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("hammer",
                playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("on", playerActions.get(0).getPreposition());
        assertEquals("door",
                playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("use", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("hammer",
                playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("on", playerActions.get(1).getPreposition());
        assertEquals("wall",
                playerActions.get(1).getIndirectObjectPhrase().getNoun());

    }

    /**
     * Test that the preposition to copy "on" changes to "against" while the
     * direct object phrase "hammer" continues to copy forward.
     */
    @Test
    public void multiAction_fixSyntaxForward_copyDirectAndPrepositionChangeDirectChangePreposition() {
        testParse("use hammer on door and wall and against box");

        assertEquals(3, playerActions.size());

        assertEquals("use", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("hammer",
                playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("on", playerActions.get(0).getPreposition());
        assertEquals("door",
                playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("use", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("hammer",
                playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("on", playerActions.get(1).getPreposition());
        assertEquals("wall",
                playerActions.get(1).getIndirectObjectPhrase().getNoun());

        assertEquals("use", playerActions.get(2).getVerbPhrase().getVerb());
        assertEquals("hammer",
                playerActions.get(2).getDirectObjectPhrase().getNoun());
        assertEquals("against", playerActions.get(2).getPreposition());
        assertEquals("box",
                playerActions.get(2).getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that the verb to copy "eat" changes from "eat" to "go" and
     * continues to copy all the way through.
     */
    @Test
    public void multiAction_fixSyntaxForward_twoVerbsChangeVerbToAll() {
        testParse("eat b, go c, d");

        assertEquals(3, playerActions.size());

        assertTrue(playerActions.get(0).hasVerbPhrase());
        assertEquals("eat", playerActions.get(0).getVerbPhrase().getVerb());
        assertTrue(playerActions.get(0).hasDirectObjectPhrase());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(0).hasPreposition());
        assertFalse(playerActions.get(0).hasIndirectObjectPhrase());

        assertTrue(playerActions.get(1).hasVerbPhrase());
        assertEquals("go", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(1).hasPreposition());
        assertFalse(playerActions.get(1).hasIndirectObjectPhrase());

        assertTrue(playerActions.get(2).hasVerbPhrase());
        assertEquals("go", playerActions.get(2).getVerbPhrase().getVerb());
        assertEquals("d", playerActions.get(2).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(2).hasPreposition());
        assertFalse(playerActions.get(2).hasIndirectObjectPhrase());
    }

    /**
     * Once stopping intransitive verb "look" is arrived at, stop copying
     * verbs. Action "d" should be unaltered.
     */
    @Test
    public void multiAction_fixSyntaxForward_stopCopyingAtStoppingIntransitiveVerb() {
        testParse("eat b, c, look, d");

        assertEquals(4, playerActions.size());

        assertEquals("eat", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(0).hasPreposition());
        assertFalse(playerActions.get(0).hasIndirectObjectPhrase());

        assertEquals("eat", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(1).hasPreposition());
        assertFalse(playerActions.get(1).hasIndirectObjectPhrase());

        assertEquals("look", playerActions.get(2).getVerbPhrase().getVerb());
        assertFalse(playerActions.get(2).hasDirectObjectPhrase());
        assertFalse(playerActions.get(2).hasPreposition());
        assertFalse(playerActions.get(2).hasIndirectObjectPhrase());

        assertFalse(playerActions.get(3).hasVerbPhrase());
        assertEquals("d", playerActions.get(3).getDirectObjectPhrase().getNoun());
        assertFalse(playerActions.get(3).hasPreposition());
        assertFalse(playerActions.get(3).hasIndirectObjectPhrase());
    }

    /**
     * Test that preposition "to" and indirect object phrase "e" of direct
     * object "c" to direct object "b".
     */
    @Test
    public void multiAction_fixSyntaxBackwards_prepositionIndirectToAll() {
        testParse("give b, c to e");

        assertEquals(2, playerActions.size());

        assertEquals("give",playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(0).getPreposition());
        assertEquals("e", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("give",playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(1).getPreposition());
        assertEquals("e", playerActions.get(1).getIndirectObjectPhrase().getNoun());
    }

    /**
     * Test that a direct object "b" and preposition "to" can copy to action
     * "d" and correct move "d" to the indirect object.
     */
    @Test
    public void multiAction_fixSyntaxForwards_transferDirectAndPrepositionForward() {
        testParse("give b to c, d");

        assertEquals(2, playerActions.size());

        assertEquals("give",
                playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b",
                playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("to",
                playerActions.get(0).getPreposition());
        assertEquals("c",
                playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("give",
                playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("b",
                playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("to",
                playerActions.get(1).getPreposition());
        assertEquals("d",
                playerActions.get(1).getIndirectObjectPhrase().getNoun());
    }


    @Test
    public void excludingPrepositions_all_except_1() {
        testParse("drop all except b");

        assertEquals(1, playerActions.size());

        assertEquals("drop", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("all", playerActions.get(0).getDirectObjectPhrase().getDeterminer());
        assertFalse(playerActions.get(0).getDirectObjectPhrase().hasNoun());
        assertEquals("except", playerActions.get(0).getPreposition());
        assertEquals("b", playerActions.get(0).getIndirectObjectPhrase().getNoun());
    }

    @Test
    public void excludingPrepositions_2_except_1() {
        testParse("drop b, c except b", true);

        assertEquals(2, playerActions.size());

        assertEquals("drop", playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("except", playerActions.get(0).getPreposition());
        assertEquals("b", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("drop", playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("except", playerActions.get(1).getPreposition());
        assertEquals("b", playerActions.get(1).getIndirectObjectPhrase().getNoun());
    }
    /**
     * Check that actions can multiply if multiple direct objects share a
     * single preposition with multiple indirect objects.
     *
     * This requires that action multiplication is implemented.
     */
    @Test
    public void multiplyActions_2x2() {
        testParse("give b, c, to d, e", true);

        assertEquals(4, playerActions.size());

        assertEquals("give",
                playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b",
                playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("to",
                playerActions.get(0).getPreposition());
        assertEquals("d",
                playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("give",
                playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c",
                playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("to",
                playerActions.get(1).getPreposition());
        assertEquals("d",
                playerActions.get(1).getIndirectObjectPhrase().getNoun());

        assertEquals("give",
                playerActions.get(2).getVerbPhrase().getVerb());
        assertTrue(playerActions.get(2).hasDirectObjectPhrase());
        assertEquals("b",
                playerActions.get(2).getDirectObjectPhrase().getNoun());
        assertEquals("to",
                playerActions.get(2).getPreposition());
        assertEquals("e",
                playerActions.get(2).getIndirectObjectPhrase().getNoun());

        assertEquals("give",
                playerActions.get(3).getVerbPhrase().getVerb());
        assertTrue(playerActions.get(3).hasDirectObjectPhrase());
        assertEquals("c",
                playerActions.get(3).getDirectObjectPhrase().getNoun());
        assertEquals("to",
                playerActions.get(3).getPreposition());
        assertEquals("e",
                playerActions.get(3).getIndirectObjectPhrase().getNoun());

    }

    /**
     * This is tricky what the expected results should be because it is
     * ambiguous how f should be treated.
     *
     * If f is ignored in forward fix, then the backwards fix will changed it
     * to "give f at h", but messes with other test cases. This gets more
     * complicated if/when action multiplying gets implemented.
     */
    @Test
    public void multiplyActions_2x2_1() {
        testParse("give b, c to e, f, g at h", true);

        assertEquals(4, playerActions.size());

        assertEquals("give",playerActions.get(0).getVerbPhrase().getVerb());
        assertEquals("b", playerActions.get(0).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(0).getPreposition());
        assertEquals("e", playerActions.get(0).getIndirectObjectPhrase().getNoun());

        assertEquals("give",playerActions.get(1).getVerbPhrase().getVerb());
        assertEquals("c", playerActions.get(1).getDirectObjectPhrase().getNoun());
        assertEquals("to", playerActions.get(1).getPreposition());
        assertEquals("e", playerActions.get(1).getIndirectObjectPhrase().getNoun());

        assertEquals("give",playerActions.get(2).getVerbPhrase().getVerb());
        assertEquals("f", playerActions.get(2).getDirectObjectPhrase().getNoun());
        assertEquals("at", playerActions.get(2).getPreposition());
        assertEquals("h", playerActions.get(2).getIndirectObjectPhrase().getNoun());

        assertEquals("give",playerActions.get(3).getVerbPhrase().getVerb());
        assertEquals("g", playerActions.get(3).getDirectObjectPhrase().getNoun());
        assertEquals("at", playerActions.get(3).getPreposition());
        assertEquals("h", playerActions.get(3).getIndirectObjectPhrase().getNoun());
    }
}
