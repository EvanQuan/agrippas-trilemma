package game.menu;

import file.*;

/**
 * Mediates post-game decisions
 * Asks to save before returning to MainMenu
 * Asks player to load or restart after death/victory
 */
public class GameOverMenu extends Menu {

    public static final int LOSE = 0;
    public static final int WIN = 1;
    private static GameOverMenu instance;

    private String title;

    /**
     * Default GameOverMenu constructor
     */
    private GameOverMenu() {
        setMode(LOSE);
    }

    public static GameOverMenu getInstance() {
        if (instance == null) {
            instance = new GameOverMenu();
        }
        return instance;
    }

    @Override
    public void outputPrompt() {
        outputlnTitle(title);

        outputPlayer("Restart");
        output(" or ");
        outputPlayer("Load");
        output(" a game save, or ");
        outputPlayer("Return");
        output(" to the ");
        outputRoom(" Main Menu");
        outputln(".");
    }

    /**
     * Mode detemines outputPrompt text and decisions???
     * @param  int mode          of menu
     */
    public void setMode(int mode) {
        switch(mode) {
            case LOSE:
                title = "Oh no, you died!";
                break;
            case WIN:
                title = "Hooray, you won!";
                break;
        }
    }

}
