package test.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Has text utility functions
 */
public abstract class ArrayUtil {

    public static final String EMPTY = "";

    /**
     * Add array to ArrayList<String>
     *
     * @param array
     *            to add to list
     * @param list
     */
    public static void addArrayToList(String[] array, ArrayList<String> list) {
        list.addAll(Arrays.asList(array));
    }

    public static ArrayList<Double> getDoubleArrayList(double[] doubleArray) {
        ArrayList<Double> doubleList = new ArrayList<Double>();
        for (int i = 0; i < doubleArray.length; i++) {
            doubleList.add(doubleArray[i]);
        }
        return doubleList;
    }

    public static ArrayList<Integer> getIntegerArrayList(int[] intArray) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 0; i < intArray.length; i++) {
            intList.add(intArray[i]);
        }
        return intList;
    }

    /**
     * Convert ArrayList<Integer> or ArrayList<Double> to ArrayList<String>
     *
     * @param ArrayList<Integer>
     *            intList
     * @return
     */
    public static ArrayList<String> getStringArrayList(ArrayList<Number> inList) {
        ArrayList<String> stringList = new ArrayList<String>();

        if (inList.isEmpty()) {
            stringList.add(EMPTY);
        } else if (inList.get(0) instanceof Integer) {
            for (int i = 0; i < inList.size(); i++) {
                try {
                    stringList.add(Integer.toString((int) inList.get(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (inList.get(0) instanceof Double) {
            for (int i = 0; i < inList.size(); i++) {
                try {
                    stringList.add(Double.toString((double) inList.get(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return stringList;
    }

    // public static ArrayList<String> getStringArrayList(double doub) {
    // return getStringArrayList(new double[] { doub });
    // }

    // public static ArrayList<String> getStringArrayList(double[] doubleArray) {
    // return getStringArrayList(getDoubleArrayList(doubleArray));
    // }
    //
    // public static ArrayList<String> getStringArrayList(int integer) {
    // return getStringArrayList(getStringArrayList(integer));
    // }
    //
    // public static ArrayList<String> getStringArrayList(int[] intArray) {
    // return getStringArrayList(getIntegerArrayList(intArray));
    // }

    public static ArrayList<String> getStringArrayList(String word) {
        return getStringArrayList(new String[] { word });
    }

    public static ArrayList<String> getStringArrayList(String[] strArray) {
        return new ArrayList<String>(Arrays.asList(strArray));
    }

    public static ArrayList<String> getStringArrayListFromDouble(ArrayList<Double> doubleList) {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Double doub : doubleList) {
            try {
                stringList.add(Double.toString(doub));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }

    /**
     *
     * @param array
     * @param list
     */
    public static void setArrayToList(String[] array, ArrayList<String> list) {
        list.clear();
        addArrayToList(array, list);
    }

    /**
     * Sorts list from longest to shortest String length
     * https://stackoverflow.com/questions/36292300/how-to-sort-an-arraylist-by-length-of-strings-in-the-array
     *
     * @param ArrayList<String>
     *            inList
     */
    public static void sortByLongestFirst(ArrayList<String> inList) {
        ArrayList<String> outList = new ArrayList<String>();
        while (!inList.isEmpty()) {
            String longest = "";
            for (String string : inList) {
                if (string.length() > longest.length()) {
                    longest = string;
                }
            }
            outList.add(longest);
            while (inList.contains(longest)) {
                inList.remove(longest);
            }
        }
        inList.addAll(outList);
    }

    /**
     * Sorts array from longest to shortest String length
     *
     * @param inArray
     * @return sorted array
     */
    public static String[] sortByLongestFirst(String[] inArray) {
        ArrayList<String> outList = new ArrayList<String>(Arrays.asList(inArray));
        sortByLongestFirst(outList);
        return outList.toArray(new String[0]);
    }


}
