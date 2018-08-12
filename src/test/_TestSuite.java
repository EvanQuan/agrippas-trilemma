package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.game.system.input.*;
import test.game.system.input.words.ObjectPhraseTest;
import test.game.system.input.words.WordTest;
import test.util.CollectionUtilsTest;
import test.util.MultisetTest;
import test.util.TextUtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CollectionUtilsTest.class,
        FuncUtilsTest.class,
        LocationTest.class,
        TestTest.class,
        PlayerActionTest.class,
        PlayerInputParser_addToken_Test.class,
        PlayerInputParser_getObjectPhrase_Test.class,
        PlayerInputParser_lexicalAnalysis_Test.class,
        PlayerInputParser_parse_Test.class,
        PlayerInputParser_splitTokensByActions_Test.class,
        VerbAgnosticPlayerInputParser_lexicalAnalysis_Test.class,
        VerbAgnosticPlayerInputParser_addToken_Test.class,
        VerbAgnosticPlayerInputParser_getObjectPhrase_Test.class,
        VerbAgnosticPlayerInputParser_parse_Test.class,
        ObjectPhraseTest.class,
        MultisetTest.class,
        TextUtilsTest.class,
        WordTest.class,
        ExitTest.class
})

/**
 * Runs all test classes under JUnit 4
 *
 * @author Evan Quan
 */
public class _TestSuite {
}
