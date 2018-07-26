package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.util.ArrayUtilsTest;
import test.util.MultisetTest;
import test.util.TextUtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ArrayUtilsTest.class, FuncUtilsTest.class, LocationTest.class, TestTest.class, ActionTest.class,
        PlayerCommandParser_lexicalAnalysis_Test.class, PlayerCommandParser_addToken_Test.class,
        PlayerCommandParser_getObjectPhrase_Test.class, PlayerCommandParser_parse_Test.class,
        PlayerCommandParser_splitTokensByActions_Test.class, VerbAgnosticPlayerCommandParser_lexicalAnalysis_Test.class,
        VerbAgnosticPlayerCommandParser_addToken_Test.class, VerbAgnosticPlayerCommandParser_getObjectPhrase_Test.class,
        VerbAgnosticPlayerCommandParser_parse_Test.class, ObjectPhraseTest.class, MultisetTest.class,
        TextUtilsTest.class, WordTest.class, ExitTest.class })
/**
 * Runs all test classes under JUnit 4
 *
 * @author Evan Quan
 */
public class _TestSuite {
}
