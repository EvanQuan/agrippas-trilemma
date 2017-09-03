package game.object.room;

public class TestRoom extends Room {

    public static final long serialVersionUID = 1L;

    public TestRoom() {
        addSingleName(new String[] {"test room"});
    }

    @Override
    public void outputFirstDecription() {
        output("This is the first time you're in the ");
        outputlnRoom("test room.");
    }
    @Override
    public void outputRepeatDescription() {
        outputln("You have already been in this room.");
    }
}
