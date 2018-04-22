package test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test class for the TextUtil class
 *
 * @author Evan Quan
 *
 */

public class TextUtilTest {

    @Test
    public void testSplitCamelCase() {
        String input = "thisIsTheInputString";
        String[] expected = { "this", "Is", "The", "Input", "String" };
        String[] result = TextUtil.splitCamelCase(input);
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void testSplitCamelCaseToString() {
        String input = "thisIsTheInputString";
        String expected = "this Is The Input String";
        String result = TextUtil.splitCamelCaseToString(input);
        assertEquals(expected, result);
    }

    /**
     * Check if startsWithVowel works
     */
    @Test
    public void testStartsWithVowel() {
        assertEquals(true, TextUtil.startsWithVowel("ab"));
        assertEquals(true, TextUtil.startsWithVowel("eb"));
        assertEquals(true, TextUtil.startsWithVowel("ib"));
        assertEquals(true, TextUtil.startsWithVowel("ob"));
        assertEquals(true, TextUtil.startsWithVowel("ub"));
        assertEquals(false, TextUtil.startsWithVowel("yb"));
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
        String result1 = TextUtil.stripExtension(input1);
        String result2 = TextUtil.stripExtension(input2);
        String result3 = TextUtil.stripExtension(input3);
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
        String result = TextUtil.toLowerTitleCase(input);
        assertEquals(expected, result);
    }

    /**
     * Check if apostrophes are not counted as new words
     */
    @Test
    public void testToTitleCaseWithApostrophes() {
        String input = "he's ish'kafel";
        String expected = "He's Ish'kafel";
        String result = TextUtil.toTitleCase(input);
        assertEquals(expected, result);
    }

    @Test
    public void testToTitleCaseWithLowerCaseInput() {
        String input = "this is the input string";
        String expected = "This Is The Input String";
        String result = TextUtil.toTitleCase(input);
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
        String result = TextUtil.toTitleCase(input);
        assertEquals(expected, result);
    }
}
