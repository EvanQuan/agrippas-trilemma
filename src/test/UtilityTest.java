package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import game.system.Utility;

/**
 * JUnit test class for the TextUtility class
 *
 * @author Evan Quan
 *
 */
public class UtilityTest {

    /**
     * Check if an array with longest to shorted element lengths can be sorted to
     * longest first
     */
    @Test
    public void testSortByLongestWithLongestFirstInput() {
        String[] input = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] expected = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] result = Utility.sortByLongestFirst(input);
        assertTrue(Arrays.equals(expected, result));
    }

    /**
     * Check if an array with random element lengths can be sorted to longest first
     */
    @Test
    public void testSortByLongestWithRandomInput() {
        String[] input = { "2. abc", "4. a", "1. abcd", "3. ab" };
        String[] expected = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] result = Utility.sortByLongestFirst(input);
        assertTrue(Arrays.equals(expected, result));
    }

    /**
     * Check if an array with shortest to longest element lengths can be sorted to
     * longest first
     */
    @Test
    public void testSortByLongestWithShortestFirstInput() {
        String[] input = { "4. a", "3. ab", "2. abc", "1. abcd" };
        String[] expected = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] result = Utility.sortByLongestFirst(input);
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void testSplitCamelCase() {
        String input = "thisIsTheInputString";
        String[] expected = { "this", "Is", "The", "Input", "String" };
        String[] result = Utility.splitCamelCase(input);
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void testSplitCamelCaseToString() {
        String input = "thisIsTheInputString";
        String expected = "this Is The Input String";
        String result = Utility.splitCamelCaseToString(input);
        assertEquals(expected, result);
    }

    /**
     * Check if startsWithVowel works
     */
    @Test
    public void testStartsWithVowel() {
        assertEquals(true, Utility.startsWithVowel("ab"));
        assertEquals(true, Utility.startsWithVowel("eb"));
        assertEquals(true, Utility.startsWithVowel("ib"));
        assertEquals(true, Utility.startsWithVowel("ob"));
        assertEquals(true, Utility.startsWithVowel("ub"));
        assertEquals(false, Utility.startsWithVowel("yb"));
    }

    /**
     * Check if strip extension works
     */
    @Test
    public void testStripExtention() {
        String input1 = "foo.exe";
        String input2 = "bar.txt";
        String input3 = "bar.asdf.qwerty";
        String expected1 = "foo";
        String expected2 = "bar";
        String expected3 = "bar.asdf";
        String result1 = Utility.stripExtension(input1);
        String result2 = Utility.stripExtension(input2);
        String result3 = Utility.stripExtension(input3);
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    /**
     * Check if all caps strings are converted to lower title case
     */
    @Test
    public void testToLowerTitleCase() {
        String input = "THIS IS THE INPUT STRING";
        String expected = "This Is The Input String";
        String result = Utility.toLowerTitleCase(input);
        assertEquals(expected, result);
    }

    /**
     * Check if apostrophes are not counted as new words
     */
    @Test
    public void testToTitleCaseWithApostrophes() {
        String input = "he's ish'kafel";
        String expected = "He's Ish'kafel";
        String result = Utility.toTitleCase(input);
        assertEquals(expected, result);
    }

    @Test
    public void testToTitleCaseWithLowerCaseInput() {
        String input = "this is the input string";
        String expected = "This Is The Input String";
        String result = Utility.toTitleCase(input);
        assertEquals(expected, result);
    }

    /**
     * Check if letters follow the first are ignored In other words, all caps
     * strings are not changed
     */
    @Test
    public void testToTitleCaseWithUpperCaseInput() {
        String input = "THIS IS THE INPUT STRING";
        String expected = "THIS IS THE INPUT STRING";
        String result = Utility.toTitleCase(input);
        assertEquals(expected, result);
    }

}
