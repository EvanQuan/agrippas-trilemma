package game.item;

import java.util.Arrays;
import java.util.ArrayList;

public class Item {
	public static final int PRIMARY = 0;
	private ArrayList<String> descriptions;
	private ArrayList<String> singleNames;
	private ArrayList<String> pluralNames;
	private double value;
	private double weight;

	/**
	 * Default constructor
	 * @param  String[] inSingleNames  single names of item
	 * @param  String[] inPluralNames  plural names of item
	 * @param  String[] inDescriptions examine description of item
	 */
	public Item(String[] inSingleNames, String[] inPluralNames, String[] inDescriptions, double inValue, double inWeight) {
		descriptions = new ArrayList<String>();
		singleNames = new ArrayList<String>();
		pluralNames = new ArrayList<String>();
		addSingleName(inSingleNames);
		addPluralName(inPluralNames);
		addDescription(inDescriptions);
		value = inValue;
		weight = inWeight;
	}

	public void addDescription(String[] inDescriptions) {
		addArrayToList(inDescriptions,descriptions);
	}
	public void addSingleName(String[] inNames) {
		addArrayToList(inNames,singleNames);
	}
	public void addPluralName(String[] inNames) {
		addArrayToList(inNames,pluralNames);
	}
	protected void addArrayToList(Object[] inArray, ArrayList inList) {
		inList.addAll(Arrays.asList(inArray));
	}

	public boolean equals(Item item) {
		return(
			singleNames.equals(item.getSingleNames) &&
			pluralNames.equals(item.getPluralNames) &&
			descriptions.equals(item.getDescriptions)
		);
	}

	public ArrayList<String> getDescriptions() {
		return(descriptions);
	}
	public ArrayList<String> getSingleNames() {
		return(singleNames);
	}
	public ArrayList<String> getPluralNames() {
		return(pluralNames);
	}

	public String getDescription(int index) {
		return(descriptions.get(index));
	}
	public String getDescription() {
		return(getDescription(PRIMARY));
	}
	public String getSingleName(int index) {
		return(singleNames.get(index));
	}
	public String getSingleName() {
		return(getSingleName(PRIMARY));
	}
	public String getPluralName(int index) {
		return(pluralNames.get(index));
	}
	public String getPluralName() {
		return(pluralNames.get(PRIMARY));
	}
	public double getValue() {
		return(value);
	}
	public double getWeight() {
		return(weight);
	}

	public String toString() {
		return(getSingleName());
	}

}
