public class MainMenu extends Menu {
	public static String[] START_GAME = {"1","1.","start","start game","1. start game"};
	public static String[] HOW_TO_PLAY = {"2","2.","how","how to","how to play","how play"};
	public static String[] ABOUT_THIS_GAME = {"3","3.","about","about game","about this","about this game"};
	private GameMenu aGameMenu = new GameMenu();
	public void printMenuStart() {
		System.out.println("Cheese Quest");
		System.out.println("1. Start Game\t2. How to Play\t3. About this Game\t4. Quit");
	}
	public void processChoice() {
		if (choiceEquals(START_GAME)) {
			aGameMenu.startMenu();
		} else if (choiceEquals(HOW_TO_PLAY)) {
			startHowToPlay();
		} else if (choiceEquals(ABOUT_THIS_GAME)) {
			startAboutThisGame();
		} else if (choiceEquals(QUIT)) {
			quit();
		} else {

		}
	}
	public void startAfterMenu() {
		System.out.println("Bye!");
		Thread.sleep(1000);
	}
	public void startHowToPlay() {

	}
	public void startAboutThisGame() {

	}
	public void quit() {

	}
}
