package game.object.room;

import game.object.*;
import game.object.item.background.BackgroundItem;
import game.object.item.background.person.Person;
import game.object.item.collectable.Collectable;

public abstract class Room extends GameObject {

    private static final long serialVersionUID = 1L;

    private Inventory<Collectable> inv;
    private Inventory<BackgroundItem> background;
    private Inventory<Person> people;

    /**
     * Name that room is referred to from other adjacent rooms' descriptions
     * Defaults to primary singleName
     */
    private String adjacentName;

    private boolean firstTime;

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

    private String defaultBlockedReason;
    private String northBlockedReason;
    private String eastBlockedReason;
    private String westBlockedReason;
    private String southBlockedReason;
    private String upBlockedReason;
    private String downBlockedReason;

    public Room() {
        inv = new Inventory<Collectable>();
        background = new Inventory<BackgroundItem>();
        people = new Inventory<Person>();

        firstTime = true;

        adjacentName = getSingleName();

        northBlocked = false;
        eastBlocked = false;
        westBlocked = false;
        southBlocked = false;

        defaultBlockedReason = "which is blocked";
        northBlockedReason = defaultBlockedReason;
        eastBlockedReason = defaultBlockedReason;
        westBlockedReason = defaultBlockedReason;
        southBlockedReason = defaultBlockedReason;
        upBlockedReason = defaultBlockedReason;
        downBlockedReason = defaultBlockedReason;
    }

    /**
     * Output description of the room
     * First call is different from subsequent calls
     */
    public void outputDescription() {
        outputlnTitle(getSingleName());
        if (firstTime) {
            outputFirstDecription();
            firstTime = false;
        } else {
            outputRepeatDescription();
        }
    }
    /**
     * First description defaults to be same as repeated description
     * @return
     */
    public void outputFirstDecription() {
        outputRepeatDescription();
    }
    public void outputRepeatDescription() {
        outputln("There is nothing particular about your surroundings.");
    }

    /**
     * Output items in room
     */
    public void outputItems() {
        // TODO
        if (!inv.isEmpty()) {
            output("There is ");
            int i = 0; // Iterator used for establishing last item in inventory
            int count;
            for (Collectable item : inv.getItemSet()) {
                count = inv.getCount(item);
                if (count == 1) {
                    output("a");
                    if (startsWithVowel(item.getSingleName())) {
                        output("n");
                    }
                    outputItem(" " + item.getSingleName());
                } else {
                    output(Integer.toString(count));
                    outputItem(" " + item.getPluralName());
                }
                if ((i == inv.getItemCount() - 2) && (inv.getItemCount() > 1)) { // second last
                    output(" and ");
                } else if (i == inv.getItemCount() - 1) { // last
                    outputln(" here.");
                } else {
                    output(", ");
                }
                i++;
            }
        }
    }
    /**
     * Output adjacent rooms
     */
    public void outputAdjacentRooms() {
        if (hasNorthRoom()) {
            outputNorthRoom();
        }
        if (hasEastRoom()) {
            outputEastRoom();
        }
        if (hasWestRoom()) {
            outputWestRoom();
        }
        if (hasSouthRoom()) {
            outputSouthRoom();
        }
        if (hasUpRoom()) {
            outputUpRoom();
        }
        if (hasDownRoom()) {
            outputDownRoom();
        }
    }
    public void outputDirectionRoom(String direction, Room room) {
        outputDirection(direction);
        output(" is ");
        outputRoom(room.getAdjacentName());
    }
    public void outputNorthRoom() {
        outputDirectionRoom("North", northRoom);
        if (isNorthBlocked()) {
            output(", " + northBlockedReason);
        }
        outputln(".");

    }
    public void outputEastRoom() {
        outputDirectionRoom("East", eastRoom);
        if (isEastBlocked()) {
            output(", " + eastBlockedReason);
        }
        outputln(".");
    }
    public void outputWestRoom() {
        outputDirectionRoom("West", westRoom);
        if (isWestBlocked()) {
            output(", " + westBlockedReason);
        }
        outputln(".");
    }
    public void outputSouthRoom() {
        outputDirectionRoom("South", southRoom);
        if (isSouthBlocked()) {
            output(", " + southBlockedReason);
        }
        outputln(".");
    }
    public void outputUpRoom() {
        outputDirectionRoom("Up", upRoom);
        if (isUpBlocked()) {
            output(", " + upBlockedReason);
        }
        outputln(".");
    }
    public void outputDownRoom() {
        outputDirectionRoom("Down", downRoom);
        if (isDownBlocked()) {
            output(", " + downBlockedReason);
        }
        outputln(".");
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
    public boolean hasUpRoom() {
        return upRoom != null;
    }
    public boolean hasDownRoom() {
        return downRoom != null;
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
    public boolean isUpBlocked() {
        return upBlocked;
    }
    public boolean isDownBlocked() {
        return downBlocked;
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
    public void setUpBlocked(boolean upBlocked) {
        this.upBlocked = upBlocked;
    }
    public void setDownBlocked(boolean downBlocked) {
        this.downBlocked = downBlocked;
    }
    public void setNorthBlockedReason(String northBlockedReason) {
        this.northBlockedReason = northBlockedReason;
    }
    public void setEastBlockedReason(String eastBlockedReason) {
        this.eastBlockedReason = eastBlockedReason;
    }
    public void setWestBlockedReason(String westBlockedReason) {
        this.westBlockedReason = westBlockedReason;
    }
    public void setSouthBlockedReason(String southBlockedReason) {
        this.southBlockedReason = southBlockedReason;
    }
    public void setUpBlockedReason(String upBlockedReason) {
        this.upBlockedReason = upBlockedReason;
    }
    public void setDownBlockedReason(String downBlockedReason) {
        this.downBlockedReason = downBlockedReason;
    }
    public String getNorhBlockedReason() {
        return northBlockedReason;
    }
    public String getEastBlockedReason() {
        return eastBlockedReason;
    }
    public String getWestBlockedReason() {
        return westBlockedReason;
    }
    public String getSouthBlockedReason() {
        return southBlockedReason;
    }
    public String getUpBlockedReason() {
        return upBlockedReason;
    }
    public String getDownBlockedReason() {
        return downBlockedReason;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
    public boolean getFirstTime() {
        return firstTime;
    }
    public void setAdjacentName(String adjacentName) {
        this.adjacentName = adjacentName;
    }
    public String getAdjacentName() {
        return adjacentName;
    }
}
