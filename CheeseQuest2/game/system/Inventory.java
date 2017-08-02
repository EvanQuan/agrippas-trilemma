package game.system;

import java.util.HashMap;
import item.*;

/**
 * Inventory can store items
 */
public class Inventory {
	HashMap<Item,Integer> items;

	public Inventory() {
		items = new HashMap<Item,Integer>();
	}
	public Inventory(HashMap<Item,Integer> inItems) {
		items = inItems;
	}
	public void add(Item item, int count) {
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
	public boolean contains(Item item) {
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
		for (Item item : items.keySet()) {
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
		for (Item item : items.keySet()) {
			weight += item.getWeight();
		}
		return(weight);
	}

	public Item remove(Item item) {
		if (containsKey(item)) {

		}
	}
	public Item removeAll(Item item) {

	}


	public void set(Item item, int count) {
		items.put(item,count);
	}
	public String toString() {
		return(items.toString());
	}
}
