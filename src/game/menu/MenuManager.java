package game.menu;

import game.system.*;
/**
 * Gets input String from InputPanel, prrocesses it, outputs it to game menu
 * Acts as a shell for menu types
 */
public class MenuManager extends GUIPrinter {
    public static final int VERB = 0;
    private String input;
    private String output;
    private Menu menu;
    private Menu previousMenu;
    private Menu ghostPreviousMenu;

    private static MenuManager instance;
    /**
     * Default TextManager constructor
     */
    private MenuManager() {
        // setMenu(MainMenu.getInstance()); // Default menu
        // setMenu(TestMenu.getInstance());
        // System.out.println("MenuManager():");
        // if (instance == null) {
        //     System.out.println("    menuManager is NULL");
        // } else {
        //     System.out.println("    menuManager is " + instance);
        // }
    }

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    /**
     * Retreieves and processes input string
     */
    public void input(String input) {
        menu.input(input);
    }

    /**
     * Returns value of menu
     * @return
     */
    public Menu getMenu() {
    	return this.menu;
    }

    /**
     * Returns value of previousMenu
     * @return
     */
    public Menu getPreviousMenu() {
        if (this.menu instanceof GhostMenu) {
            return this.ghostPreviousMenu;
        } else {
            return this.previousMenu;
        }
    }

    /**
     * Returns value of ghostPreviousMenu
     * @return
     */
    public Menu getGhostPreviousMenu() {
        return this.ghostPreviousMenu;
    }

    /**
     * Sets new value of menu
     * @param
     */
    public void setMenu(Menu menu) {
        if (this.menu == null) {
            this.menu = menu;
        }
        setPreviousMenu(this.menu,menu);
    	this.menu = menu;
        // outputln("After set: Current menu is " + this.menu.getClass().getSimpleName());
        this.menu.outputPrompt();
    }

    /**
     * Sets new value of previousMenu
     * @param
     */
    public void setPreviousMenu(Menu previousMenu, Menu newMenu) {
        // outputln("Before set: Current menu is " + newMenu.getClass().getSimpleName());
        if (newMenu instanceof GhostMenu) {
            this.ghostPreviousMenu = previousMenu;
            // outputln("which is a GhostMenu, so will return to " + ghostPreviousMenu.getClass().getSimpleName());
        } else if (!(previousMenu instanceof GhostMenu)) {
            this.previousMenu = previousMenu;
        }
        // outputln("previousMenu is " + this.previousMenu.getClass().getSimpleName());
        // if (this.previousMenu == null) {
        //     System.out.println("previousMenu is null");
        // } else {
        //     System.out.println("previousMenu is " + this.previousMenu.getClass().getSimpleName());
        // }
    }
}
