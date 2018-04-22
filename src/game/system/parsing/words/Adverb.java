package game.system.parsing.words;

/**
 * An adverb is a word that’s used to give information about a {@link Verb},
 * {@link Adjective}, or other Adverb.
 *
 * @author Evan Quan
 *
 */
public class Adverb extends DescribableByAdverb {

    private Word describes;

    public Word getDescribes() {
        return describes;
    }

    @Override
    public boolean hasAdverb() {
        // TODO Auto-generated method stub
        return false;
    }
}
