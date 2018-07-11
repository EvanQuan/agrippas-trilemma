package game.system.parsing;

import game.system.parsing.words.DirectObjectPhrase;
import game.system.parsing.words.IndirectObjectPhrase;
import game.system.parsing.words.Verb;

/**
 * Represents a command that the player has issued to the game. A player command
 * conforms to the following grammar:
 * <p>
 * {@link Verb} {@link DirectObjectPhrase}? {@link IndirectObjectPhrase}?
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

    private Verb verbPhrase;
    private DirectObjectPhrase directObjectPhrase;
    private IndirectObjectPhrase indirectObjectPhrase;

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
    public PlayerCommand(String string, Verb verbPhrase) {
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
    public PlayerCommand(String string, Verb verbPhrase, DirectObjectPhrase directObjectPhrase) {
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
    public PlayerCommand(String string, Verb verbPhrase, DirectObjectPhrase directObjectPhrase, IndirectObjectPhrase indirectObjectPhrase) {
        this.string = string;
        this.verbPhrase = verbPhrase;
        this.directObjectPhrase = directObjectPhrase;
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    /**
     * Creates a Player Command instance with a verb, and indirect object phrase.
     *
     * @param string
     * @param verbPhrase
     * @param indirectObjectPhrase
     */
    public PlayerCommand(String string, Verb verbPhrase, IndirectObjectPhrase indirectObjectPhrase) {
        this.string = string;
        this.verbPhrase = verbPhrase;
        this.directObjectPhrase = null;
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    /**
     *
     * @return true if this command has a {@link DirectObjectPhrase}
     */
    public boolean hasDirectObjectPhrase() {
        return this.directObjectPhrase != null;
    }

    /**
     *
     * @return true if this command has an {@link IndirectObjectPhrase}
     */
    public boolean hasIndirectObjectPhrase() {
        return this.indirectObjectPhrase != null;
    }

    public void setVerbPhrase(Verb verbPhrase) {
        this.verbPhrase = verbPhrase;
    }

    public void setDirectObjectPhrase(DirectObjectPhrase directObjectPhrase){
        this.directObjectPhrase = directObjectPhrase;
    }

    public void setIndirectObjectPhrase(IndirectObjectPhrase indirectObjectPhrase) {
        this.indirectObjectPhrase = indirectObjectPhrase;
    }

    @Override
    public String toString() {
        return this.string;
    }

}
