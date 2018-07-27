package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import game.system.input.words.ObjectPhrase;

/**
 * JUnit 4 tests for {@link ObjectPhrase}
 *
 * @author Evan Quan
 *
 */
public class ObjectPhraseTest {

    public static ObjectPhrase one;
    public static ObjectPhrase two;

    @Test
    public void equals_adjectives_adjectives_true() {
        one.setAdjectives(new ArrayList<>());
        two.setAdjectives(new ArrayList<>());
        assertEquals(one, two);
    }

    @Test
    public void equals_all_all_true() {
        one.setDeterminer("");
        two.setDeterminer("");
        one.setAdjectives(new ArrayList<>());
        two.setAdjectives(new ArrayList<>());
        one.setNoun("");
        two.setNoun("");
        assertEquals(one, two);
    }

    @Test
    public void equals_allNull_allNull_true() {
        assertEquals(one, two);
    }

    @Test
    public void equals_determiner_determiner_true() {
        one.setDeterminer("");
        two.setDeterminer("");
        assertEquals(one, two);
    }

    @Test
    public void equals_differentAdjectives_false() {
        one.setDeterminer("");
        two.setDeterminer("");
        one.setAdjectives(new ArrayList<>(Arrays.asList(new String[] { "" })));
        two.setAdjectives(new ArrayList<>());
        one.setNoun("");
        two.setNoun("");
        assertNotEquals(one, two);
    }

    @Test
    public void equals_differentDeterminer_false() {
        one.setDeterminer(" ");
        two.setDeterminer("");
        one.setAdjectives(new ArrayList<>());
        two.setAdjectives(new ArrayList<>());
        one.setNoun("");
        two.setNoun("");
        assertNotEquals(one, two);
    }

    @Test
    public void equals_differentNoun_false() {
        one.setDeterminer("");
        two.setDeterminer("");
        one.setAdjectives(new ArrayList<>());
        two.setAdjectives(new ArrayList<>());
        one.setNoun(" ");
        two.setNoun("");
        assertNotEquals(one, two);
    }

    @Test
    public void equals_noun_noun_true() {
        one.setNoun("");
        two.setNoun("");
        assertEquals(one, two);
    }

    @Test
    public void hasSameArticle_allNull_allNull_true() {
        assertTrue(one.hasSameDeterminer(two));
    }

    @Before
    public void setUp() {
        one = new ObjectPhrase();
        two = new ObjectPhrase();
    }

}
