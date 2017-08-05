package game.system;

import java.util.HashMap;
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
 * Inventory can store
 * Item
 * Room
 */
public class Inventory<T extends GameObject> {
	HashMap<T,Integer> items;

	public Inventory() {
		items = new HashMap<T,Integer>();
	}
	public Inventory(HashMap<T,Integer> items) {
		this.items = items;
	}
	public void add(T item, int count) {
		if (contains(item)) {
			if (items.get(item) + count == 0) {
				items.remove(item);
			} else {
				set(item,items.get(item) + count);
			}
		} else {
			set(item,count);
		}
	}
	public void add(T item) {
		add(item,1);
	}
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
	// 	double weight = 0;
	// 	if items.
	// 	for (T item : items.keySet()) {
	// 		if (item instanceof Collectable) {
	// 			weight += item.getWeight();
	// 		}
	// 	}
	// 	return(weight);
	// }

	public Integer removeAll(T item) {
		return items.remove(item);
	}


	public void set(T item, int count) {
		items.put(item,count);
	}
	public String toString() {
		return(items.toString());
	}
}
