package game.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Container can store (contain) game objects T: Key is the GameObject that is
 * stored Integer: Value is the quantity of GameObject stored
 *
 * @author Evan Quan
 */
public class ObjectContainer<T extends GameObject> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * T: Key is the GameObject that is stored Integer: Value is the quantity of
     * GameObject stored
     */
    HashMap<T, Integer> items;

    /**
     * Instantiates new ObjectContainer
     */
    public ObjectContainer() {
        items = new HashMap<T, Integer>();
    }

    /**
     * Instantiates new ObjectContainer
     *
     * @param items to add to ObjectContainer
     */
    public ObjectContainer(HashMap<T, Integer> items) {
        this.items = items;
    }

    /**
     * Add count of item inside inventory If count results in negative item
     * count, item count is set to 0
     *
     * @param count of item to add
     * @param item  to add
     */
    public void add(int count, T item) {
        if (contains(item)) {
            set(items.get(item) + count, item);
        } else {
            set(count, item);
        }
    }

    /**
     * Add a single item
     *
     * @param item to add
     */
    public void add(T item) {
        add(1, item);
    }

    /**
     * Checks if item is contained in inventory
     *
     * @param item to check
     * @return true if item is contained in inventory
     */
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    /**
     * Get count of item in container
     *
     * @param item to get count of
     * @return Integer count of item 0 if item is not contained
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
     * @return total number of objects contained
     */
    public int getObjectCount() {
        int count = 0;
        for (T item : items.keySet()) {
            count += items.get(item);
        }
        return count;
    }

    /**
     * Used for iterating through inventory
     *
     * @return Set view of the items contained in items HashMap
     */
    public Set<T> getObjectSet() {
        return items.keySet();
    }

    /**
     * @return total number of object types contained
     */
    public int getObjectTypeCount() {
        return items.size();
    }

    /**
     * @return true if items is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Remove count of item inside inventory using add() method
     *
     * @param count of item to remove
     * @param item  to remove
     */
    public void remove(int count, T item) {
        add(-count, item);
    }

    /**
     * Remove a single item using add() method
     *
     * @param item to remove
     */
    public void remove(T item) {
        add(-1, item);
    }

    public Integer removeAll(T item) {
        return items.remove(item);
    }

    /**
     * Sets item to item count if count <= 0 the item is removed
     *
     * @param count of item to set
     * @param item  in items
     */
    public void set(int count, T item) {
        if (count <= 0) {
            if (contains(item)) {
                items.remove(item);
            }
        } else {
            items.put(item, count);
        }
    }

    @Override
    public String toString() {
        return (items.toString());
    }
}
