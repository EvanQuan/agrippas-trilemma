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
            instance = new AskToSaveMenu();
        }
        return instance;
    }

    public void outputPrompt() {
//        println("Would you like to save the game first?");
//        print("(");
//        printPlayer("Yes");
//        print("/");
//        printPlayer("No");
//        print("/");
//        printPlayer("Return");
//        println(")");
    }
    public void processInput() {
//        if (inputEquals(yes)) {
//            changeToSaveMenu();
//        } else if (inputEquals(no)) {
//            changeToMainMenu();
//        } else if (inputEquals(returnToPreviousMenu)) {
//            changeToPreviousMenu();
//        } else {
//            outputPrompt();
//        }
    }
}
