package game.menu;

/**
 * Gets input {@link game.system.input.PlayerCommand} from InputPanel, prrocesses it, outputs it to game currentMenu
 * Acts as a shell for currentMenu types
 */
public abstract class MenuManager {
    /**
     * Current currentMenu. All {@link game.system.input.PlayerCommand}s interact with this currentMenu.
     */
    private static Menu currentMenu;

    /**
     * The last currentMenu the player was at. This is tracked in case the player wants to return to the previous currentMenu.
     */
    private static Menu previousMenu;

    /**
     * The last currentMenu the player was at from the current ghost currentMenu. This is tracked so the player can return to the
     * previous "real" currentMenu after leaving the current ghost currentMenu.
     */
    private static Menu ghostPreviousMenu;

    /**
     * Retreieves and processes input string
     */
    public static void input(String input) {
//        currentMenu.input(input);
    }

    /**
     *
     * @return the current currentMenu
     */
    public static Menu getCurrentMenu() {
    	return currentMenu;
    }

    /**
     *
     * @return the previous currentMenu
     */
    public static Menu getPreviousMenu() {
        return (currentMenu instanceof GhostMenu) ? ghostPreviousMenu : previousMenu;
    }

    /**
     * Returns value of ghostPreviousMenu
     * @return
     */
    @Deprecated
    public static Menu getGhostPreviousMenu() {
        return ghostPreviousMenu;
    }

    /**
     * Sets new value of currentMenu
     * @param menu
     */
    public static void setCurrentMenu(Menu menu) {
        if (currentMenu == null) {
            // This ensures that previous menu is never null.
            // At the start the current menu is also the previous menu.
            currentMenu = menu;
        }
        setPreviousMenu(currentMenu, menu);
    	currentMenu = menu;
        // outputln("After set: Current currentMenu is " + this.currentMenu.getClass().getSimpleName());
//        this.currentMenu.outputPrompt();
    }

    /**
     * Sets new value of previousMenu
     * @param previousMenu
     * @param newMenu
     */
    public static void setPreviousMenu(Menu previousMenu, Menu newMenu) {
        previousMenu = newMenu;
        // outputln("previousMenu is " + this.previousMenu.getClass().getSimpleName());
        // if (this.previousMenu == null) {
        //     System.out.println("previousMenu is null");
        // } else {
        //     System.out.println("previousMenu is " + this.previousMenu.getClass().getSimpleName());
        // }
    }

    /**
     *
     * @param previousMenu
     * @param newMenu
     */
    public static void setPreviousMenu(Menu previousMenu, GhostMenu newMenu) {
        ghostPreviousMenu = previousMenu;
    }

    /**
     * This represents the player returning to a real currentMenu from a ghost currentMenu. To prevent an infinite
     * loop, do nothing so that the ghostPrevious menu always returns to a real menu.
     * @param previousMenu
     * @param newMenu
     */
    public static void setPreviousMenu(GhostMenu previousMenu, Menu newMenu) {
    }

    /**
     * This represents the player moving between ghost menus. To prevent an infinite loop, do nothing so that the
     * ghostPrevious menu always returns to a real menu.
     * @param previousMenu
     * @param newMenu
     */
    public static void setPreviousMenu(GhostMenu previousMenu, GhostMenu newMenu) {
    }
}
