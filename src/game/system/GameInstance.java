package game.system;

/**
 * Overlooks the whole game. Initializes and contains all game logic components. Is
 * self-contained, meaning all components exist only for their specified instance (non-static), potentially allowing
 * for multiple game-instances to run simultaneously.
 * <br><br>
 * NOTE: This may be done later, if it turns out there's a reasonable way to do this. As in stands, due to how
 * differently the GUI and console versions work (specifically how input is handled), this is being held off/not
 * completed.
 *
 * @author Evan Quan
 */
public class GameInstance {

    /**
     * Default constructor. Once this is instantiated, the whole game is all set up and nothing
     * further needs to be done.
     */
    public GameInstance() {

    }

    /**
     * Start the game.
     */
    public void start() {

    }
}