package game.menu;

public class RestartMenu extends Menu {

    private static RestartMenu restartMenu = new RestartMenu();

    private RestartMenu() {

    }

    public static RestartMenu getInstance() {
        return restartMenu;
    }
}
