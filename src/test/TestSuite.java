package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.util.ArrayUtilsTest;
import test.util.TextUtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ LocationTest.class, TestTest.class, ArrayUtilsTest.class, TextUtilsTest.class, ExitTest.class })
/**
 * Runs all test classes under JUnit 4
 *
 * @author Evan Quan
 * @since March 4, 2018
 *
 */
public class TestSuite {
}
