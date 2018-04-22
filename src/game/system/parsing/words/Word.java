package game.system.parsing.words;

/**
 * Represents a word
 *
 * @author Evan Quan
 *
 */
public abstract class Word {

    boolean valid;
    String name;

    /**
     *
     * @return the name of this word
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return true if the world is valid. This is determined by a combination of
     *         the word type and name, and its relation with other words.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     *
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
