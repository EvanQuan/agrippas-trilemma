package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.system.input.words.Word;

public class WordTest {

    @Test
    public void isArticle_empty_false() {
        assertFalse(Word.isArticle(""));
    }

    @Test
    public void isArticle_the_true() {
        assertTrue(Word.isArticle("the"));
    }

    @Test
    public void isDeterminer_0_true() {
        assertTrue(Word.isDeterminer("0"));
    }

    @Test
    public void isDeterminer_empty_false() {
        assertFalse(Word.isDeterminer(""));
    }

    @Test
    public void isDeterminer_this_true() {
        assertTrue(Word.isDeterminer("this"));
    }

    @Test
    public void isQuantifier_0_0_true() {
        assertTrue(Word.isQuantifier("0.0"));
    }

    @Test
    public void isQuantifier_0_00_true() {
        assertTrue(Word.isQuantifier("0.00"));
    }

    @Test
    public void isQuantifier_0_01_true() {
        assertFalse(Word.isQuantifier("0.01"));
    }

    @Test
    public void isQuantifier_0_true() {
        assertTrue(Word.isQuantifier("0"));
    }

    @Test
    public void isQuantifier_all_true() {
        assertTrue(Word.isQuantifier("all"));
    }

    @Test
    public void isQuantifier_empty_false() {
        assertFalse(Word.isQuantifier(""));
    }
}
