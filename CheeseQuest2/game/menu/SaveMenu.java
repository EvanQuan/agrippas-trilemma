package game.menu;

public class SaveMenu extends Menu {

    private static SaveMenu saveMenu = new SaveMenu();

    private SaveMenu() {

    }

    public static SaveMenu getInstance() {
        return saveMenu;
    }
}
