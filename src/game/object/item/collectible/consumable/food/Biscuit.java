package game.object.item.collectible.consumable.food;

import game.object.item.collectible.consumable.food.Food;

public class Biscuit extends Food {
    private static final long serialVersionUID = 1L;
    public Biscuit() {
        setSingleNames(new String[] {"hardtack biscuit", "biscuit"});
        setPluralNames(new String[] {"hardtack biscuits", "biscuits"});
        setDescriptions(new String[] {"harder than a brick and probably just as tasty."});
        setValue(5);
        setWeight(0.1);
        setFullness(30);
    }
}
