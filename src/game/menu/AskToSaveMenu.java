package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;

/**
 * Prompts user if they want to save
 */
public class AskToSaveMenu extends Menu {

    private static AskToSaveMenu instance;

    private AskToSaveMenu() {
    }

    public static AskToSaveMenu getInstance() {
        if (instance == null) {
            instance = new AskToSaveMenu();
        }
        return instance;
    }

    public void printMainPrompt() {
//        println("Would you like to save the game first?");
//        print("(");
//        printPlayer("Yes");
//        print("/");
//        printPlayer("No");
//        print("/");
//        printPlayer("Return");
//        println(")");
    }


    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some
     * corresponding output to this menu's currently set {@link IPrintBuffer}.
     * This is occurs after isValidInput() is called and succeeds.
     */
    @Override
    public void processInput() {
        // TODO
//        if (inputEquals(yes)) {
//            changeToSaveMenu();
//        } else if (inputEquals(no)) {
//            changeToMainMenu();
//        } else if (inputEquals(returnToPreviousMenu)) {
//            changeToPreviousMenu();
//        } else {
//            printMainPrompt();
//        }
    }

    @Override
    public void initializeCommands() {
        // TODO
    }
}
