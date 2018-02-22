package game.object.room;

import game.object.item.collectible.*;
import game.object.item.collectible.currency.*;


public class TestRoom extends Room {

    public static final long serialVersionUID = 1L;

    public TestRoom() {
        setSingleNames(new String[] {"test room","room of testing"});
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
