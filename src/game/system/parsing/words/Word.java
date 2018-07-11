package game.system.parsing.words;

/**
 * Represents a word. A word must contain one or more alphanumeric characters.
 *
 * @author Evan Quan
 *
 */
public abstract class Word {

    private String name;

    /**
     * Complete constructor. Cannot be instantiated.
     *
     * @param name
     *            - Determines if the word is valid. This is determined by a
     *            combination of the word type and name, and its relation with other
     *            words.
     */
    public Word(String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of this word
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return String representation of word
     */
    public String toString() {
        return name;
    }
}
