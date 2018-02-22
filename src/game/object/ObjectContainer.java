package game.object;

import java.io.Serializable;
import java.util.*;

/**
 * Container can store (contain) game objects
 */
public class ObjectContainer<T extends GameObject> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * T: Key is the GameObject that is stored
     * Integer: Value is the quantity of GameObject stored
     */
    HashMap<T,Integer> items;

    public ObjectContainer() {
        items = new HashMap<T,Integer>();
    }
    public ObjectContainer(HashMap<T,Integer> items) {
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
        return items.containsKey(item);
    }
    /**
     * @return total number of object types contained
     */
    public int getObjectTypeCount() {
        return items.size();
    }
    /**
     * @return total number of objects contained
     */
    public int getObjectCount() {
        int count = 0;
        for (T item : items.keySet()) {
            count += items.get(item);
        }
        return count;
    }

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
     * Get count of item in container
     * @param  T item to get count of
     * @return Integer count of item
     *                 0 if item is not contained
     */
    public Integer getCount(T item) {
        if (contains(item)) {
            return items.get(item);
        } else {
            // HashMap normally returns null if key not contained
            return 0;
        }
    }
    /**
     * Used for iterating through inventory
     * @return Set view of the items contained in items HashMap
     */
    public Set<T> getObjectSet() {
        return items.keySet();
    }

    public String toString() {
        return(items.toString());
    }
}
