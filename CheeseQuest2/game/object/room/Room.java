package game.object.room;

import game.object.item.background.BackgroundItem;
import game.object.item.background.person.Person;
import game.object.item.collectable.Collectable;
import game.system.Inventory;

public abstract class Room extends GameObject {
	private Coordinates coordinates = new Coordinates();
	private Inventory<Collectable> inv = new Inventory<Collectable>();
	private Inventory<BackgroundItem> background = new Inventory<BackgroundItem>;
	private Inventory<Person> people = new Inventory<Person>();

	private static Room room = new this();

	private Room() {
	}

	public static Coordinates getCoordinates() {
		return(roomCoordinates);
	}
	public static Room getInstance() {
		return room;
	}

	public static boolean isNorthOf(Room room) {
		return(room.getCoordinates().getNorthRoom().equals(this.room));
	}
	public static boolean isSouthOf(Room room) {
		return(room.getCoordinates().getSouthRoom().equals(this.room));
	}
	public static boolean isEastOf(Room room) {
		return(room.getCoordinates().getEastRoom().equals(this.room));
	}
	public static boolean isWestOf(Room room) {
		return(room.getCoordinates().getWestRoom().equals(this.room));
	}
	public static boolean isAbove(Room room) {
		return(room.getCoordinates().getUpRoom().equals(this.room));
	}
	public static boolean isBelow(Room room) {
		return(room.getCoordinates().getDownRoom().equals(this.room));
	}

	public static boolean hasNorthRoom() {
		return this.coordinates.getNorthRoom() != null;
	}
	public static boolean hasEastRoom() {
		return this.coordinates.getEastRoom() != null;
	}
	public static boolean hasWestRoom() {
		return this.coordinates.getWestRoom() != null;
	}
	public static boolean hasSouthRoom() {
		return this.coordinates.getSouthRoom() != null;
	}
}
