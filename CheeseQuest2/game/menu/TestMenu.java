package game.menu;

/**
 * Test menu for menu manager to transfer between menuss
 */
public class TestMenu extends Menu {
    private static TestMenu instance;

    public static final String[] BACK = {"q"};
    private TestMenu() {

    }

    @Override
    public void outputPrompt() {
        outputln("You are now in the Test menu.");
        outputln("To go to the main menu, type \"q\"");
    }
    @Override
    public void processInput() {
        if (inputEquals(BACK)) {
            changeToMainMenu();
        } else {
            outputln("Invalid input. Type in \"q\"");
        }
    }


    public static TestMenu getInstance() {
        if (instance == null) {
            instance = new TestMenu();
        }
        return instance;
    }
}
