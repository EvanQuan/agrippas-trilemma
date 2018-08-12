package game.system.input.words;
import util.FuncUtils;

import java.util.ArrayList;

/**
 * Verb phrases describe a verb. As it stands now, this is equivalent to just a single verb String,
 * but later on, extra properties may be added (Ex. adverbs).
 * They conform to the following grammar:
 * <p>
 * Verb?
 *
 * @author Evan Quan
 *
 */
public class VerbPhrase {

    ArrayList<String> adverbs;
    private String verb;

    public VerbPhrase() {
    }

    public VerbPhrase(String verb) {
        adverbs = new ArrayList<>();
        this.verb = verb;
    }

    /**
     *
     * @param other
     *            phrase to compare equality with.
     * @return true if the article, adjective, and noun are equal for both object
     *         phrases.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof VerbPhrase) {
            return hasSameAdjectives((VerbPhrase) other)
            && hasSameVerb((VerbPhrase) other);
        }
        return false;
    }

    public ArrayList<String> getAdverbs() {
        return this.adverbs;
    }

    public String getVerb() {
        return this.verb;
    }

    public boolean hasAdverbs() {
        return !this.adverbs.isEmpty();
    }

    public boolean hasVerb() {
        return this.verb != null;
    }

    public boolean hasSameAdjectives(VerbPhrase other) {
        return FuncUtils.nullablesEqual(this.adverbs, other.getAdverbs());
    }

    public boolean hasSameVerb(VerbPhrase other) {
        return FuncUtils.nullablesEqual(this.verb, other.getVerb());
    }

    /**
     *
     * @return true if this verb phrase does not have a verb.
     */
    public boolean isEmpty() {
        return !hasAdverbs() && !hasVerb();
    }

    public void setAdverbs(ArrayList<String> adverbs) {
        this.adverbs = adverbs;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }


    @Override
    public String toString() {
        return "["
                + (hasAdverbs() ? "adverbs: " + adverbs : "")
                + (hasVerb() ? " | verb: " + verb : "")
                + "]";
    }
}
