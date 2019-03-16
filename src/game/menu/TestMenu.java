package game.menu;

/**
 * Test menu for menu manager to transfer between menuss
 */
public class TestMenu extends Menu {
    private static TestMenu instance;

    public static final String[] BACK = {"q"};
    public static final String[] VALID_INPUT = {"take", "pick up", "get"};

    private TestMenu() {

    }

    public void printMainPrompt() {
//        printTitleln("Test Menu");
//        println("You are now in the Test menu.");
//        println("Valid receiveInput: " + Arrays.asList(VALID_INPUT));
//        println("To go to the main menu, type \"" + Arrays.asList(BACK) + "\"");
    }

    /**
     * Prints a message signifying that the user can inputted invalid input.
     */
    @Override
    protected void printInvalidInput() {

    }

    @Override
    public void processInput() {
        // TODO
//        if (inputStartsWith(BACK)) {
//            changeToMainMenu();
//        } else if (inputStartsWithStrip(VALID_INPUT)) {
//
//            if (inputStartsWith(new String[] {"potato", "the potato"})) {
//                println("You pick up the potato.");
//            } else if (inputStartsWith("llama")) {
//                println("You pick up the llama");
//            } else {
//                println("there is no " + getInputString() + " here to pick up");
//            }
//            println("Valid receiveInput.");
//            println("inputString: \"" + getInputString() + "\"");
//            println("inputWords: \"" + Arrays.asList(getInputWords()) + "\"");
//            println("remainingWords: \"" + getRemainingString() + "\"");
//        } else {
//            println("Invalid receiveInput.");
//        }
    }

    public void checkDrop() {
//        if (inputStartsWith("drop")) {
//            println("You have nothing to drop");
//        }
    }

    /**
     *
     */
    @Override
    public void initializeCommands() {
        // TODO
    }

    /**
     * Input has already been validated. Do stuff before processing valid
     * input.
     */
    @Override
    protected void preProcessInput() {

    }

    /**
     * Retrieves information about the playerCommand after it has been process.
     * This may influence how future commands are processed. This is only ran if
     * isValidInput() is successful.
     */
    @Override
    protected void postProcessInput() {

    }

    /**
     * @return Singleton instance of TestMenu
     */
    public static TestMenu getInstance() {
        if (instance == null) {
            instance = new TestMenu();
        }
        return instance;
    }
}
