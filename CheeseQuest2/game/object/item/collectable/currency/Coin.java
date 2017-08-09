package game.object.item.collectable.currency;

public class Coin extends Currency {

    private static Coin coin = new Coin();

    private Coin() {
        addSingleName(new String[] {"coin"});
        addPluralName(new String[] {"coins"});
        addDescription(new String[] {"The edges are worn down from handling."});
        setWeight(0.01);
    }

    public static Coin getInstance() {
        return coin;
    }
}
