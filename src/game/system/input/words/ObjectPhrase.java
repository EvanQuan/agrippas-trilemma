package game.system.input.words;

import util.CollectionUtils;
import util.FuncUtils;

import java.util.ArrayList;

/**
 * Object phrases describe an object, or noun. They conform to the following
 * grammar:
 * <br><br>
 * Determiner? Adjective* Noun? (of ObjectPhrase?)?
 * <br><br>
 * (ooooo recursion, spooky!)
 * @author Evan Quan
 */
public class ObjectPhrase {

    private String determiner;
    private ArrayList<String> adjectives;
    private String noun;
    private String belongingPreposition;
    private ObjectPhrase owner;

    public ObjectPhrase() {
        this.adjectives = new ArrayList<>();
    }

    /**
     * TODO Might have infinite recursion issue.
     *
     * @param other phrase to compare equality with.
     * @return true if the article, adjective, and noun are equal for both
     * object phrases.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ObjectPhrase) {
            return hasSameDeterminer((ObjectPhrase) other)
                    && hasSameAdjectives((ObjectPhrase) other)
                    && hasSameNoun((ObjectPhrase) other)
                    && hasSamePreposition((ObjectPhrase) other)
                    && hasSameOwner((ObjectPhrase) other);
        }
        return false;
    }

    /**
     * @return a shallow copy of this phrase's adjectives. This is so that the
     * adjective list can be modified for receiveInput purposes if need be
     * without actually affecting the actual adjectives.<br> null if adjectives
     * is null. NOTE: Due to current PlayerInputParser.getObjectPhrase()
     * implementation, adjectives will never be null, as an ObjectPhrase with no
     * adjectives will have an empty ArrayList
     */
    public ArrayList<String> getAdjectives() {
        return this.adjectives;
    }

    public String getDeterminer() {
        return this.determiner;
    }

    public String getNoun() {
        return this.noun;
    }

    public boolean hasAdjectives() {
        return this.adjectives != null && !this.adjectives.isEmpty();
    }

    public String getBelongingPreposition() {
        return this.belongingPreposition;
    }

    public ObjectPhrase getOwner() {
        return this.owner;
    }

    public boolean hasDeterminer() {
        return this.determiner != null;
    }

    public boolean hasNoun() {
        return this.noun != null;
    }

    public boolean hasBelongingPreposition() {
        return this.belongingPreposition != null;
    }

    public boolean hasOwner() {
        return this.owner != null;
    }

    public boolean hasSameAdjectives(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.adjectives, other.getAdjectives());
    }

    public boolean hasSameDeterminer(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.determiner, other.getDeterminer());
    }

    public boolean hasSameNoun(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.noun, other.getNoun());
    }

    public boolean hasSamePreposition(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.belongingPreposition,
                other.getBelongingPreposition());
    }

    public boolean hasSameOwner(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.owner, other.getOwner());
    }

    /**
     * @return true if this object phrase does not have a determiner, adjectives
     * and noun.
     */
    public boolean isEmpty() {
        return !hasDeterminer() && !hasAdjectives() && !hasNoun();
    }

    public void setAdjectives(ArrayList<String> adjectives) {
        this.adjectives = adjectives;
    }

    public void setAdjectives(String[] adjectives) {
        this.adjectives = CollectionUtils.getArrayList(adjectives);
    }

    public void setDeterminer(String determiner) {
        this.determiner = determiner;
    }

    /**
     * The only valid example is "of"
     *
     * @param belongingPreposition
     */
    public void setBelongingPreposition(String belongingPreposition) {
        this.belongingPreposition = belongingPreposition;
    }

    /**
     * Object phrases can be "owned" by other object phrases.
     * <br>
     * <b>Horns of Dirge</b>
     * <br>
     * Where the object phrase <b>Horns</b> is owned by <b>Dirge</b>.
     *
     * @param owner
     */
    public void setOwner(ObjectPhrase owner) {
        this.owner = owner;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    @Override
    public String toString() {
        return "["
                + (hasDeterminer()? "determiner: " + determiner : "")
                + (hasAdjectives() ? " | adjectives: " + adjectives : "")
                + (hasNoun() ? " | noun: " + noun : "")
                + (hasBelongingPreposition() ?
                " | preposition: " + belongingPreposition : "")
                + (hasOwner() ? " | owner: " + owner : "")
                + "]";
    }
}
