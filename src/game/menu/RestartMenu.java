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
     * Create all valid commands for this menu. Use addCommand().
     */
    @Override
    protected void initializeCommands() {
        // TODO
    }

    public static RestartMenu getInstance() {
        return restartMenu;
    }
}
