package game.system.parsing;

import game.system.parsing.words.ObjectPhrase;
import util.FuncUtils;

/**
 * Represents an action for the player to do. Confirms to the following grammar:
 * <p>
 * Verb? ObjectPhrase? Preposition? ObjectPhrase?
 * <p>
 *
 * @author Evan Quan
 *
 */
public class Action {

    private String verbPhrase;
    private ObjectPhrase directObjectPhrase;
    private String preposition;
    private ObjectPhrase indirectObjectPhrase;

    @Override
    public boolean equals(Object other) {
        if (other instanceof Action) {
            return hasSameVerbPhrase((Action) other) && hasSameDirectObjectPhrase((Action) other)
                    && hasSamePreposition((Action) other) && hasSameIndirectObjectPhrase((Action) other);
        }
        return false;
    }

    public ObjectPhrase getDirectObjectPhrase() {
        return this.directObjectPhrase;
    }

    public ObjectPhrase getIndirectObjectPhrase() {
        return this.indirectObjectPhrase;
    }

    public String getPreposition() {
        return this.preposition;
    }

    public String getVerbPhrase() {
        return this.verbPhrase;
    }

    /**
     *
     * @return true if this command has a direct {@link ObjectPhrase}
     */
    public boolean hasDirectObjectPhrase() {
        return this.directObjectPhrase != null && !this.directObjectPhrase.isEmpty();
    }

    /**
     *
     * @return true if this command has an indirect {@link ObjectPhrase}
     */
    public boolean hasIndirectObjectPhrase() {
        return this.indirectObjectPhrase != null && !this.indirectObjectPhrase.isEmpty();
    }

    public boolean hasPreposition() {
        return this.preposition != null;
    }

    public boolean hasSameDirectObjectPhrase(Action other) {
        return FuncUtils.nullablesEqual(this.directObjectPhrase, other.getDirectObjectPhrase());
    }

    public boolean hasSameIndirectObjectPhrase(Action other) {
        return FuncUtils.nullablesEqual(this.indirectObjectPhrase, other.getIndirectObjectPhrase());
    }

    public boolean hasSamePreposition(Action other) {
        return FuncUtils.nullablesEqual(this.preposition, other.getPreposition());
    }

    public boolean hasSameVerbPhrase(Action other) {
        return FuncUtils.nullablesEqual(this.verbPhrase, other.getVerbPhrase());
    }

    public boolean hasVerbPhrase() {
        return this.verbPhrase != null;
    }

    public boolean isEmpty() {
        return !hasVerbPhrase() && !hasDirectObjectPhrase() && !hasPreposition() && !hasIndirectObjectPhrase();
    }

    public void setDirectObjectPhrase(ObjectPhrase directObjectPhrase) {
        this.directObjectPhrase = directObjectPhrase;
    }

    public void setIndirectObjectPhrase(ObjectPhrase indirectObjectPhrase) {
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    public void setVerbPhrase(String verbPhrase) {
        this.verbPhrase = verbPhrase;
    }

    @Override
    public String toString() {
        return "[verbPhrase: " + verbPhrase + " | directObjectPhrase: " + directObjectPhrase + " | preposition: "
                + preposition + " | indirectObjectPhrase: " + indirectObjectPhrase + "]";
    }
}
