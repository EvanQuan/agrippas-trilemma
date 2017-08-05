package game.menu;

public class MainMenu extends Menu {
	private static MainMenu menu = new Menu();
	/**
	 * ASCII text: http://patorjk.com/software/taag/
	 * Title
	 * 	Font: Doom
	 * 	Character Width: Default
	 * 	Character Height: Default
	 * Subtitle
	 * 	Font: Small
	 * 	Character Width: Default
	 * 	Character Height: Default
	 * Scroll border: http://www.chris.com/ascii/index.php?art=art%20and%20design/borders
	 */
	private String titleScreen =
	"                                                                                       .---.\n" +
	"                                                                                      /  .  \\ \n" +
	"                                                                                     |\\_/|   |\n" +
	"                                                                                     |   |  /|\n" +
	"  .----------------------------------------------------------------------------------------' |\n" +
	" /  .-.     _____  _   _  _____ _____ _____ _____   _____ _   _ _____ _____ _____    _____   |\n" +
	"|  /   \\   /  __ \\| | | ||  ___|  ___/  ___|  ___| |  _  | | | |  ___/  ___|_   _|  / __  \\  |\n" +
	"| |\\_.  |  | /  \\/| |_| || |__ | |__ \\ `--.| |__   | | | | | | | |__ \\ `--.  | |    `' / /'  |\n" +
	"|\\|  | /|  | |    |  _  ||  __||  __| `--. \\  __|  | | | | | | |  __| `--. \\ | |      / /    |\n" +
	"| `---' |  | \\__/\\| | | || |___| |___/\\__/ / |___  \\ \\/' / |_| | |___/\\__/ / | |    ./ /___  |\n" +
	"|       |   \\____/\\_| |_/\\____/\\____/\\____/\\____/   \\_/\\_\\\\___/\\____/\\____/  \\_/    \\_____/  |\n" +
	"|       |            _____  _           ___  _                                __             |\n" +
	"|       |           |_   _|| |_   ___  | _ \\| | __ _  __ _  _  _  ___   ___  / _|            |\n" +
	"|       |             | |  | ' \\ / -_) |  _/| |/ _` |/ _` || || |/ -_) / _ \\|  _|            |\n" +
	"|       |             |_|  |_||_|\\___| |_|  |_|\\__,_|\\__, | \\_,_|\\___| \\___/|_|              |\n" +
	"|       |                    _  __           _     _ |___/               _                   |\n" +
	"|       |                   | |/ / __ _  ___| |_  | |__ __ _ __ __ __ _ | |                  |\n" +
	"|       |                   | ' < / _` |(_-<| ' \\ | / // _` |\\ V // _` || |                  |\n" +
	"|       |                   |_|\\_\\\\__,_|/__/|_||_||_\\_\\\\__,_| \\_/ \\__,_||_|                  |\n" +
	"|       |                                                                                   /\n" +
	"|       |----------------------------------------------------------------------------------'\n" +
	"\\       |\n" +
	" \\     /                                 A Text Adventure Game\n" +
	"  `---'\n" +
	"\n";
	private String options =
	"           1. Start Game        2. How to Play        3. About this Game        4. Quit\n";

	private MainMenu() {
	}

	public static MainMenu getInstance() {
	return menu;
	}

	/**
	 * Print prompt for user
	 */
	@Override
	public void printMainPrompt() {

	}

	private printTitleScreen() {

	}

	/**
	 * Process input text and determines what to do
	 * @return true if valid input, else false
	 */
	@Override
	public boolean processInput() {

	}

	/**
	 * Prints prompt for invalid input
	 */
	@Override
	public void printInvalidPrompt() {

	}
}
