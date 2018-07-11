package game.system.parsing.words;

/**
 * Quantifies a noun.
 *
 * @author Evan Quan
 *
 */
public class Quantifier extends Determiner {

    Number count;

    /**
     * Complete constructor
     *
     * @param name
     * @param valid
     * @param noun
     * @param count
     */
    public Quantifier(String name, Noun noun, Number count) {
        super(name, noun);
        this.count = count;
    }

    public Number getCount() {
        return count;
    }

}
