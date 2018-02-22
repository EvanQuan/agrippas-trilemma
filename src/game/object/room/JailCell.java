package game.object.room;

public class JailCell extends Room {
    private static final long serialVersionUID = 1L;
    public JailCell() {
    setSingleNames(new String[] {"jail cell","cell"});
    setDescriptions(new String[] {"It's cold and dusty."});

    // getCoordinates().setEastRoom(Hallway.getInstance());
    }
}
