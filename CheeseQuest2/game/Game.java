package game;

import game.system.Player;
import game.system.World;

/**
 * Manages InputPanel input to determine player and world interactions
 */
public class Game {
    private Player player;
    private World world;


    /**
     * Input string determines game actions
     * @param String input from InputPanel
     */
    public void input(String input) {
        paritionInput();
        processInput();
        sendOutput();
    }

    private void paritionInput() {

    }

    private void processInput() {

    }

    private void sendOutput() {
        
    }
}
