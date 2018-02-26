package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ LocationTest.class, TestTest.class, UtilityTest.class })
/**
 * Runs all test classes under JUnit 4
 *
 * @author Evan Quan
 *
 */
public class TestSuite {
}
