package game.menu;

import java.util.*;
import game.system.*;
import game.object.item.background.character.*;
import game.object.item.collectible.Collectible;
import game.object.location.*;

/**
 * Manages all in game actions
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
        look = new ArrayList<String>(getStringArrayList(new String[] {"look","l"}));
    }

    @Override
    public void outputPrompt() {
        println("room: " + room);
        playerLook();
    }
    public static GameMenu getInstance() {
        if (instance == null) {
            instance  = new GameMenu();
        }
        return instance;
    }
    /**
     * Allows children to extend basic processInput commands
     */
    public void enableExtendInput() {
        extendInput = true;
    }

    public void setSave(Save save) {
        name = save.getName();
        room = save.getRoom();
        player = save.getPlayer();
        turnCount = save.getTurnCount();
    }

    @Override
    public void processInput() {
        if (inputStartsWithStrip(look)) {
            if (inputRemains()) {
                println("You can't look at that."); // NOTE: Change later debug
            } else {
                playerLook();
            }
        } else if (inputStartsWithStrip(new String[] {"quit","quit game"})) {
            if (inputRemains()) {
                println("You can't do that.");
            } else {
                quit(); // NOTE: Change to GameOverMenu
            }
        } else if (!extendInput) { // outputInvalid must be manually set for children extendInput
            outputInvalid();
        }
    }

    public void outputInvalid() {

    }
    /**
     * Returns current save
     * NOTE: a new save is created every time this is called
     * @return save
     */
    public Save getSave() {
        try {
            Save save = new Save(room,player,turnCount,name);
            return save;
        } catch (Exception e) {
            System.out.println("GameMenu.getSave(): Save has not been initialized and cannot be returned.");
            e.printStackTrace();
            return null;
        }
    }
    public void quit() {
        changeToAskSaveMenu();
    }





    /**
     * In game actions
     * - End game
     * - Increment turn counter
     * - Regulate player statics
     *  - Health
     *  - Mana
     *  - Corruption
     *  - Hunger
     *  - Living status
     * - Change current room
     * Player actions
     * - Take
     * - Drop
     * - Eat
     * - Drink
     * - Buy
     * - Sell
     * - Give
     * - Examine
     * - Mine
     * - Throw
     * Room actions
     * - firstTime toggling
     * - Look
     * - Transfer items
     * - Change direction block status
     * - Lock/unlock/open/close containers
     */

    /**
     * Look at room surroundings
     * People present
     * Items
     * Adjacent rooms
     */
    public void playerLook() {
        room.outputDescription();
        room.outputItems();
        room.outputAdjacentRooms();
        // outputItems();

    }
    // public void output()

    /**
     * Output information about all items present in room
     */
    // public void outputItems() {
    //     Inventory<Collectable> inv = room.getInventory();
    //     if (!inv.isEmpty()) {
    //         output("There is ");
    //         for (Collectable item : inv.getItemSet()) {
    //             int count = inv.getCount(item);
    //             if (count == 1) {
    //
    //             }
    //             if (startsWithVowel(item.getSingleName())) {
    //
    //             }
    //         }
    //     }
    // }

    // The Northmen say the world will come to an end. The sun will grow black. The earth will sink into the sea. The stars will disappear. Fire and water will meet. Flames will play against the sky. The heavens and earth and all the world will be burned. All the gods will be dead, and the warriors of Valhalla, and people everywhere. Senua, prepare yourself for Ragnarok, for it is nigh.
    // The Northmen say the world will come to an end. The sun will grow black. The earth will sink into the sea. The stars will disappear. Fire and water will meet. Flames will play against the sky. The heavens and earth and all the world will be burned. All the gods will be dead, the warriors of Valhalla, and people everywhere. Prepare yourself for Ragnarok, for it is nigh.
}
