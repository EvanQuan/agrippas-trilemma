package game.system.parsing.words;

/**
 * Refer to something owned by someone or something previously mentioned.
 * <p>
 * For example:<br>
 * - mine<br>
 * - hers<br>
 * - ours<br>
 *
 * @author Evan Quan
 *
 */
public class PossessivePronoun extends Pronoun {

    /**
     * Complete constructor
     *
     * @param name
     * @param refersTo
     */
    public PossessivePronoun(String name, Noun refersTo) {
        super(name, refersTo);
    }

}
