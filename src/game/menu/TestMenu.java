package game.menu;

import java.util.*;

/**
 * Test menu for menu manager to transfer between menuss
 */
public class TestMenu extends Menu {
    private static TestMenu instance;

    public static final String[] BACK = {"q"};
    public static final String[] VALID_INPUT = {"take", "pick up","get"};
    private TestMenu() {

    }

    @Override
    public void outputPrompt() {
        outputlnTitle("Test Menu");
        outputln("You are now in the Test menu.");
        outputln("Valid input: " + Arrays.asList(VALID_INPUT));
        outputln("To go to the main menu, type \"" + Arrays.asList(BACK) + "\"");
    }
    @Override
    public void processInput() {
        if (inputStartsWith(BACK)) {
            changeToMainMenu();
        } else if (inputStartsWithStrip(VALID_INPUT)) {

            if (inputStartsWith(new String[] {"potato", "the potato"})) {
                outputln("You pick up the potato.");
            } else if (inputStartsWith("llama")) {
                outputln("You pick up the llama");
            } else {
                outputln("there is no " + getInputString() + " here to pick up");
            }
            outputln("Valid input.");
            outputln("inputString: \"" + getInputString() + "\"");
            outputln("inputWords: \"" + Arrays.asList(getInputWords()) + "\"");
            outputln("remainingWords: \"" + getRemainingString() + "\"");
        } else {
            outputln("Invalid input.");
        }
    }
    public void checkDrop() {
        if (inputStartsWith("drop")) {
            outputln("You have nothing to drop");
        }
    }


    public static TestMenu getInstance() {
        if (instance == null) {
            instance = new TestMenu();
        }
        return instance;
    }
}
