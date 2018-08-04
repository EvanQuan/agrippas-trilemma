package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;

public class RestartMenu extends Menu {

    private static RestartMenu restartMenu = new RestartMenu();

    private RestartMenu() {

    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some corresponding output to this menu's currently set
     * {@link IPrintBuffer}.
     *
     * @param playerCommand to processInput
     */
    @Override
    public void processInput(PlayerCommand playerCommand) {
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
     * Appends prompt to output {@link IPrintBuffer}. This should be called every time a {@link Menu}is changed in
     * {@link MenuManager} to signify to the user that the menu has changed, and what input is appropriate for the given
     * menu.
     */
    @Override
    public void printMainPrompt() {

    }

    public static RestartMenu getInstance() {
        return restartMenu;
    }
}
