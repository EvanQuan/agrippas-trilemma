package util;

/**
 * Has utility functions for text
 *
 * @author Evan Quan
 *
 */
public abstract class TextUtils {

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
