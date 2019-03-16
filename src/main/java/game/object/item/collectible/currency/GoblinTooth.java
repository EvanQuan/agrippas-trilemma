package game.object.item.collectible.currency;

public class GoblinTooth extends Currency {

    private static final long serialVersionUID = 1L;

    public GoblinTooth() {
        setSingleNames(new String[]{"goblin tooth", "tooth"});
        setPluralNames(new String[]{"goblin teeth", "teeth"});
        setDescriptions(new String[]{"Small and pointy."});
        setWeight(0.01);
    }
}
