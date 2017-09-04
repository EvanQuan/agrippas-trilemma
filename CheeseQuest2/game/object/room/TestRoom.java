package game.object.room;

import game.object.item.collectable.*;
import game.object.item.collectable.currency.*;


public class TestRoom extends Room {

    public static final long serialVersionUID = 1L;

    public TestRoom() {
        addSingleName(new String[] {"test room","room of testing"});
        // getInventory().add(3,Coin.getInstance());
        // setSouthBlocked(true);
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
