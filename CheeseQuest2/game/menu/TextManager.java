package game.menu;

/**
 * Gets input String from InputPanel, prrocesses it, outputs it to game menu
 * Gets ouput text from game menu, and outputs it to OutputPanel
 * Acts as a shell for menu types
 */
public class TextManager {
    public static final int VERB = 0;
    private String input;
    private String output;
    private Menu currentMenu;

    /**
     * Default TextManager constructor
     */
    public TextManager() {
        this.currentMenu = MainMenu.getInstance();
        getOutputFromMenu();
    }

    /**
     * Retreieves and processes input string
     */
    public void input(String input) {
        setInput(input);
        outputToMenu();
        getOutputFromMenu();
    }

    /**
     * Sets new value of input
     * @param String input
     */
    public void setInput(String input) {
        this.input  = input;
    }

    /**
     * Sends input to currentMenu
     */
    public void outputToMenu() {
        currentMenu.input(input);
    }

    public void getOutputFromMenu() {
        checkMenuChange();
        this.output = currentMenu.getOutput();
    }

    /**
     * Check if menu needs to change, and changes it
     */
    public void checkMenuChange() {
        if (!currentMenu.isInMenu()) {
            currentMenu = currentMenu.getNextMenu();
        }
    }

    /**
     * Returns value of outputString
     */
    public String output() {
        return this.output;
    }


	/**
	* Returns value of currentMenu
	* @return
	*/
	public Menu getCurrentMenu() {
		return this.currentMenu;
	}


	/**
	* Sets new value of currentMenu
	* @param
	*/
	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}
}
