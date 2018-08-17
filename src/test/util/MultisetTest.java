package test.util;

import org.junit.Before;
import org.junit.Test;
import util.Multiset;

import static org.junit.Assert.*;

/**
 * JUnit 4 tests for {@link Multiset} class
 *
 * @author Evan Quan
 */
public class MultisetTest {

    private static Multiset<String> set;
    private static Multiset<String> set2;

    @Before
    public void setup() {
        set = new Multiset<>();
        set2 = new Multiset<>();
    }

    @Test
    public void test_add_0_to_0() {
        assertEquals(0, set.add("", 0));
        assertEquals(0, set.count(""));
    }

    @Test
    public void test_add_0_to_1() {
        assertEquals(0, set.add("", 1));
        assertEquals(1, set.add("", 0));
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_add_1() {
        set.add("", 1);
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_add_contains_false() {
        assertTrue(set.add(""));
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_add_contains_true() {
        set.add("", 1);
        assertFalse(set.add(""));
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_add_negative_0() {
        set.add("", -1);
        int result = set.count("");
        assertEquals(0, result);
    }

    @Test
    public void test_add_set_empty() {
        set.add("", 1);
        assertFalse(set.add(set2));
    }

    @Test
    public void test_add_set_nonempty() {
        set.add("", 2);
        set.add(" ", 1);
        assertTrue(set2.add(set));
        assertEquals(set.count(""), set2.count(""));
        assertEquals(set.count(" "), set2.count(" "));
    }

    @Test
    public void test_clear_false() {
        assertFalse(set.clear());
    }

    @Test
    public void test_clear_true() {
        set.add("", 2);
        set.add(" ", 1);
        assertTrue(set.clear());
        assertEquals(0, set.count(""));
        assertEquals(0, set.count(" "));
    }

    @Test
    public void test_clone() {
        set.add("", 2);
        set.add(" ", 1);
        Multiset<String> other = set.clone();
        assertEquals(set.count(""), other.count(""));
        assertEquals(set.count(" "), other.count(" "));
    }

    @Test
    public void test_contains_element_true() {
        set.add("");
        assertTrue(set.contains(""));
    }

    @Test
    public void test_contains_false() {
        assertFalse(set.contains(""));
    }

    @Test
    public void test_contains_otherMultiset_false() {
        set.add("");
        set.add("Hi", 2);
        set.add("Bye", 4);
        set2.add("");
        set2.add("Hi");
        set2.add("Bye", 5);
        assertFalse(set.contains(set2));
    }

    @Test
    public void test_contains_otherMultiset_true() {
        set.add("");
        set.add("Hi", 2);
        set.add("Bye", 4);
        Multiset<String> other = new Multiset<String>();
        other.add("");
        other.add("Hi");
        other.add("Bye", 2);
        assertTrue(set.contains(other));
    }

    @Test
    public void test_equals_0_1_false() {
        set.add("");
        assertFalse(set.equals(set2));
        assertFalse(set2.equals(set));
    }

    @Test
    public void test_equals_1_1_true() {
        set.add("");
        set2.add("");
        assertTrue(set.equals(set2));
        assertTrue(set.equals(set));
        assertTrue(set2.equals(set));
    }

    @Test
    public void test_equals_1_2_false() {
        set.add("");
        set2.add("", 2);
        assertFalse(set.equals(set2));
        assertFalse(set2.equals(set));
    }

    @Test
    public void test_equals_empty_true() {
        assertTrue(set.equals(set2));
        assertTrue(set.equals(set));
        assertTrue(set2.equals(set));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_equals_equals_String_false() {
        assertFalse(set.equals(""));
    }

    @Test
    public void test_getElementCount_2_2_true() {
        set.add("", 2);
        set.add(" ", 1);
        assertEquals(2, set.getElementCount());
    }

    @Test
    public void test_getOccurrenceCount_3_3_true() {
        set.add("", 2);
        set.add(" ", 1);
        assertEquals(3, set.getOccurrenceCount());
    }

    @Test
    public void test_isEmpty_true() {
        assertTrue(set.isEmpty());
    }

    @Test
    public void test_remove() {
        set.add("", 2);
        set.remove("");
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_remove_0_from_0() {
        assertEquals(0, set.remove("", 0));
        assertEquals(0, set.count(""));
    }

    @Test
    public void test_remove_0_from_1() {
        assertEquals(0, set.add("", 1));
        assertEquals(1, set.remove("", 0));
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_remove_negative() {
        set.remove("", -1);
        int result = set.count("");
        assertEquals(1, result);
    }

    @Test
    public void test_remove_positive() {
        set.add("", 2);
        set.remove("", 1);
        int result = set.count("");
        assertEquals(1, result);
    }

    @Test
    public void test_remove_set() {
        set.add("", 2);
        set.add(" ", 1);
        Multiset<String> toRemove = new Multiset<String>();
        for (String element : set) {
            toRemove.add(element, set.count(element));
        }
        set.remove(toRemove);
        assertEquals(0, set.count(""));
        assertEquals(0, set.count(" "));
    }

    @Test
    public void test_removeAll_contains_false() {
        assertFalse(set.removeAll(""));
    }

    @Test
    public void test_removeAll_contains_true() {
        set.add("", 2);
        set.add(" ", 1);
        assertTrue(set.removeAll(""));
        assertEquals(0, set.count(""));
        assertEquals(1, set.count(" "));
    }

    @Test
    public void test_retainAll_contains_0_only() {
        set.add("", 0);
        assertFalse(set.retainAll(""));
        assertEquals(0, set.count(""));
    }

    @Test
    public void test_retainAll_contains_0_others() {
        set.add("", 0);
        set.add(" ", 1);
        assertFalse(set.retainAll(""));
        assertEquals(0, set.count(""));
        assertEquals(0, set.count(" "));
    }

    @Test
    public void test_retainAll_contains_1_only() {
        set.add("", 1);
        assertFalse(set.retainAll(""));
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_retainAll_contains_1_others() {
        set.add("", 1);
        set.add(" ", 1);
        assertTrue(set.retainAll(""));
        assertEquals(1, set.count(""));
        assertEquals(0, set.count(" "));
    }

    @Test
    public void test_retainAll_empty() {
        assertFalse(set.retainAll(""));
    }

    @Test
    public void test_setCount_decrease_2_1() {
        assertEquals(0, set.add("", 2));
        assertEquals(2, set.setCount("", 1));
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_setCount_increase_2_3() {
        assertEquals(0, set.add("", 2));
        assertEquals(2, set.setCount("", 3));
        assertEquals(3, set.count(""));
    }

    @Test
    public void test_setCount_negative() {
        set.setCount("", -1);
        assertEquals(0, set.count(""));
    }

    @Test
    public void test_setCount_same_0() {
        set.setCount("", 0);
        assertEquals(0, set.count(""));
    }

    @Test
    public void test_setCount_same_1() {
        set.add("", 1);
        set.setCount("", 1);
        assertEquals(1, set.count(""));
    }

    @Test
    public void test_toString_empty() {
        assertEquals("[ Multiset | Types: 0 | Elements: 0 | {} ]", set.toString());
    }

    @Test
    public void test_toString_Foo_1() {
        set.add("Foo");
        assertEquals("[ Multiset | Types: 1 | Elements: 1 | {Foo=1} ]", set.toString());
    }

}
