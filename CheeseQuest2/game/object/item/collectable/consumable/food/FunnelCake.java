package game.object.item.collectable.consumable.food;

public class FunnelCake extends Food {

    private static final long serialVersionUID = 1L;

    public FunnelCake() {
        addSingleName(new String[] {"funnel cake","cake","funnel"});
        addPluralName(new String[] {"funnel cakes","cakes","funnels"});
        addDescription(new String[] {"Moist and delicious."});
        setValue(10);
        setWeight(0.2);
        setHunger(20);
    }
}
