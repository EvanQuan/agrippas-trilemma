package game.menu;

/**
 * Gets input String from InputPanel, prrocesses it, outputs it to game menu
 * Acts as a shell for menu types
 */
public class MenuManager {
    public static final int VERB = 0;
    private String input;
    private String output;
    private Menu menu;
    private Menu lastMenu;

    private static MenuManager menuManager = new MenuManager();
    /**
     * Default TextManager constructor
     */
    private MenuManager() {
        this.menu = MainMenu.getInstance();
        this.lastMenu = menu;
    }

    public static MenuManager getInstance() {
        return menuManager;
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
     * Returns value of lastMenu
     * @return
     */
    public Menu getLastMenu() {
        return this.lastMenu;
    }

    /**
     * Sets new value of menu
     * @param
     */
    public void setMenu(Menu menu) {
        setLastMenu(this.menu);
    	this.menu = menu;
    }

    /**
     * Sets new value of lastMenu
     * @param
     */
    public void setLastMenu(Menu lastMenu) {
        this.lastMenu = lastMenu;
    }
}
