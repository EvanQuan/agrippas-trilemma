package game.system.input.words;

import util.CollectionUtils;
import util.FuncUtils;

import java.util.ArrayList;

/**
 * Object phrases describe an object, or noun. They conform to the following
 * grammar:
 * <br><br>
 * Determiner? Adjective* Noun? (preposition ObjectPhrase?)?
 *
 * @author Evan Quan
 */
public class ObjectPhrase {

    private String determiner;
    private ArrayList<String> adjectives;
    private String noun;
    private String belongingPreposition;
    private ObjectPhrase owner;

    /**
     * Default constructor. Initializes empty adjectives array. All other
     * fields are empty.
     */
    public ObjectPhrase() {
        this.adjectives = new ArrayList<>();
    }

    /**
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
     *
     * @return this object phrase's adjectives.
     */
    public ArrayList<String> getAdjectives() {
        return this.adjectives;
    }

    /**
     *
     * @return this object phrase's determiner.
     */
    public String getDeterminer() {
        return this.determiner;
    }

    /**
     *
     * @return this object phrase's noun.
     */
    public String getNoun() {
        return this.noun;
    }

    /**
     *
     * @return true if this object phrase has adjectives.
     */
    public boolean hasAdjectives() {
        return !this.adjectives.isEmpty();
    }

    /**
     *
     * @return this object phrase's preposition.
     */
    public String getBelongingPreposition() {
        return this.belongingPreposition;
    }

    /**
     *
     * @return this object phrase's owner.
     */
    public ObjectPhrase getOwner() {
        return this.owner;
    }

    /**
     *
     * @return true if this object phrase has a determiner.
     */
    public boolean hasDeterminer() {
        return this.determiner != null;
    }

    /**
     *
     * @return true if this object phrase has a noun.
     */
    public boolean hasNoun() {
        return this.noun != null;
    }

    /**
     *
     * @return true if this object phrase has a preposition.
     */
    public boolean hasBelongingPreposition() {
        return this.belongingPreposition != null;
    }

    /**
     *
     * @return true if this object phrase has an owner.
     */
    public boolean hasOwner() {
        return this.owner != null;
    }

    /**
     *
     * @param other to compare
     * @return true if both object phrases have the same adjectives.
     */
    public boolean hasSameAdjectives(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.adjectives, other.getAdjectives());
    }

    /**
     *
     * @param other to compare
     * @return true if both object phrases have the same determiner.
     */
    public boolean hasSameDeterminer(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.determiner, other.getDeterminer());
    }

    /**
     *
     * @param other to compare
     * @return true if both object phrases have the same noun.
     */
    public boolean hasSameNoun(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.noun, other.getNoun());
    }

    /**
     *
     * @param other to compare
     * @return true if both object phrases have the same preposition.
     */
    public boolean hasSamePreposition(ObjectPhrase other) {
        return FuncUtils.nullablesEqual(this.belongingPreposition,
                other.getBelongingPreposition());
    }

    /**
     *
     * @param other to compare
     * @return true if both object phrases have the same owner.
     */
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

    /**
     *
     * @param adjectives to set
     */
    public void setAdjectives(ArrayList<String> adjectives) {
        this.adjectives = adjectives;
    }

    /**
     *
     * @param adjectives to set
     */
    public void setAdjectives(String[] adjectives) {
        this.adjectives = CollectionUtils.getArrayList(adjectives);
    }

    /**
     *
     * @param determiner to set
     */
    public void setDeterminer(String determiner) {
        this.determiner = determiner;
    }

    /**
     * Use {@link Word}.isBelongingPreposition() to find valid prepositions.
     *
     * @param belongingPreposition to set
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
     * @param owner to set
     */
    public void setOwner(ObjectPhrase owner) {
        this.owner = owner;
    }

    /**
     * A noun is a person, place or thing.
     *
     * @param noun to set
     */
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
