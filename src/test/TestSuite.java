package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.util.ArrayUtilTest;
import test.util.TextUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ LocationTest.class, TestTest.class, ArrayUtilTest.class, TextUtilTest.class, ExitTest.class })
/**
 * Runs all test classes under JUnit 4
 *
 * @author Evan Quan
 * @since March 4, 2018
 *
 */
public class TestSuite {
}
