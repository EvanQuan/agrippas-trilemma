package game.system.parsing.words;

import java.util.ArrayList;

import util.ArrayUtils;
import util.FuncUtils;

/**
 * Object phrases describe an object, or Noun. They conform to the following
 * grammar:
 * <p>
 * Determiner? Adjective* Noun
 *
 * @author Evan Quan
 *
 */
public class ObjectPhrase {

    private String determiner;
    private ArrayList<String> adjectives;
    private String noun;

    public ObjectPhrase() {
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
        if (other instanceof ObjectPhrase) {
            return hasSameDeterminer((ObjectPhrase) other) && hasSameAdjectives((ObjectPhrase) other)
                    && hasSameNoun((ObjectPhrase) other);
        }
        return false;
    }

    /**
     *
     * @return a shallow copy of this phrase's adjectives. This is so that the
     *         adjective list can be modified for parsing purposes if need be
     *         without actually affecting the actual adjectives.<br>
     *         null if adjectives is null. NOTE: Due to current
     *         PlayerCommandParser.getObjectPhrase() implementation, adjectives will
     *         never be null, as an ObjectPhrase with no adjectives will have an
     *         empty ArrayList
     */
    public ArrayList<String> getAdjectives() {
        return this.adjectives == null ? null : new ArrayList<>(this.adjectives);
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

    public boolean hasDeterminer() {
        return this.determiner != null;
    }

    public boolean hasNoun() {
        return this.noun != null;
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

    /**
     *
     * @return true if this object phrase does not have a determiner, adjectives and
     *         noun.
     */
    public boolean isEmpty() {
        return !hasDeterminer() && !hasAdjectives() && !hasNoun();
    }

    public void setAdjectives(ArrayList<String> adjectives) {
        this.adjectives = adjectives;
    }

    public void setAdjectives(String[] adjectives) {
        this.adjectives = ArrayUtils.getStringArrayList(adjectives);
    }

    public void setDeterminer(String determiner) {
        this.determiner = determiner;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    @Override
    public String toString() {
        return "[determiner: " + determiner + " | adjectives: " + adjectives + " | noun: " + noun + "]";
    }
}
