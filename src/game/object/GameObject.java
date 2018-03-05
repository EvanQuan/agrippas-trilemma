package game.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import game.system.Utility;

/**
 * All objects in the game. Have names and descriptions.
 *
 * @author Evan Quan
 * @since March 4, 2018
 */
public abstract class GameObject implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT = 0;

    private ArrayList<String> singleNames;
    private ArrayList<String> pluralNames;
    private ArrayList<String> descriptions;
    private int descriptionIndex;

    /**
     * Game objects have properties that determine if other actions can be
     * performed.
     *
     * This is not structurally enforced.
     */
    private HashSet<String> properties;

    /**
     * Empty initialization constructor only used for subclasses
     */
    protected GameObject() {
        singleNames = new ArrayList<String>();
        pluralNames = new ArrayList<String>();
        descriptions = new ArrayList<String>();
        properties = new HashSet<String>();
        descriptionIndex = DEFAULT;
    }

    /**
     *
     * @param description
     *            to add
     */
    public void addDescription(String description) {
        descriptions.add(description);
    }

    /**
     *
     * @param descriptions
     *            to add
     */
    public void addDescriptions(String[] descriptions) {
        Utility.addArrayToList(descriptions, this.descriptions);
    }

    /**
     *
     * @param pluralName
     *            to add
     */
    public void addPluralName(String pluralName) {
        pluralNames.add(pluralName);
    }

    /**
     *
     * @param pluralNames
     *            to add
     */
    public void addPluralNames(String[] pluralNames) {
        Utility.addArrayToList(pluralNames, this.pluralNames);
    }

    /**
     * Add property to object
     *
     * @param property
     *            to add
     * @return true if object did not already have property
     */
    public boolean addProperty(String property) {
        return properties.add(property.toLowerCase());
    }

    /**
     *
     * @param singleName
     *            to add
     */
    public void addSingleName(String singleName) {
        singleNames.add(singleName);
    }

    /**
     *
     * @param singleNames
     *            to add
     */
    public void addSingleNames(String[] singleNames) {
        Utility.addArrayToList(singleNames, this.singleNames);
    }

    /**
     * Compares if other game object is identical to this one
     *
     * @param otherGameObject
     *            to compare
     * @return true if GameObjects are the same
     */
    public boolean equals(GameObject otherGameObject) {
        return this.singleNames.equals(otherGameObject.getSingleNames())
                && this.pluralNames.equals(otherGameObject.getPluralNames())
                && this.descriptions.equals(otherGameObject.getDescriptions())
                && this.properties.equals(otherGameObject.getProperties());
    }

    /**
     * Generic return property Children should override TODO
     *
     * @param
     * @return
     */
    public Object get(String property) {
        return null;
    }

    /**
     * @return get description of object
     */
    public String getDescription() {
        return getDescription(descriptionIndex);
    }

    public String getDescription(int index) {
        return this.descriptions.get(index);
    }

    /**
     * @return all descriptions of object
     */
    public ArrayList<String> getDescriptions() {
        return this.descriptions;
    }

    /**
     *
     * @return primary plural name
     */
    public String getPluralName() {
        return getPluralName(DEFAULT);
    }

    /**
     *
     * @param index
     *            of plural name
     * @return plural name of index
     */
    public String getPluralName(int index) {
        return this.pluralNames.get(index);
    }

    /**
     *
     * @return all plural names
     */
    public ArrayList<String> getPluralNames() {
        return this.pluralNames;
    }

    /**
     *
     * @return all properties
     */
    public HashSet<String> getProperties() {
        return this.properties;
    }

    /**
     * @return primary single name of object
     */
    public String getSingleName() {
        return getSingleName(DEFAULT);
    }

    public String getSingleName(int index) {
        return this.singleNames.get(index);
    }

    public ArrayList<String> getSingleNames() {
        return this.singleNames;
    }

    /**
     * Check if the object contains a contains a property. These properties are not
     * structurally enforced.
     *
     * @param property
     *            to check the object has
     * @return true is object contains property, else false
     */
    public boolean hasProperty(String property) {
        return properties.contains(property.toLowerCase());
    }

    /**
     * Remove property from object
     *
     * @param property
     *            to remove
     * @return true is object had property to remove
     */
    public boolean removeProperty(String property) {
        return properties.remove(property.toLowerCase());
    }

    /**
     * Generic set property Children should override
     *
     * @param property
     *            to set
     * @param value
     *            to set property to
     * @return true is property is set, else false
     */
    public boolean set(String property, Object value) {
        return false;
    }

    /**
     * Set properties
     */
    public void setDescriptions(String[] descriptions) {
        Utility.setArrayToList(descriptions, this.descriptions);
    }

    /**
     * Set description to the next available one if it exists
     */
    public void setNextDescription() {
        if (descriptionIndex < descriptions.size() - 1) {
            descriptionIndex++;
        }
    }

    public void setPluralNames(String[] pluralNames) {
        Utility.setArrayToList(pluralNames, this.pluralNames);
    }

    /**
     * Plural names by default are identical to single names with appended 's'
     * character
     */
    public void setPluralNamesDefault() {
        pluralNames.clear();
        for (int i = 0; i < singleNames.size(); i++) {
            pluralNames.add(i, singleNames.get(i) + "s");
        }
    }

    /**
     * Set description to the previous available one if it exists
     */
    public void setPreviousDescription() {
        if (descriptionIndex > DEFAULT) {
            descriptionIndex--;
        }
    }

    public void setSingleNames(String[] singleNames) {
        Utility.setArrayToList(singleNames, this.singleNames);
    }

    /**
     * Create string representation of GameObject for printing
     *
     * @return
     */
    @Override
    public String toString() {
        return (getSingleName());
    }
}
