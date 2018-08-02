package game.menu;

import game.system.input.PlayerCommand;

import java.util.Stack;

/**
 * Receives {@link game.system.input.PlayerCommand}s as an receiveInput, and sends it to the current {@link Menu} to process,
 * where the Menu outputs the results to its respective output. All player receiveInput should feed into this class not
 * directly to any individual menu instance since menus can change what the MenuManger's current menu is based on
 * player receiveInput.
 */
public abstract class MenuStack {
    /**
     * Current currentMenu. All {@link game.system.input.PlayerCommand}s interact with this currentMenu.
     */
    private static Menu currentMenu;

    /**
     * The previous {@link Menu}s the player was at. This is tracked in case the player wants to return to the
     * previous menu. It is a stack, as returning to a previous menu does not set the Menu being moved away from as
     * the previous menu, as that would cause a loop between 2 menus.
     */
    private static Stack<Menu> previousMenus = new Stack<>();

    /**
     * Receive playerCommand and passes it to the current menu to process.
     */
    public static void receiveInput(PlayerCommand playerCommand) {
        currentMenu.processInput(playerCommand);
    }

    /**
     * Get the current menu. This menu receives {@link PlayerCommand}s from receiveInput().
     *
     * @return the current currentMenu
     */
    public static Menu getCurrentMenu() {
    	return currentMenu;
    }

    /**
     * Peek at the top of the previous menu stack, without altering it. This can be useful if the current menu wants to
     * figure out what the previous menu is for displaying purposes.
     *
     * @return the previous currentMenu
     */
    public static Menu getPreviousMenu() {
        return previousMenus.peek();
    }

    /**
     * Set the specified menu as the current menu, and push the previous current menu to the previous menu stack.
     *
     * @param menu
     */
    public static void pushCurrentMenu(Menu menu) {
        if (currentMenu != null) {
            // This ensures that previous menu is never null when pushed to the stack.
            // ex. At the start of the program, the main menu is pushed (not set) as the current menu.
            previousMenus.push(currentMenu);
        }
    	currentMenu = menu;
    }

    /**
     * Set the current main as the top of ths previous menu stack. If the previous menu stack is empty, do nothing.
     */
    public static void popPreviousMenu() {
        if (previousMenus.peek() != null) {
            currentMenu = previousMenus.pop();
        }
    }

    /**
     * Set the specified menu as the current menu. Does not affect the previous menu stack.
     * <br><br>
     * NOTE: Use this for in-game sub-menus so that they always return back to {@link GameMenu}, and so that the
     * underlying menu is still the {@link MainMenu}.
     *
     * @param menu to set to the new current menu.
     */
    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
}
