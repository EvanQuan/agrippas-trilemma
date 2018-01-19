package game.object;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;

import game.object.item.Item;
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

    public GameObject() {
        singleNames = new ArrayList<String>();
        pluralNames = new ArrayList<String>();
        descriptions = new ArrayList<String>();
        singleNames.add("game object");
        descriptions.add("Default description");
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
     * Plural names are identical to single names with appended 's' character
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

    public boolean equals(Item item) {
        return(
            this.singleNames.equals(item.getSingleNames()) &&
            this.pluralNames.equals(item.getPluralNames()) &&
            this.descriptions.equals(item.getDescriptions())
        );
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

    /**
    * Create string representation of GameObject for printing
    * @return
    */
    @Override
    public String toString() {
        return(getSingleName());
    }
}
