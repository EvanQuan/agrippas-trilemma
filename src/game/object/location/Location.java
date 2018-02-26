package game.object.location;

import java.util.ArrayList;

import game.object.GameObject;
import game.object.ObjectContainer;
import game.object.item.background.BackgroundItem;
import game.object.item.background.character.Character;
import game.object.item.collectible.Collectible;

/**
 * Represents places. The player can be located in a single Location at a time,
 * where Locations are connected to one another through exits.
 *
 * @author Evan Quan
 * @since 2018-02-23
 *
 */
public class Location extends GameObject {

    private static final long serialVersionUID = 1L;

    private ObjectContainer<Collectible> items;
    private ObjectContainer<BackgroundItem> background;
    private ObjectContainer<Character> characters;
    private ArrayList<Exit> exits;

    private String adjacentName;

    private boolean visited;

    /**
     * Cannot instantiate a blank location
     */
    protected Location() {
        items = new ObjectContainer<Collectible>();
        background = new ObjectContainer<BackgroundItem>();
        characters = new ObjectContainer<Character>();
        exits = new ArrayList<Exit>();

        visited = false;
    }

    /**
     *
     * @param primaryName
     *            to add
     * @param primaryDescription
     *            to add
     */
    public Location(String primaryName, String primaryDescription) {
        this();
        addSingleName(primaryName);
        addDescription(primaryDescription);

        // Adjacent name defaults to singleName
        adjacentName = getSingleName();
    }

    /**
     *
     * @param singleNames
     *            to add
     * @param descriptions
     *            to add
     */
    public Location(String[] singleNames, String[] descriptions) {
        addSingleNames(singleNames);
        addDescriptions(descriptions);
    }

    /**
     * Add exit to this location if it does not already contain it
     *
     * @param exit
     *            to add
     */
    public void addExit(Exit exit) {
        if (!exits.contains(exit)) {
            exits.add(exit);
        }
    }

    /**
     * Name that room is referred to from other adjacent rooms' descriptions
     * Defaults to primary singleName
     */
    public String getAdjacentName() {
        return adjacentName;
    }

    public ObjectContainer<BackgroundItem> getBackground() {
        return background;
    }

    /**
     *
     * @return shallow copy of exits
     */
    public ArrayList<Exit> getExits() {
        return new ArrayList<Exit>(exits);
    }

    /**
     *
     * @return deep copy of exits
     */
    public ArrayList<Exit> getExitsDeep() {
        return exits;
    }

    public ObjectContainer<Collectible> getInventory() {
        return items;
    }

    public ObjectContainer<Character> getPeople() {
        return characters;
    }

    /**
     * Check if player has visited this room before
     *
     * @return true is player has visited this room before, else false
     */
    public boolean hasVisited() {
        return visited;
    }

    /**
     * Removes the specified exit if it already contains it
     *
     * @param exit
     *            to remove
     */
    public void removeExit(Exit exit) {
        exits.remove(exit);
    }

    public void setAdjacentName(String adjacentName) {
        this.adjacentName = adjacentName;
    }

    public void setBackground(ObjectContainer<BackgroundItem> background) {
        this.background = background;
    }

    public void setCharacters(ObjectContainer<Character> characters) {
        this.characters = characters;
    }

    /**
     * Set if the player has visited this room before
     *
     * @param visited
     *            true if player has visited this room, else false
     */
    public void setHasVisited(boolean visited) {
        this.visited = visited;
    }

    public void setItems(ObjectContainer<Collectible> items) {
        this.items = items;
    }
}
