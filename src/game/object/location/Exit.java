package game.object.location;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents an exit to from a location to another location. Exits connect
 * locations together by direction, though might not be mutually connected.
 *
 * Exits can be:
 * open (or closed)
 * openable
 * locked (or unlocked)
 * lockable
 *
 * @author Evan Quan
 * @since 2018-02-23
 *
 */
public class Exit implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final boolean DEFAULT_BLOCKED = false;
    public static final boolean DEFAULT_VISIBLE = true;
    public static final String DEFAULT_BLOCKED_REASON = "which is blocked";

    // Directions
    public static final int UNDEFINED = 0;
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;
    public static final int WEST = 4;
    public static final int UP = 5;
    public static final int DOWN = 6;
    public static final int NORTHEAST = 7;
    public static final int NORTHWEST = 8;
    public static final int SOUTHEAST = 9;
    public static final int SOUTHWEST = 10;
    public static final int IN = 11;
    public static final int OUT = 12;

    public static final String[] directionNames = { "UNDEFINED", "NORTH", "SOUTH", "EAST", "WEST", "UP", "DOWN",
            "NORTHEAST", "NORTHWEST", "SOUTHEAST", "SOUTHWEST", "IN", "OUT" };

    public static final String[] shortDirectionNames = { "NULL", "N", "S", "E", "W", "U", "D", "NE", "NW", "SE", "SW",
            "I", "O" };

    private Location leadsTo;
    private int direction;
    private String directionName;
    private String shortDirectionName;

    private boolean visible;
    private boolean blocked;
    private String blockedReason;

    public Exit(int direction, Location leadsTo) {
        this(direction, leadsTo, DEFAULT_VISIBLE, DEFAULT_BLOCKED, DEFAULT_BLOCKED_REASON);
    }

    public Exit(int direction, Location leadsTo, boolean visible) {
        this(direction, leadsTo, visible, DEFAULT_BLOCKED, DEFAULT_BLOCKED_REASON);
    }

    /**
     * Complete constructor
     *
     * @param direction
     *            the exit is leading to
     * @param leadsTo
     *            the location the exit refers to
     * @param visible
     *            if the exit is visible from the current room
     * @param blocked
     *            if the exit can be traversed to
     * @param blockedReason
     *            the reason that the exit cannot be traversed to
     */
    public Exit(int direction, Location leadsTo, boolean visible, boolean blocked, String blockedReason) {
        setDirection(direction);
        this.visible = visible;
        this.blocked = blocked;
        this.blockedReason = blockedReason;
    }

    public Exit(int direction, Location leadsTo, boolean blocked, String blockedReason) {
        this(direction, leadsTo, DEFAULT_VISIBLE, blocked, blockedReason);
    }

    /**
     *
     * @return blocked reason
     */
    public String getBlockedReason() {
        return blockedReason;
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
     * @return short direction name of exit
     */
    public String getShortDirectionName() {
        return shortDirectionName;
    }

    /**
     *
     * @return true is exit is blocked, else false
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     *
     * @return if exit is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param blocked
     *            status of exit
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     *
     * @param blockedReason
     */
    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }

    /**
     * Set the direction the exit leads to
     *
     * @param direction
     *            of exit
     */
    public void setDirection(int direction) {
        // Set direction to UNDEFINED if direction is invalid
        if (NORTH > direction || direction < OUT) {
            this.direction = UNDEFINED;
        } else {
            this.direction = direction;
        }
        directionName = directionNames[this.direction];
        shortDirectionName = shortDirectionNames[this.direction];
    }

    /**
     * Set the name of the direction the exit leads to
     *
     * @param directionName
     *            of exit
     */
    public void setDirectionName(String directionName) {
        if (Arrays.asList(directionNames).contains(directionName)) {
            this.directionName = directionName;
        } else {
            this.directionName = directionNames[UNDEFINED];
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
     *
     * @param visible
     *            status of exit
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return directionName + " is " + leadsTo.toString();
    }
}
