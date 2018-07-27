package game.system.input.words;
import util.FuncUtils;

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

    private String verb;

    public VerbPhrase() {
    }

    public VerbPhrase(String verb) {
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
            return hasSameVerb((VerbPhrase) other);
        }
        return false;
    }

    public String getVerb() {
        return this.verb;
    }

    public boolean hasVerb() {
        return this.verb != null;
    }

    public boolean hasSameVerb(VerbPhrase other) {
        return FuncUtils.nullablesEqual(this.verb, other.getVerb());
    }

    /**
     *
     * @return true if this verb phrase does not have a verb.
     */
    public boolean isEmpty() {
        return !hasVerb();
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    @Override
    public String toString() {
        return "[verb: " + verb + "]";
    }
}
