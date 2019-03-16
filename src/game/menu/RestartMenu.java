package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;

public class RestartMenu extends Menu {

    private static RestartMenu restartMenu = new RestartMenu();

    private RestartMenu() {

    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some
     * corresponding output to this menu's currently set {@link IPrintBuffer}.
     */
    @Override
    public void processInput() {
        // TODO
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

    /**
     * Appends prompt to output {@link IPrintBuffer}. This should be called
     * every time a {@link Menu}is changed in {@link MenuManager} to signify to
     * the user that the menu has changed, and what input is appropriate for the
     * given menu.
     */
    @Override
    public void printMainPrompt() {

    }

    /**
     * Prints a message signifying that the user can inputted invalid input.
     */
    @Override
    protected void printInvalidInput() {

    }

    public static RestartMenu getInstance() {
        return restartMenu;
    }
}
