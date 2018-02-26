package game.system;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Has text utility functions
 */
public abstract class Utility {

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

    /**
     * Split word by upper case letters
     *
     * @param
     * @return
     */
    public static String[] splitCamelCase(String string) {
        return string.split("(?<!^)(?=[A-Z])");
    }

    /**
     * Split word by upper case letters by spaces
     *
     * @param String
     *            string [description]
     * @return [description]
     */
    public static String splitCamelCaseToString(String string) {
        return String.join(" ", splitCamelCase(string));
    }

    /**
     * @param String
     *            string
     * @return true if string starts with a vowel
     */
    public static boolean startsWithVowel(String string) {
        switch (Character.toLowerCase(string.charAt(0))) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    /**
     * Removes extension from file name, noted by a '.'
     *
     * @param String
     *            fileName
     * @return fileName without extension
     */
    public static String stripExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileName = fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    /**
     * Converts to lower case before title case Important for convert upper case or
     * mixed case to title case
     *
     * @param String
     *            string to be converted
     * @return title case string
     */
    public static String toLowerTitleCase(String string) {
        return toTitleCase(string.toLowerCase());
    }

    /**
     * Converts beginning of each word in string to upper case
     *
     * @param String
     *            string to be converted
     * @return title case string
     */
    public static String toTitleCase(String string) {
        String[] words = string.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
        }
        return String.join(" ", words);
    }

}
