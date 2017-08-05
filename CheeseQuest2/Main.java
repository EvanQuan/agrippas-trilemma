/**
 * Start game here
 */

import menu.*;

public class Main {
	public static Menu menu;
	public static void main(String[] args) {
		menu = new MainMenu();
		while (menu != null) {
			menu.start();
			menu = menu.getNextMenu();
		}
  }
}
