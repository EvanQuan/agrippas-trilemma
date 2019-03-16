package game.menu;

import game.object.item.background.character.Player;
import game.object.location.Location;
import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;
import game.system.save.Save;
import util.CollectionUtils;

import java.util.ArrayList;

/**
 * Manages all in game playerActions
 */
public class GameMenu extends Menu {

    private static GameMenu instance;
    private boolean extendInput;
    // World info
    private String name;
    private Location room;
    private Player player;
    private int turnCount;
    private ArrayList<String> look;

    private GameMenu() {
        extendInput = false;
        look = new ArrayList<String>(CollectionUtils.getArrayList(new String[]{"look", "l"}));
    }

    public static GameMenu getInstance() {
        if (instance == null) {
            instance = new GameMenu();
        }
        return instance;
    }

    /**
     * Create all valid stringCommands for this menu. Use addStringCommand().
     */
    @Override
    protected void initializeCommands() {
        // TODO
    }

    /**
     * Input has already been validated. Do stuff before processing valid
     * input.
     */
    @Override
    protected void preProcessInput() {

    }

    /**
     * Retrieves information about the playerCommand after it has been process.
     * This may influence how future commands are processed. This is only ran if
     * isValidInput() is successful.
     */
    @Override
    protected void postProcessInput() {

    }

    /**
     * Allows children to extend basic processInput stringCommands
     */
    public void enableExtendInput() {
        extendInput = true;
    }

    /**
     * Returns current save NOTE: a new save is created every time this is
     * called
     *
     * @return save
     */
    public Save getSave() {
        try {
            Save save = new Save(room, player, turnCount, name);
            return save;
        } catch (Exception e) {
            System.out.println("GameMenu.getSave(): Save has not been initialized and cannot be returned.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * In game playerActions - End game - Increment turn counter - Regulate
     * player statics - Health - Mana - Corruption - Hunger - Living status -
     * Change current room Player playerActions - Take - Drop - Eat - Drink -
     * Buy - Sell - Give - Examine - Mine - Throw Room playerActions - firstTime
     * toggling - Look - Transfer items - Change direction block status -
     * Lock/unlock/open/close containers
     */

    public void setSave(Save save) {
        name = save.getName();
        room = save.getRoom();
        player = save.getPlayer();
        turnCount = save.getTurnCount();
    }

    public void outputInvalid() {

    }

    public void printMainPrompt() {
//        println("room: " + room);
        playerLook();
    }

    /**
     * Prints a message signifying that the user can inputted invalid input.
     */
    @Override
    protected void printInvalidInput() {

    }

    /**
     * Look at room surroundings People present Items Adjacent rooms
     */
    public void playerLook() {
    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some
     * corresponding output to this menu's currently set {@link IPrintBuffer}.
     */
    public void processInput() {
//        if (inputStartsWithStrip(look)) {
//            if (inputRemains()) {
//                println("You can't look at that."); // NOTE: Change later debug
//            } else {
//                playerLook();
//            }
//        } else if (inputStartsWithStrip(new String[] { "quit", "quit game" })) {
//            if (inputRemains()) {
//                println("You can't do that.");
//            } else {
//                quit(); // NOTE: Change to GameOverMenu
//            }
//        } else if (!extendInput) { // printInvalidInput must be manually set for children extendInput
//            printInvalidInput();
//        }
    }

    public void quit() {
        changeTo(AskToSaveMenu.getInstance());
    }

    /**
     * Output information about all items present in room
     */
    // public void outputItems() {
    // Inventory<Collectable> inv = room.getInventory();
    // if (!inv.isEmpty()) {
    // output("There is ");
    // for (Collectable item : inv.getItemSet()) {
    // int count = inv.getCount(item);
    // if (count == 1) {
    //
    // }
    // if (startsWithVowel(item.getSingleName())) {
    //
    // }
    // }
    // }
    // }

    // The Northmen say the world will come to an end. The sun will grow black. The
    // earth will sink into the sea. The stars will disappear. Fire and water will
    // meet. Flames will play against the sky. The heavens and earth and all the
    // world will be burned. All the gods will be dead, and the warriors of
    // Valhalla, and people everywhere. Senua, prepare yourself for Ragnarok, for it
    // is nigh.
    // The Northmen say the world will come to an end. The sun will grow black. The
    // earth will sink into the sea. The stars will disappear. Fire and water will
    // meet. Flames will play against the sky. The heavens and earth and all the
    // world will be burned. All the gods will be dead, the warriors of Valhalla,
    // and people everywhere. Prepare yourself for Ragnarok, for it is nigh.
}
