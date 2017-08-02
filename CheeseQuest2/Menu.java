import java.util.Scanner;

public class Menu {
	public static final String INPUT_PROMPT = "\n\n\n\n> ";
	private Scanner in;
	protected String choice;
	protected boolean inMenu = true;
	public Menu() {
		in = new Scanner(System.in);
	}
	public void askChoice() {
		System.out.print(INPUT_PROMPT);
		choice = in.nextLine();
	}
	public void startMenu() {
		printMenuStart();
		while (inMenu) {
			askChoice();
			processChoice();
		}
		startAfterMenu();
	}
	public void processChoice() {
	}
	public void printMenuStart() {
		System.out.println("This is the start of the menu.");
	}
	public void startAfterMenu() {
	}

	public boolean choiceEquals(String[] options) {
		boolean isEqual = false;
		for (int i = 0; i < option.length; i++) {
			if (choice.equalsIgnoreCase(options[i])) {
				isEqual = true;
			}
		}
		return(isEqual);
	}
}
