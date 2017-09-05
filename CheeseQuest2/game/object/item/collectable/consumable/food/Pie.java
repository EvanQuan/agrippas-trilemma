package game.object.item.collectable.consumable.food;

public class Pie extends Food {

    private static final long serialVersionUID = 1L;

    private static Pie instance;

    public Pie() {
        setSingleNames(new String[] {"chicken pot pie", "chicken pie", "pie"});
        setPluralNamesDefault();
        setDescriptions(new String[] {"Hardy and delicious. Just looking at it makes you hungry."});
        setValue(20);
        setWeight(2);
        setFullness(50);
    }

    public static Pie getInstance() {
        if (instance == null) {
            instance = new Pie();
        }
        return instance;
    }
}
