package game.object.room;

import game.object.GameObject;
import game.object.item.background.BackgroundItem;
import game.object.item.background.person.Person;
import game.object.item.collectable.Collectable;
import game.system.Inventory;

public abstract class Room extends GameObject {

    public static final long serialVersionUID = 1L;

    private Inventory<Collectable> inv;
    private Inventory<BackgroundItem> background;
    private Inventory<Person> people;

    private Room northRoom;
    private Room eastRoom;
    private Room westRoom;
    private Room southRoom;
    private Room upRoom;
    private Room downRoom;

    private boolean northBlocked;
    private boolean eastBlocked;
    private boolean westBlocked;
    private boolean southBlocked;
    private boolean upBlocked;
    private boolean downBlocked;

    public Room() {
        inv = new Inventory<Collectable>();
        background = new Inventory<BackgroundItem>();
        people = new Inventory<Person>();

        northBlocked = false;
        eastBlocked = false;
        westBlocked = false;
        southBlocked = false;
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
        return(this.equals(room.northRoom));
    }
    public boolean isSouthOf(Room room) {
        return(this.equals(room.southRoom));
    }
    public boolean isEastOf(Room room) {
        return(this.equals(room.eastRoom));
    }
    public boolean isWestOf(Room room) {
        return(this.equals(room.westRoom));
    }
    public boolean isAbove(Room room) {
        return(this.equals(room.upRoom));
    }
    public boolean isBelow(Room room) {
        return(this.equals(room.downRoom));
    }

    public boolean hasNorthRoom() {
        return northRoom != null;
    }
    public boolean hasEastRoom() {
        return eastRoom != null;
    }
    public boolean hasWestRoom() {
        return westRoom != null;
    }
    public boolean hasSouthRoom() {
        return southRoom != null;
    }

    /**
    * Returns value of northRoom
    * @return
    */
    public Room getNorthRoom() {
        return northRoom;
    }

    /**
    * Returns value of eastRoom
    * @return
    */
    public Room getEastRoom() {
        return eastRoom;
    }

    /**
    * Returns value of westRoom
    * @return
    */
    public Room getWestRoom() {
        return westRoom;
    }

    /**
    * Returns value of southRoom
    * @return
    */
    public Room getSouthRoom() {
        return southRoom;
    }

    /**
    * Returns value of upRoom
    * @return
    */
    public Room getUpRoom() {
        return upRoom;
    }

    /**
    * Returns value of downRoom
    * @return
    */
    public Room getDownRoom() {
        return downRoom;
    }

    /**
    * Sets new value of northRoom
    * @param
    */
    public void setNorthRoom(Room northRoom) {
        this.northRoom = northRoom;
    }

    /**
    * Sets new value of eastRoom
    * @param
    */
    public void setEastRoom(Room eastRoom) {
        this.eastRoom = eastRoom;
    }

    /**
    * Sets new value of westRoom
    * @param
    */
    public void setWestRoom(Room westRoom) {
        this.westRoom = westRoom;
    }

    /**
    * Sets new value of southRoom
    * @param
    */
    public void setSouthRoom(Room southRoom) {
        this.southRoom = southRoom;
    }

    /**
    * Sets new value of upRoom
    * @param
    */
    public void setUpRoom(Room upRoom) {
        this.upRoom = upRoom;
    }

    /**
    * Sets new value of downRoom
    * @param
    */
    public void setDownRoom(Room downRoom) {
        this.downRoom = downRoom;
    }

    /**
     * Returns value of northBlocked
     * @return
     */
    public boolean isNorthBlocked() {
        return northBlocked;
    }
    public boolean isEastBlocked() {
        return eastBlocked;
    }
    public boolean isWestBlocked() {
        return westBlocked;
    }
    public boolean isSouthBlocked() {
        return southBlocked;
    }
    public void setNorthBlocked(boolean northBlocked) {
        this.northBlocked = northBlocked;
    }
    public void setEastBlocked(boolean eastBlocked) {
        this.eastBlocked = eastBlocked;
    }
    public void setWestBlocked(boolean westBlocked) {
        this.westBlocked = westBlocked;
    }
    public void setSouthBlocked(boolean southBlocked) {
        this.southBlocked = southBlocked;
    }
}
