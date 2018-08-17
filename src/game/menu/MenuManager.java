package game.menu;

import game.system.GameInstance;
import game.system.input.PlayerCommand;

import java.util.Stack;

/**
 * <p>
 * Receives {@link PlayerCommand}s as an input, and sends it to the current
 * {@link Menu} to process, where the Menu outputs the results to its respective
 * output. All player input should feed into this class not directly to any
 * individual menu instance since menus can change what the MenuManger's current
 * menu is based on player receiveInput. Also prompts the menus when to output,
 * such as when switching between menus, or when responding to player input.
 * </p>
 * <p>
 * This class is abstract and all it's methods/fields static because while this
 * stores menu singleton instances, the menus themselves are what are altering
 * the menu stack, and not some other external source. This prevents the menus
 * from needing to store a MenuManager singleton of their own, or having them
 * retrieve a MenuManager instance from some other source. The former doesn't
 * work because infinite mutual recursion, and the later is annoying because the
 * MenuManager could be acquired from either the {@link
 * game.system.gui.InputPanel}, or {@link GameInstance}, (or both), which work
 * in radically different ways.
 * </p>
 */
public class MenuManager {

    /**
     * Cannot instantiate
     */
    private MenuManager() {
    }

    /**
     * Current currentMenu. All {@link game.system.input.PlayerCommand}s
     * interact with this currentMenu.
     */
    private static Menu currentMenu;

    /**
     * The lack of current menu signifies that MenuManger has not been
     * initialized, or that the game is over and the program should quit.
     *
     * @return true if MenuManager has a current menu.
     */
    public static boolean hasCurrentMenu() {
        return currentMenu != null;
    }

    /**
     * The previous {@link Menu}s the player was at. This is tracked in case the
     * player wants to return to the previous menu. It is a stack, as returning
     * to a previous menu does not set the Menu being moved away from as the
     * previous menu, as that would cause a loop between 2 menus.
     */
    private static Stack<Menu> previousMenus = new Stack<>();

    /**
     * Receive playerCommand and passes it to the current menu to process.
     */
    public static void receiveInput(PlayerCommand playerCommand) {
        currentMenu.receiveInput(playerCommand);
    }

    /**
     * Get the current menu. This menu receives {@link PlayerCommand}s from
     * receiveInput().
     *
     * @return the current currentMenu
     */
    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    /**
     * Peek at the top of the previous menu stack, without altering it. This can
     * be useful if the current menu wants to figure out what the previous menu
     * is for displaying purposes.
     *
     * @return the previous currentMenu
     */
    public static Menu getPreviousMenu() {
        return previousMenus.peek();
    }

    /**
     * Set the specified menu as the current menu, and push the previous current
     * menu to the previous menu stack. After the menu has been set, the new
     * current menu outputs its main prompt.
     *
     * @param menu
     */
    public static void pushCurrentMenu(Menu menu) {
        if (currentMenu != null) {
            // This ensures that previous menu is never null when pushed to the
            // stack.
            // ex. At the start of the program, the main menu is pushed
            // (not set) as the current menu.
            previousMenus.push(currentMenu);
        }
        setCurrentMenu(menu);
    }

    /**
     * Set the current main as the top of ths previous menu stack. If the
     * previous menu stack is empty, do nothing. After the menu has been set,
     * the new current menu outputs its main prompt.
     */
    public static void popPreviousMenu() {
        if (previousMenus.peek() != null) {
            setCurrentMenu(previousMenus.pop());
        }
    }

    /**
     * <p>
     * Set the specified menu as the current menu. Does not affect the previous
     * menu stack. After the menu has been set, the new current menu outputs its
     * main prompt.
     * </p>
     * <p>
     * NOTE: Use this for in-game sub-menus so that they always return back to
     * {@link GameMenu}, and so that the underlying menu is still the {@link
     * MainMenu}.
     * </p>
     *
     * @param menu to set to the new current menu.
     */
    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
        if (currentMenu != null) {
            currentMenu.printMainPrompt();
        }
    }
}
