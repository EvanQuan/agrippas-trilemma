package game.object.room;

import game.object.GameObject;
import game.object.item.background.BackgroundItem;
import game.object.item.background.person.Person;
import game.object.item.collectable.Collectable;
import game.system.Inventory;

public abstract class Room extends GameObject {
    private Coordinates coordinates;
    private Inventory<Collectable> inv;
    private Inventory<BackgroundItem> background;
    private Inventory<Person> people;

    public Room() {
        coordinates = new Coordinates();
        inv = new Inventory<Collectable>();
        background = new Inventory<BackgroundItem>();
        people = new Inventory<Person>();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Inventory<Collectable> getInventory() {
        return inv;
    }
    public void setInventory(Inventory<Collectable> inv) {
        this.inv = inv;
    }

    public Inventory<BackgroundItem> getBackground() {
        return background;
    }
    public void setBackground(Inventory<BackgroundItem> background) {
        this.background = background;
    }

    public Inventory<Person> getPeople() {
        return people;
    }
    public void setPeople(Inventory<Person> people) {
        this.people = people;
    }

    public boolean isNorthOf(Room room) {
        return(room.getCoordinates().getNorthRoom().equals(this));
    }
    public boolean isSouthOf(Room room) {
        return(room.getCoordinates().getSouthRoom().equals(this));
    }
    public boolean isEastOf(Room room) {
        return(room.getCoordinates().getEastRoom().equals(this));
    }
    public boolean isWestOf(Room room) {
        return(room.getCoordinates().getWestRoom().equals(this));
    }
    public boolean isAbove(Room room) {
        return(room.getCoordinates().getUpRoom().equals(this));
    }
    public boolean isBelow(Room room) {
        return(room.getCoordinates().getDownRoom().equals(this));
    }

    public boolean hasNorthRoom() {
        return this.coordinates.getNorthRoom() != null;
    }
    public boolean hasEastRoom() {
        return this.coordinates.getEastRoom() != null;
    }
    public boolean hasWestRoom() {
        return this.coordinates.getWestRoom() != null;
    }
    public boolean hasSouthRoom() {
        return this.coordinates.getSouthRoom() != null;
    }

    /**
     * // Debug
     * Maybe remove for coordinates only
     */
    public boolean isNorthBlocked() {
        return this.coordinates.isNorthBlocked();
    }
    public boolean isEastBlocked() {
        return this.coordinates.isEastBlocked();
    }
    public boolean isWestBlocked() {
        return this.coordinates.isWestBlocked();
    }
    public boolean isSouthBlocked() {
        return this.coordinates.isSouthBlocked();
    }
}
