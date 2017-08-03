package game.system;

import java.util.HashMap;
import item.*;

/**
 * Inventory can store items
 */
public class Inventory<T extends Item> {
	HashMap<Item,Integer> items;

	public Inventory() {
		items = new HashMap<T,Integer>();
	}
	public Inventory(HashMap<T,Integer> inItems) {
		items = inItems;
	}
	public void add(T item, int count) {
		if (items.contains(item)) {
			if (items.get(item) + count == 0) {
				items.remove(item);
			} else {
				set(item,items.get(item) + count);
			}
		} else {
			set(item,count);
		}
	}
	public boolean contains(T item) {
		return(items.containKey(item));
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
	public int getWeight() {
		int weight = 0;
		for (T item : items.keySet()) {
			weight += item.getWeight();
		}
		return(weight);
	}

	public T remove(T item) {
		if (containsKey(item)) {

		}
	}
	public T removeAll(T item) {

	}


	public void set(Item item, int count) {
		items.put(item,count);
	}
	public String toString() {
		return(items.toString());
	}
}
