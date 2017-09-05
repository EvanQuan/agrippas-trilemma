package game.menu;

import game.system.*;
import game.object.room.*;
import game.object.item.collectable.Collectable;
import game.object.item.background.person.*;

/**
 * Manages all in game actions
 */
public class GameMenu extends Menu {

    private static GameMenu instance;
    private Room room;
    private Player player;
    private int turnCount;

    private GameMenu() {
    }

    @Override
    public void outputPrompt() {
        playerLook();
    }
    public static GameMenu getInstance() {
        if (instance == null) {
            instance  = new GameMenu();
        }
        return instance;
    }
    public void setWorld(World world) {
        room = world.getRoom();
        player = world.getPlayer();
        turnCount = world.getTurnCount();
    }
    /**
     * Returns current world
     * NOTE: a new world is created every time this is called
     * @return world
     */
    public World getWorld() {
        try {
            World world = new World(room,player,turnCount);
            return world;
        } catch (Exception e) {
            System.out.println("GameMenu.getWorld(): World has not been initialized and cannot be returned.");
            e.printStackTrace();
            return null;
        }
    }
    public void quit() {
        changeToLoadMenu();
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
