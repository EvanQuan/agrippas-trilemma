package game.object.room;

public class TestRoom extends Room {

    public static final long serialVersionUID = 1L;

    public TestRoom() {
        addSingleName(new String[] {"test room"});
        addDescription(new String[] {"It's a white box with no way out."});
    }
}
