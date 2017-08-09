package game.object.item.collectable.consumable.food;

public class Pie extends Food {
    public Pie() {
        addSingleName(new String[] {"chicken pot pie", "chicken pie", "pie"});
        addPluralName(new String[] {"chicken pot pies", "chicken pies", "pies"});
        addDescription(new String[] {"Hardy and delicious. Just looking at it makes you hungry."});
        setValue(20);
        setWeight(2);
        setHunger(50);
    }
}
