package game.object;

import java.io.Serializable;
import java.util.*;
import game.object.*;
import game.object.item.background.*;
import game.object.item.background.person.*;
import game.object.item.collectable.*;
import game.object.item.collectable.consumable.drink.*;
import game.object.item.collectable.consumable.food.*;
import game.object.item.collectable.consumable.healer.*;
import game.object.item.collectable.container.*;
import game.object.item.collectable.currency.*;
import game.object.item.collectable.equipable.chest.*;
import game.object.item.collectable.equipable.feet.*;
import game.object.item.collectable.equipable.head.*;
import game.object.item.collectable.equipable.mainhand.*;
import game.object.item.collectable.equipable.offhand.*;

/**
 * Inventory can store game objects
 */
public class Inventory<T extends GameObject> implements Serializable {

    private static final long serialVersionUID = 1L;

    HashMap<T,Integer> items;

    public Inventory() {
        items = new HashMap<T,Integer>();
    }
    public Inventory(HashMap<T,Integer> items) {
        this.items = items;
    }
    /**
     * Add count of item inside inventory
     * If count results in negative item count, item count is set to 0
     * @param int count of item to add
     * @param T   item  to add
     */
    public void add(int count, T item) {
        if (contains(item)) {
            set(items.get(item) + count,item);
        } else {
            set(count,item);
        }
    }
    /**
     * Add a single item
     * @param T item to add
     */
    public void add(T item) {
        add(1,item);
    }

    /**
     * Remove count of item inside inventory using add() method
     * @param int count of item to remove
     * @param T   item  to remove
     */
    public void remove(int count, T item) {
        add(-count, item);
    }
    /**
     * Remove a single item using add() method
     * @param T item to remove
     */
    public void remove(T item) {
        add(-1,item);
    }

    /**
     * Checks if item is contained in inventory
     * @param  T item          to check
     * @return   true if item is contained in inventory
     */
    public boolean contains(T item) {
        return(items.containsKey(item));
    }
    /**
     * The total number of item types
     * @return item type count
     */
    public int getItemTypes() {
        return(items.size());
    }
    /**
     * The total number of all items
     * @return item count
     */
    public int getItemCount() {
        int count = 0;
        for (T item : items.keySet()) {
            count += items.get(item);
        }
        return(count);
    }
    /**
     * The total weight of all items
     * @return item weight
     */
    // public double getWeight() {
    //     double weight = 0;
    //     // if items. ??? Only collectable inventories have weight
    //     for (T item : items.keySet()) {
    //         if (item instanceof Collectable) {
    //             weight += item.getWeight();
    //         }
    //     }
    //     return(weight);
    // }

    public Integer removeAll(T item) {
        return items.remove(item);
    }

    /**
     * Sets item to item count
     * if count <= 0 the item is removed
     * @param int count of item to set
     * @param T   item  in items
     */
    public void set(int count, T item) {
        if (count <= 0) {
            if (contains(item)) {
                items.remove(item);
            }
        } else {
            items.put(item,count);
        }
    }
    /**
     *
     * @return true if items is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Get count of item in inventory
     * @param  T item          [description]
     * @return   [description]
     */
    public Integer getCount(T item) {
        return items.get(item);
    }
    /**
     * Used for iterating through inventory
     * @return Set view of the items contained in items HashMap
     */
    public Set<T> getItemSet() {
        return items.keySet();
    }

    public String toString() {
        return(items.toString());
    }
}
