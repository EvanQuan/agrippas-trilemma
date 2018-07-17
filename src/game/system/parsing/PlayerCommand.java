package game.system.parsing;

import game.system.parsing.words.ObjectPhrase;
import util.FuncUtils;

/**
 * Represents a command that the player has issued to the game. A player command
 * conforms to the following grammar:
 * <p>
 * Verb ObjectPhrase? Preposition? ObjectPhrase?
 * <p>
 * Note: normally, for most commands, there is only a single direct/indirect
 * object phrase pairing, but in the future, there may be multiple to allow for
 * multiple commands to be issued at once that share a single verb.
 *
 * @author Evan Quan
 *
 */
public class PlayerCommand {

    /**
     * Represents the player command directly as a string, unaltered.
     */
    private String string;

    private String verbPhrase;
    private ObjectPhrase directObjectPhrase;
    private String preposition;
    private ObjectPhrase indirectObjectPhrase;

    /**
     * Creates a Player Command instance.
     *
     * @param string
     */
    public PlayerCommand(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PlayerCommand) {
            return hasSameString((PlayerCommand) other) && hasSameVerbPhrase((PlayerCommand) other)
                    && hasSameDirectObjectPhrase((PlayerCommand) other) && hasSamePreposition((PlayerCommand) other)
                    && hasSameIndirectObjectPhrase((PlayerCommand) other);
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

    public String getString() {
        return this.string;
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

    public boolean hasSameDirectObjectPhrase(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.directObjectPhrase, other.getDirectObjectPhrase());
    }

    public boolean hasSameIndirectObjectPhrase(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.indirectObjectPhrase, other.getIndirectObjectPhrase());
    }

    public boolean hasSamePreposition(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.preposition, other.getPreposition());
    }

    public boolean hasSameString(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.string, other.getString());
    }

    public boolean hasSameVerbPhrase(PlayerCommand other) {
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
