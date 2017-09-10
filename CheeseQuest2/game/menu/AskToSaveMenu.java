package game.menu;

/**
 * Prompts user if they want to save
 */
public class AskToSaveMenu extends GhostMenu {

    private static AskToSaveMenu instance;

    private AskToSaveMenu() {

    }
    public static AskToSaveMenu getInstance() {
        if (instance == null) {
            instance = AskToSaveMenu();
        }
        return instance;
    }

    @Override
    public void outputPrompt() {
        outputln("Would you like to save the game first?");
        output("(");
        outpuPlayer("Yes");
        output("/")
        outputPlayer("No");
        output("/")
        outputPlyaer("Return");
        outputln(")");
    }
    @Override
    public void processInput() {
        if (inputEquals(yes)) {
            changeToSaveMenu();
        } else if (inputEquals(no)) {
            changeToMainMenu();
        } else if (inputEquals(returnToPreviousMenu)) {
            changeToPreviousMenu();
        } else {
            outputPrompt();
        }
    }
}
