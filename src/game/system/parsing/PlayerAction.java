package game.system.parsing;

import game.system.parsing.words.ObjectPhrase;
import game.system.parsing.words.VerbPhrase;
import util.FuncUtils;

/**
 * Represents a single action for the player to do. A {@link PlayerCommand} may be composed of multiple actions.
 * Confirms to the following grammar:
 * <p>
 * Verb? ObjectPhrase? Preposition? ObjectPhrase?
 * <p>
 *
 * @author Evan Quan
 *
 */
public class PlayerAction {

    private VerbPhrase verbPhrase;
    private ObjectPhrase directObjectPhrase;
    private String preposition;
    private ObjectPhrase indirectObjectPhrase;

    @Override
    public boolean equals(Object other) {
        if (other instanceof PlayerAction) {
            return hasSameVerbPhrase((PlayerAction) other) && hasSameDirectObjectPhrase((PlayerAction) other)
                    && hasSamePreposition((PlayerAction) other) && hasSameIndirectObjectPhrase((PlayerAction) other);
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

    public VerbPhrase getVerbPhrase() {
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

    public boolean hasSameDirectObjectPhrase(PlayerAction other) {
        return FuncUtils.nullablesEqual(this.directObjectPhrase, other.getDirectObjectPhrase());
    }

    public boolean hasSameIndirectObjectPhrase(PlayerAction other) {
        return FuncUtils.nullablesEqual(this.indirectObjectPhrase, other.getIndirectObjectPhrase());
    }

    public boolean hasSamePreposition(PlayerAction other) {
        return FuncUtils.nullablesEqual(this.preposition, other.getPreposition());
    }

    public boolean hasSameVerbPhrase(PlayerAction other) {
        return FuncUtils.nullablesEqual(this.verbPhrase, other.getVerbPhrase());
    }

    /**
     *
     * @return true if this command has a {@link VerbPhrase}
     */
    public boolean hasVerbPhrase() {
        return this.verbPhrase != null && !this.verbPhrase.isEmpty();
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

    public void setVerbPhrase(VerbPhrase verbPhrase) {
        this.verbPhrase = verbPhrase;
    }

    /**
     * Set the verbPhrase to a new VerbPhrase with the corresponding verb.
     *
     * @param verb
     */
    public void setVerbPhrase(String verb) {
        this.verbPhrase = new VerbPhrase(verb);
    }

    @Override
    public String toString() {
        return "[verbPhrase: " + verbPhrase + " | directObjectPhrase: " + directObjectPhrase + " | preposition: "
                + preposition + " | indirectObjectPhrase: " + indirectObjectPhrase + "]";
    }
}
