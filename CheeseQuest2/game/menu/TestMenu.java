package game.menu;

public class TestMenu extends Menu {
    private static TestMenu menu = new TestMenu();

    private TestMenu() {

    }


    public static TestMenu getInstance() {
        return menu;
    }
}
