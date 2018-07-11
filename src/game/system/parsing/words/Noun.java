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
    private Determiner determiner;
    private ArrayList<Noun> owns;
    private ArrayList<Noun> ownedBy;

    /**
     * Complete constructor.
     *
     * @param name
     * @param adjectives
     * @param owns
     * @param ownedBy
     */
    public Noun(String name, ArrayList<Adjective> adjectives, Determiner determiner, ArrayList<Noun> owns,
            ArrayList<Noun> ownedBy) {
        super(name);
        this.adjectives = adjectives;
        this.determiner = determiner;
        this.owns = owns;
        this.ownedBy = ownedBy;
    }

    /**
     *
     * @return list of adjectives that describe this noun
     */
    public ArrayList<Adjective> getAdjective() {
        return adjectives;
    }

    /**
     *
     * @return determiner
     */
    public Determiner getDeterminer() {
        return determiner;
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

    /**
     *
     * @return true if this noun has a determiner
     */
    public boolean hasDeterminer() {
        return determiner == null;
    }
}
