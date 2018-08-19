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

    /**
     * Create all valid stringCommands for this menu. Use addStringCommand().
     */
    @Override
    protected void initializeCommands() {
        // TODO
    }

    public static SaveMenu getInstance() {
        if (instance == null) {
            instance = new SaveMenu();
        }
        return instance;
    }

    public void printMainPrompt() {

    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some
     * corresponding output to this menu's currently set {@link IPrintBuffer}.
     */
    public void processInput() {

    }
}
