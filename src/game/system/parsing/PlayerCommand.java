package game.system.parsing;

import game.system.parsing.words.*;

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
    /**
     * Creates a Player Command instance with a verb phrase.
     *
     * @param string
     * @param verbPhrase
     */
    public PlayerCommand(String string, String verbPhrase) {
        this.string = string;
        this.verbPhrase = verbPhrase;
        this.directObjectPhrase = null;
        this.indirectObjectPhrase = null;
    }

    /**
     * Creates a Player Command instance with a verb, and direct object phrase.
     *
     * @param string
     * @param verbPhrase
     * @param directObjectPhrase
     */
    public PlayerCommand(String string, String verbPhrase, ObjectPhrase directObjectPhrase) {
        this.string = string;
        this.verbPhrase = verbPhrase;
        this.directObjectPhrase = directObjectPhrase;
        this.indirectObjectPhrase = null;
    }

    /**
     * Creates a Player Command instance with a verb, direct, and indirect object
     * phrase.
     *
     * @param string
     * @param verbPhrase
     * @param directObjectPhrase
     * @param indirectObjectPhrase
     */
    public PlayerCommand(String string, String verbPhrase, ObjectPhrase directObjectPhrase,
                         String preposition, ObjectPhrase indirectObjectPhrase) {
        this.string = string;
        this.verbPhrase = verbPhrase;
        this.directObjectPhrase = directObjectPhrase;
        this.preposition = preposition;
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    /**
     * Creates a Player Command instance with a verb, and indirect object phrase.
     *
     * @param string
     * @param verbPhrase
     * @param indirectObjectPhrase
     */
    public PlayerCommand(String string, String verbPhrase, String preposition,
                         ObjectPhrase indirectObjectPhrase) {
        this.string = string;
        this.verbPhrase = verbPhrase;
        this.directObjectPhrase = null;
        this.preposition = preposition;
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    /**
     *
     * @return true if this command has a {@link ObjectPhrase}
     */
    public boolean hasDirectObjectPhrase() {
        return this.directObjectPhrase != null;
    }

    /**
     *
     * @return true if this command has an {@link ObjectPhrase}
     */
    public boolean hasIndirectObjectPhrase() {
        return this.indirectObjectPhrase != null;
    }

    public boolean hasPreposition() {
        return  this.preposition != null;
    }

    public ObjectPhrase getDirectObjectPhrase() {
        return this.directObjectPhrase;
    }

    public ObjectPhrase getIndirectObjectPhrase() {
        return this.indirectObjectPhrase;
    }

    public void setVerbPhrase(String verbPhrase) {
        this.verbPhrase = verbPhrase;
    }

    public void setDirectObjectPhrase(ObjectPhrase directObjectPhrase){
        this.directObjectPhrase = directObjectPhrase;
    }

    public void setIndirectObjectPhrase(ObjectPhrase indirectObjectPhrase) {
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    public String getVerbPhrase() {
        return this.verbPhrase;
    }

    public String getPreposition() {
        return this.preposition;
    }

    @Override
    public String toString() {
        return this.string;
    }

}
