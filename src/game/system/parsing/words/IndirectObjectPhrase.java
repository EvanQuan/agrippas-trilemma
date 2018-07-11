package game.system.parsing.words;

/**
 * <b>Indirect Object Phrase</b>
 * <p>
 * Conforms to the following grammar:
 * <p>
 * {@link Preposition} {@link Article}? {@link Adjective}* {@link Noun}
 *
 * NOTE: For now, the constructor only includes the Noun as the noun is the only
 * necessary component. Since the parser is likely to build the indirect object
 * phrase over time, the other words may be added over time after the
 * IndirectObjectPhrase has been instantiated.
 *
 * @author Evan Quan
 *
 */
public class IndirectObjectPhrase extends ObjectPhrase {

    private Preposition preposition;

    public IndirectObjectPhrase(Noun noun) {
        super(noun);
    }

    public Preposition getPreposition() {
        return this.preposition;
    }

    public void setPreposition(Preposition preposition) {
        this.preposition = preposition;
    }
}
