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
        printTitleln("Test Menu");
        println("You are now in the Test menu.");
        println("Valid input: " + Arrays.asList(VALID_INPUT));
        println("To go to the main menu, type \"" + Arrays.asList(BACK) + "\"");
    }
    @Override
    public void processInput() {
        if (inputStartsWith(BACK)) {
            changeToMainMenu();
        } else if (inputStartsWithStrip(VALID_INPUT)) {

            if (inputStartsWith(new String[] {"potato", "the potato"})) {
                println("You pick up the potato.");
            } else if (inputStartsWith("llama")) {
                println("You pick up the llama");
            } else {
                println("there is no " + getInputString() + " here to pick up");
            }
            println("Valid input.");
            println("inputString: \"" + getInputString() + "\"");
            println("inputWords: \"" + Arrays.asList(getInputWords()) + "\"");
            println("remainingWords: \"" + getRemainingString() + "\"");
        } else {
            println("Invalid input.");
        }
    }
    public void checkDrop() {
        if (inputStartsWith("drop")) {
            println("You have nothing to drop");
        }
    }


    public static TestMenu getInstance() {
        if (instance == null) {
            instance = new TestMenu();
        }
        return instance;
    }
}
