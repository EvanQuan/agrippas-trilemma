package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;

/**
 * Prompts to save game save Uses WriteObject to save game Can override
 * preexisting saves
 */
public class SaveMenu extends Menu {

    private static SaveMenu instance;

    private SaveMenu() {

    }

    public static SaveMenu getInstance() {
        if (instance == null) {
            instance = new SaveMenu();
        }
        return instance;
    }

    /**
     * Create all valid stringCommands for this menu. Use addStringCommand().
     */
    @Override
    protected void initializeCommands() {
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

    public void printMainPrompt() {

    }

    /**
     * Prints a message signifying that the user can inputted invalid input.
     */
    @Override
    protected void printInvalidInput() {

    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some
     * corresponding output to this menu's currently set {@link IPrintBuffer}.
     */
    public void processInput() {

    }
}
