package game.system.parsing.words;

import java.util.ArrayList;

/**
 * Identifies a person, thing, idea, quality, or state.
 * <p>
 * May be described by an {@link Adjective}.<br>
 * May "own" or be "owned by" another noun.
 *
 * @author Evan Quan
 *
 */
public class Noun extends Word {

    private ArrayList<Adjective> adjectives;
    private ArrayList<Noun> owns;
    private ArrayList<Noun> ownedBy;

    /**
     *
     * @return list of adjectives that describe this noun
     */
    public ArrayList<Adjective> getAdjective() {
        return adjectives;
    }

    /**
     *
     * @return list of nouns that "own" this noun
     */
    public ArrayList<Noun> getOwnedBy() {
        return ownedBy;
    }

    /**
     *
     * @return list of nouns that this noun "owns'
     */
    public ArrayList<Noun> getOwns() {
        return owns;
    }

    /**
     *
     * @return true is this noun is described by some adjectives
     */
    public boolean hasAdjective() {
        return adjectives.isEmpty();
    }
}
