package game.object.item.background.person;

import game.object.item.background.BackgroundItem;
import game.system.Inventory;

/**
 * Can be contained in rooms
 * May have inventories
 * Is either alive or dead
 */
public abstract class Person extends BackgroundItem {
	private Inventory<Collectable> inv;
	private String name;
	private boolean isAlive;
}
