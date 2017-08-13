package game.object;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;

import game.Savable;
import game.object.item.Item;

/**
 * All objects in the game
 */
public abstract class GameObject implements Serializable {
    public static final int DEFAULT = 0;
    private ArrayList<String> singleNames;
    private ArrayList<String> pluralNames;
    private ArrayList<String> descriptions;

    public GameObject() {
        this.singleNames = new ArrayList<String>();
        this.pluralNames = new ArrayList<String>();
        this.descriptions = new ArrayList<String>();
    }

    public void addDescription(String[] descriptions) {
        addArrayToList(descriptions,this.descriptions);
    }
    public void addSingleName(String[] singleNames) {
        addArrayToList(singleNames,this.singleNames);
    }
    public void addPluralName(String[] pluralNames) {
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
        return(this.descriptions);
    }
    public ArrayList<String> getSingleNames() {
        return(this.singleNames);
    }
    public ArrayList<String> getPluralNames() {
        return(this.pluralNames);
    }

    public String getDescription(int index) {
        return(this.descriptions.get(index));
    }
    public String getDescription() {
        return(getDescription(DEFAULT));
    }
    public String getSingleName(int index) {
        return(this.singleNames.get(index));
    }
    public String getSingleName() {
        return(getSingleName(DEFAULT));
    }
    public String getPluralName(int index) {
        return(this.pluralNames.get(index));
    }
    public String getPluralName() {
        return(getPluralName(DEFAULT));
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
