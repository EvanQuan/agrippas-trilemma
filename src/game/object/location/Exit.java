package game.object.location;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents an exit from a location to another location. Exits connect
 * locations together by direction, though might not be mutually connected.
 *
 * @author Evan Quan
 * @since March 4, 2018
 */
public class Exit implements Serializable {

    private static final long serialVersionUID = 1L;

    // Directions
    /**
     * Direction of location the exit leads to is North
     */
    public static final int DIRECTION_NORTH = 0;
    /**
     * Direction of location the exit leads to is South
     */
    public static final int DIRECTION_SOUTH = 1;
    /**
     * Direction of location the exit leads to is East
     */
    public static final int DIRECTION_EAST = 2;
    /**
     * Direction of location the exit leads to is West
     */
    public static final int DIRECTION_WEST = 3;
    /**
     * Direction of location the exit leads to is Up
     */
    public static final int DIRECTION_UP = 4;
    /**
     * Direction of location the exit leads to is Down
     */
    public static final int DIRECTION_DOWN = 5;
    /**
     * Direction of location the exit leads to is Northeast
     */
    public static final int DIRECTION_NORTHEAST = 6;
    /**
     * Direction of location the exit leads to is Northwest
     */
    public static final int DIRECTION_NORTHWEST = 7;
    /**
     * Direction of location the exit leads to is Southeast
     */
    public static final int DIRECTION_SOUTHEAST = 8;
    /**
     * Direction of location the exit leads to is Southwest
     */
    public static final int DIRECTION_SOUTHWEST = 9;
    /**
     * Direction of location the exit leads to is In
     */
    public static final int DIRECTION_IN = 10;
    /**
     * Direction of location the exit leads to is Out
     */
    public static final int DIRECTION_OUT = 11;

    public static final String[] directionNames = { "north", "south", "east", "west", "up", "down", "northeast",
            "northwest", "southeast", "southwest", "in", "out" };
    public static final String[] shortDirectionNames = { "n", "s", "e", "w", "u", "d", "ne", "nw", "se", "sw", "in",
            "o" }; // NOTE: i is reserved for inventory

    /**
     * Open, not openable by player
     */
    public static final int TYPE_DEFAULT = 12;
    /**
     * Open, openable by player
     */
    public static final int TYPE_OPEN = 13;
    /**
     * Closed, openable by player
     */
    public static final int TYPE_CLOSED = 14;
    /**
     * Closed, unopenable by player
     */
    public static final int TYPE_BLOCKED = 15;

    /**
     * Open space
     */
    public static final int KIND_DEFAULT = 16;
    /**
     * Physical door
     */
    public static final int KIND_DOOR = 17;

    /**
     * Get an exit
     *
     * @param type
     *            the mechanic properties of of the exit
     * @param kind
     *            the flavor of the exit
     * @param direction
     *            the exit is pointing towards
     * @param leadsTo
     *            location the exit leads to
     * @return exit created
     *
     * @throws IllegalArgumentException
     *             if invalid type, kind, direction, or leadsTo
     */
    public static Exit getExit(int type, int kind, int direction, Location leadsTo) throws IllegalArgumentException {
        boolean openable;
        boolean open;
        String openedDescription;
        String closedDescription;

        switch (type) {
            case TYPE_DEFAULT:
                openable = false;
                open = false;
                break;
            case TYPE_OPEN:
                openable = true;
                open = false;
                break;
            case TYPE_CLOSED:
                openable = true;
                open = true;
                break;
            case TYPE_BLOCKED:
                openable = false;
                open = true;
                break;
            default:
                throw new IllegalArgumentException();
        }

        switch (kind) {
            case KIND_DEFAULT:
                openedDescription = "which is open";
                closedDescription = "which is blocked";
                break;
            case KIND_DOOR:
                openedDescription = "which is open";
                closedDescription = "which is closed";
                break;
            default:
                throw new IllegalArgumentException();
        }

        Exit exit = new Exit(direction, leadsTo, openable, open, openedDescription, closedDescription);
        return exit;
    }

    /**
     * Reverse the direction
     *
     * @param direction
     *            to reverse
     * @return reversed direction
     * @throws IllegalArgumentException
     *             if direction is invalid
     */
    public static int reverseDirection(int direction) throws IllegalArgumentException {
        switch (direction) {
            case Exit.DIRECTION_NORTH:
                return Exit.DIRECTION_SOUTH;
            case Exit.DIRECTION_NORTHEAST:
                return Exit.DIRECTION_SOUTHWEST;
            case Exit.DIRECTION_EAST:
                return Exit.DIRECTION_WEST;
            case Exit.DIRECTION_SOUTHEAST:
                return Exit.DIRECTION_NORTHWEST;
            case Exit.DIRECTION_SOUTH:
                return Exit.DIRECTION_NORTH;
            case Exit.DIRECTION_SOUTHWEST:
                return Exit.DIRECTION_NORTHEAST;
            case Exit.DIRECTION_WEST:
                return Exit.DIRECTION_EAST;
            case Exit.DIRECTION_NORTHWEST:
                return Exit.DIRECTION_SOUTHEAST;
            case Exit.DIRECTION_IN:
                return Exit.DIRECTION_OUT;
            case Exit.DIRECTION_OUT:
                return Exit.DIRECTION_IN;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * The location that the exit leads to
     */
    private Location leadsTo;

    /**
     * The direction that the exit leads to
     */
    private int direction;
    /**
     * The full name of the direction that the exit leads to
     */
    private String directionName;

    /**
     * The abbreviated name of the direction that the exit leads to
     */
    private String shortDirectionName;
    /**
     * The state of whether the player can traverse through the exit
     */
    private boolean open;
    /**
     * The state of whether the exit can be opened and closed by the player
     */
    private boolean openable;

    private String openedDescription;
    private String closedDescription;

    /**
     * Complete constructor
     *
     * @param direction
     *            the exit is leading to
     * @param leadsTo
     *            the location the exit refers to
     * @param visible
     *            if the exit is visible from the current room
     * @param open
     *            if the exit can be traveled to
     * @param openedDescription
     *            the description of the open exit
     * @param closedDescription
     *            the description of the closed exit
     */
    private Exit(int direction, Location leadsTo, boolean openable, boolean open, String openedDescription,
            String closedDescription) {
        setDirection(direction);
        this.leadsTo = leadsTo;
        this.openable = openable;
        this.open = open;
        this.openedDescription = openedDescription;
        this.closedDescription = closedDescription;
    }

    /**
     * Get shallow copy of this exit
     */
    @Override
    public Exit clone() {
        return new Exit(direction, leadsTo, openable, open, openedDescription, closedDescription);
    }

    /**
     *
     * @return closed description
     */
    public String getClosedDescription() {
        return closedDescription;
    }

    /**
     *
     * @return direction this exit leads to
     */
    public int getDirection() {
        return direction;
    }

    /**
     *
     * @return direction name of exit
     */
    public String getDirectionName() {
        return directionName;
    }

    /**
     *
     * @return Location this exit leads to
     */
    public Location getLeadsTo() {
        return leadsTo;
    }

    /**
     *
     * @return opened description
     */
    public String getOpenedDescription() {
        return openedDescription;
    }

    /**
     *
     * @return short direction name of exit
     */
    public String getShortDirectionName() {
        return shortDirectionName;
    }

    /**
     *
     * @return true is exit is open, else false
     */
    public boolean isOpen() {
        return open;
    }

    /**
     *
     * @return true is exit is openable by player, else false
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     *
     * @param closedDescription
     */
    public void setClosedDescription(String closedDescription) {
        this.closedDescription = closedDescription;
    }

    /**
     * Set the direction the exit leads to
     *
     * @param direction
     *            of exit
     * @throws IllegalArgumentException
     *             if direction is not valid
     */
    public void setDirection(int direction) throws IllegalArgumentException {
        if (DIRECTION_NORTH > direction || direction > DIRECTION_OUT) {
            throw new IllegalArgumentException();
        } else {
            this.direction = direction;
            directionName = directionNames[this.direction];
            shortDirectionName = shortDirectionNames[this.direction];
        }
    }

    /**
     * Set the name of the direction the exit leads to
     *
     * @param directionName
     *            of exit
     */
    public void setDirectionName(String directionName) throws IllegalArgumentException {
        if (Arrays.asList(directionNames).contains(directionName)) {
            this.directionName = directionName;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param leadsTo
     *            Location of this exit
     */
    public void setLeadsTo(Location leadsTo) {
        this.leadsTo = leadsTo;
    }

    /**
     * Set if the exit if open or not. If open the exit can travel through, else the
     * it cannot.
     *
     * @param open
     *            status of exit
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Set if the exit is able to be changed between opened and closed by the
     * player. This property has no mechanical impact on this exit. Used only in
     * integrating with game.
     *
     * @param openable
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     *
     * @param openedDescription
     */
    public void setOpenedDescription(String openedDescription) {
        this.openedDescription = openedDescription;
    }

    /**
     * Set the short name of the direction the exit leads to
     *
     * @param shortDirectionName
     *            of exit
     */
    public void setShortDirectionName(String shortDirectionName) {
        if (Arrays.asList(shortDirectionNames).contains(shortDirectionName)) {
            this.shortDirectionName = shortDirectionName;
        } else {
            this.shortDirectionName = shortDirectionName;
        }
    }

    /**
     * Toggle the open status of the exit
     */
    public void toggleOpen() {
        if (open) {
            open = false;
        } else {
            open = true;
        }
    }
}
