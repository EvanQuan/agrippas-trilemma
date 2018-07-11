package game.system.parsing.words;

/**
 * <b>Direct Object Phrase</b>
 * <p>
 * Conforms to the following grammar:
 * <p>
 * {@link Article}? {@link Adjective}* {@link Noun}
 *
 * NOTE: Currently this is an exact copy of the {@link ObjectPhrase}, but exists
 * because conceptually it doesn't make sense for IndirectObjectPhrase to
 * inherit from this.
 *
 * @author Evan Quan
 *
 */
public class DirectObjectPhrase extends ObjectPhrase {

    public DirectObjectPhrase(Noun noun) {
        super(noun);
    }

}
