package game.system.parsing.words;

/**
 * <b>CURRENTLY UNUSED</b>
 * <p>
 * An adverb is a word that’s used to give information about a {@link Verb},
 * {@link Adjective}, or other Adverb.
 *
 * @author Evan Quan
 *
 */
public class Adverb extends DescribableByAdverb {

    public static final String[] valid = { "quickly", "slowly", "immediately" };

    private DescribableByAdverb describes;

    /**
     *
     * @param name
     * @param valid
     * @param describedBy
     * @param describes
     */
    public Adverb(String name, Adverb describedBy, DescribableByAdverb describes) {
        super(name, describedBy);
        this.describes = describes;
    }

    public Word getDescribes() {
        return describes;
    }
}
