package game.object.item.collectible.consumable.food;

public class FunnelCake extends Food {

    private static final long serialVersionUID = 1L;

    private static FunnelCake instance;

    public FunnelCake() {
        setSingleNames(new String[] {"funnel cake","cake","funnel"});
        setPluralNamesDefault();
        setDescriptions(new String[] {"Moist and delicious."});
        setValue(10);
        setWeight(0.2);
        setFullness(20);
    }

    public static FunnelCake getInstance() {
        if (instance == null) {
            instance = new FunnelCake();
        }
        return instance;
    }
}
