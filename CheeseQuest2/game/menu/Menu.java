package game.menu;

import java.util.Scanner;
import java.util.HashMap;

/**
 * Player is continuously in a menu in game.
 * Menu type changes throughout game.
 *
 */
public abstract class Menu {
	public static final int PROMPT_SPACING = 4;
	public static final int VERB = 0;
	private Scanner input;
	private String inputLine;
	private String[] inputWords;
	private Menu nextMenu;
	private boolean inMenu;

	public Menu() {
		input = new Scanner(System.in);
		inMenu = true;
	}

	/**
	 * Start menu prompt, input and process choice
	 * Escapes prompt when input is validInput = true
	 * Escapes menu when inMenu = false
	 */
	public Menu start() {
		boolean validInput;
		while (inMenu) {
			printMainPrompt();
			do {
				getInput();
				validInput = processInput();
				if (!validInput) {
					printInvalidPrompt();
				}
			} while (!validInput);
		}
	}


	/**
	 * Print prompt for user input
	 */
	public void printMainPrompt() {
	}

	/**
	 * Get input from user and stores the line in inputLine
	 * and individual words in inputWords
	 */
	public void getInput() {
		System.out.print("\n" * PROMPT_SPACING  + "> ");
		inputLine = input.nextLine();
		inputWords = inputLine.split(" ");
	}
	/**
	 * Process input text and determines what to do
	 * @return true if valid input, else false
	 */
	public boolean processInput() {
	}

	/**
	 * Prints prompt for invalid input
	 */
	public void printInvalidPrompt() {

	}
	
	/**
	* Returns value of inputLine
	* @return
	*/
	public String getInputLine() {
		return inputLine;
	}

	/**
	* Returns value of g
	* @return
	*/
	public String getInputWords() {
		return inputWords;
	}

	/**
	* Returns value of nextMenu
	* @return
	*/
	public Menu getNextMenu() {
		return nextMenu;
	}

	/**
	* Returns value of inMenu
	* @return
	*/
	public boolean isInMenu() {
		return inMenu;
	}

	/**
	* Sets new value of nextMenu
	* @param
	*/
	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

	/**
	* Sets new value of inMenu
	* @param
	*/
	public void setInMenu(boolean inMenu) {
		this.inMenu = inMenu;
	}
}
