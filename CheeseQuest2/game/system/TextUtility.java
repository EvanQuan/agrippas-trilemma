package game.system;

/**
 * Has text utility functions
 */
public abstract class TextUtility {

    /**
     * Converts beginning of each word in string to upper case
     * @param  String string        to be converted
     * @return        title case string
     */
    public String toTitleCase(String string) {
        String[] words = string.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1);
        }
        return String.join(" ",words);
    }

    /**
     * Coverts to lower case before title case
     * Important for convert upper case or mixed case to title case
     * @param  String string        to be converted
     * @return        title case string
     */
    public String toLowerTitleCase(String string) {
        return toTitleCase(string.toLowerCase());
    }

    /**
     * @param  String string
     * @return        true if string starts with a vowel
     */
    public boolean startsWithVowel(String string) {
        switch(Character.toLowerCase(string.charAt(0))) {
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
     * @param  String fileName
     * @return        fileName without extention
     */
    public String stripExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileName = fileName.substring(0,dotIndex);
        }
        return fileName;
    }

    /**
     * Split word by upper case letters
     * @param  String string        [description]
     * @return        [description]
     */
    public String[] splitCamelCase(String string) {
        return string.split("(?<!^)(?=[A-Z])");
    }

    /**
     * Split word by upper case letters by spaces
     * @param  String string        [description]
     * @return        [description]
     */
    public String splitCamelCaseToString(String string) {
        return String.join(" ", splitCamelCase(string));
    }
}
