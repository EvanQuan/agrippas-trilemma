package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import game.system.parsing.PlayerCommand;
import org.junit.Before;
import org.junit.Test;

import game.system.parsing.PlayerCommandParser;

public class PlayerCommandParserTest {

    public static PlayerCommand verb;
    public static PlayerCommand empty;
    public static PlayerCommand verb_directNoun;

    @Before
    public void setUp() {
        verb = PlayerCommandParser.parse("verb");
        empty = PlayerCommandParser.parse("");
        verb_directNoun = PlayerCommandParser.parse("verb noun");
    }

    @Test
    public void getVerbPhrase_verb() {
//        assertFalse(verb.hasDirectObjectPhrase());
//        assertFalse(verb.hasIndirectObjectPhrase());
//        assertFalse(verb.hasPreposition());
//        assertEquals("verb", verb.getVerbPhrase());
//        assertEquals(null, verb.getPreposition());
//        assertEquals(null, verb.getDirectObjectPhrase());
//        assertEquals(null, verb.getIndirectObjectPhrase());

    }
}
