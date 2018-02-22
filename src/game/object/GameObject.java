package game.object;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;

import game.system.Outputable;

/**
 * All objects in the game
 */
public abstract class GameObject extends Outputable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT = 0;
    private ArrayList<String> singleNames;
    private ArrayList<String> pluralNames;
    private ArrayList<String> descriptions;
    /**
     * Game objects have properties that determine if other actions
     * can be performed.
     *
     * This is not structurally enforced.
     * */
    private HashSet<String> properties;

    public GameObject() {
        singleNames = new ArrayList<String>();
        pluralNames = new ArrayList<String>();
        descriptions = new ArrayList<String>();
        properties = new HashSet<String>();

//        singleNames.add("Game object");
//        descriptions.add("Default description");
    }

    /**
     * Set properties
     */
    public void setDescriptions(String[] descriptions) {
        setArrayToList(descriptions,this.descriptions);
    }
    public void setSingleNames(String[] singleNames) {
        setArrayToList(singleNames,this.singleNames);
    }
    public void setPluralNames(String[] pluralNames) {
        setArrayToList(pluralNames,this.pluralNames);
    }
    /**
     * Plural names by default are identical to single names with appended 's' character
     */
    public void setPluralNamesDefault() {
        pluralNames.clear();
        for (int i = 0; i < singleNames.size(); i++) {
            pluralNames.add(i,singleNames.get(i) + "s");
        }
    }
    protected void setArrayToList(String[] array, ArrayList<String> list) {
        list.clear();
        addArrayToList(array,list);
    }
    /**
     * Append properties
     */
    public void addDescriptions(String[] descriptions) {
        addArrayToList(descriptions,this.descriptions);
    }
    public void addSingleNames(String[] singleNames) {
        addArrayToList(singleNames,this.singleNames);
    }
    public void addPluralNames(String[] pluralNames) {
        addArrayToList(pluralNames,this.pluralNames);
    }

    protected void addArrayToList(String[] array, ArrayList<String> list) {
        list.addAll(Arrays.asList(array));
    }

    /**
     * Check if the object contains a contains a property.
     * These properties are not structurally enforced.
     * @param property to check the object has
     * @return true is object contains property, else false
     */
    public boolean hasProperty(String property) {
        return properties.contains(property.toLowerCase());
    }
    /**
     * Add property to object
     * @param property to add
     * @return true if object did not already have property
     */
    public boolean addProperty(String property) {
        return properties.add(property.toLowerCase());
    }
    /**
     * Remove property from object
     * @param property to remove
     * @return true is object had property to remove
     */
    public boolean removeProperty(String property) {
        return properties.remove(property.toLowerCase());
    }


    /**
     * Generic return property
     * Children should override
     * @param
     * @return
     */
    public Object get(String property) {
        return null;
    }

    /**
     * Generic set property
     * Children should override
     * @param property to set
     * @param value to set property to
     * @return true is property is set, else false
     */
    public boolean set(String property, Object value) {
        return false;
    }

    /**
     * Compares if other game object is identical to this one
     * @param otherGameObject to compare
     * @return true is GameObjects are the same
     */
    public boolean equals(GameObject otherGameObject) {
        return
            this.singleNames.equals(otherGameObject.getSingleNames())
            && this.pluralNames.equals(otherGameObject.getPluralNames())
            && this.descriptions.equals(otherGameObject.getDescriptions())
            && this.properties.equals(otherGameObject.getProperties());
    }

    public ArrayList<String> getDescriptions() {
        return this.descriptions;
    }
    public ArrayList<String> getSingleNames() {
        return this.singleNames;
    }
    public ArrayList<String> getPluralNames() {
        return this.pluralNames;
    }

    public String getDescription(int index) {
        return this.descriptions.get(index);
    }
    public String getDescription() {
        return getDescription(DEFAULT);
    }
    public String getSingleName(int index) {
        String name = "";
        try {
            name = this.singleNames.get(index);
        } catch (Exception e)  {
            outputln("singleNames: " + singleNames);
            e.printStackTrace();
        }
        return name;
    }
    public String getSingleName() {
        return getSingleName(DEFAULT);
    }
    public String getPluralName(int index) {
        return this.pluralNames.get(index);
    }
    public String getPluralName() {
        return getPluralName(DEFAULT);
    }

    public HashSet<String> getProperties() {
        return this.properties;
    }

    /**
    * Create string representation of GameObject for printing
    * @return
    */
    @Override
    public String toString() {
        return(getSingleName());
    }
}
