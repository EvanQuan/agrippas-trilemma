package test.util;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test class for the ArrayUtil class
 *
 * @author Evan Quan
 *
 */
public class ArrayUtilTest {

    /**
     * Check if an array with longest to shorted element lengths can be sorted to
     * longest first
     */
    @Test
    public void testSortByLongestWithLongestFirstInput() {
        String[] input = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] expected = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] result = ArrayUtil.sortByLongestFirst(input);
        assertTrue(Arrays.equals(expected, result));
    }

    /**
     * Check if an array with random element lengths can be sorted to longest first
     */
    @Test
    public void testSortByLongestWithRandomInput() {
        String[] input = { "2. abc", "4. a", "1. abcd", "3. ab" };
        String[] expected = { "1. abcd", "2. abc", "3. ab", "4. a" };
        String[] result = ArrayUtil.sortByLongestFirst(input);
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
        String[] result = ArrayUtil.sortByLongestFirst(input);
        assertTrue(Arrays.equals(expected, result));
    }

}
