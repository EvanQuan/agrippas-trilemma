package test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.system.parsing.PlayerCommand;
import game.system.parsing.words.ObjectPhrase;

public class PlayerCommandTest {

    public static PlayerCommand one;
    public static PlayerCommand two;
    public static ObjectPhrase onedo;
    public static ObjectPhrase oneio;
    public static ObjectPhrase twodo;
    public static ObjectPhrase twoio;

    @Test
    public void equals_directObjectPhrase_true() {
        one = new PlayerCommand("a");
        two = new PlayerCommand("a");
        onedo.setNoun("a");
        twodo.setNoun("a");
        one.setDirectObjectPhrase(onedo);
        two.setDirectObjectPhrase(twodo);
        assertEquals(one, two);
    }

    @Test
    public void equals_empty_true() {
        assertEquals(one, two);
    }

    @Test
    public void equals_indirectObjectPhrase_true() {
        one = new PlayerCommand("a");
        two = new PlayerCommand("a");
        oneio.setNoun("a");
        twoio.setNoun("a");
        one.setIndirectObjectPhrase(oneio);
        two.setIndirectObjectPhrase(twoio);
        assertEquals(one, two);
    }

    @Test
    public void equals_nonPlayerCommand_false() {
        assertNotEquals(one, "");
    }

    @Test
    public void equals_verb_false() {
        one = new PlayerCommand("a");
        two = new PlayerCommand("b");
        one.setVerbPhrase("a");
        two.setVerbPhrase("b");
        assertNotEquals(one, two);
    }

    @Test
    public void equals_verb_true() {
        one = new PlayerCommand("a");
        two = new PlayerCommand("a");
        one.setVerbPhrase("a");
        two.setVerbPhrase("a");
        assertEquals(one, two);
    }

    @Test
    public void hasDirectObjectPhrase_false() {
        assertFalse(one.hasDirectObjectPhrase());
    }

    @Test
    public void hasDirectObjectPhrase_true() {
        one.setDirectObjectPhrase(onedo);
        assertTrue(one.hasDirectObjectPhrase());
    }

    @Test
    public void hasIndirectObjectPhrase_false() {
        assertFalse(one.hasIndirectObjectPhrase());
    }

    @Test
    public void hasIndirectObjectPhrase_true() {
        one.setIndirectObjectPhrase(oneio);
        assertTrue(one.hasIndirectObjectPhrase());
    }

    @Test
    public void hasPreposition_false() {
        assertFalse(one.hasPreposition());
    }

    @Test
    public void hasPreposition_true() {
        one.setPreposition("");
        assertTrue(one.hasPreposition());
    }

    @Before
    public void setUp() {
        one = new PlayerCommand("");
        two = new PlayerCommand("");
        onedo = new ObjectPhrase();
        oneio = new ObjectPhrase();
        twodo = new ObjectPhrase();
        twoio = new ObjectPhrase();
    }

}
