package game.system.parsing.words;

/**
 * A determiner is a word that introduces a {@link Noun}. They may be an
 * {@link Article}, which identify a specific noun, or a {@link Quantifier}
 * which specifies a quantity of nouns.
 *
 * @author Evan Quan
 *
 */
public abstract class Determiner extends Word {

    Noun noun;

    public Determiner(String name, Noun noun) {
        super(name);
        this.noun = noun;
    }

    public Noun getNoun() {
        return noun;
    }
}
