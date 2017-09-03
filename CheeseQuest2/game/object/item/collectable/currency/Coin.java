package game.object.item.collectable.currency;

public class Coin extends Currency {

    private static final long serialVersionUID = 1L;

    private static Coin instance;

    private Coin() {
        addSingleName(new String[] {"coin"});
        addPluralName(new String[] {"coins"});
        addDescription(new String[] {"The edges are worn down from handling."});
        setWeight(0.01);
    }

    public static Coin getInstance() {
        if (instance == null) {
            instance = new Coin();
        }
        return instance;
    }
}
