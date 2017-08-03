package game.object.room;

import game.object.item.background.BackgroundItem;
import game.object.item.background.person.Person;
import game.object.item.collectable.Collectable;
import game.system.Inventory;

public abstract class Room {
	private Coordinates coordinates;
	private Inventory<Collectable> inv;
	private Inventory<BackgroundItem> background;
	private Inventory<Person> people;

	public Room() {
		inv = new Inventory<Collectable>();
		background = new Inventory<BackgroundItem>();
		people = new Inventory<Person>();
	}
	public Coordinates getCoordinates() {
		return(roomCoordinates);
	}

	public boolean isNorthOf(Room room) {
		return(coordinates.getZ() > room.getCoordinates().getZ());
	}
	public boolean isSouthOf(Room room) {
		return(coordinates.getZ() < room.getCoordinates().getZ());
	}
	public boolean isEastOf(Room room) {
		return(coordinates.getX() > room.getCoordinates().getX());
	}
	public boolean isWestOf(Room room) {
		return(coordinates.getX() < room.getCoordinates().getX());
	}
	public boolean isTopOf(Room room) {
		return(coordinates.getY() > room.getCoordinates().getY());
	}
	public boolean equals(Room room) {
		return(
			coordinates.getX() == room.getCoordinates().getX() &&
			coordinates.getY() == room.getCoordinates().getY() &&
			coordinates.getZ() == room.getCoordinates().getZ()
		);
	}
}
