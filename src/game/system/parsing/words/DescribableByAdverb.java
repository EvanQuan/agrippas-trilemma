package game.system.parsing.words;

import java.util.ArrayList;

/**
 * <b>CURRENTLY UNUSED</b><br>
 * Indicates that a word can be described by an adverb
 *
 * @author Evan Quan
 *
 */
public abstract class DescribableByAdverb extends Word {

    Adverb describedBy;

    /**
     * Complete constructor
     *
     * @param name
     * @param describedBy
     */
    public DescribableByAdverb(String name, Adverb describedBy) {
        super(name);
        this.describedBy = describedBy;
    }

    /**
     * Get all adverbs that describe this word.
     *
     * @return
     */
    public ArrayList<Adverb> getAdverbs() {
        ArrayList<Adverb> totalAdverbs = new ArrayList<Adverb>();
        this.getAdverbsRecursively(totalAdverbs);

        return totalAdverbs;
    }

    /**
     * Recursively appends all {@link Adverb}s to totalAdverbs. If this word is not
     * described by an adverb, none are added to totalAdverbs and the recursively
     * search ends.
     *
     * @param totalAdverbs
     *            append adverbs to this
     */
    protected void getAdverbsRecursively(ArrayList<Adverb> totalAdverbs) {
        if (describedBy.hasAdverb()) {
            describedBy.getAdverbsRecursively(totalAdverbs);
            totalAdverbs.add(describedBy);
        }
    }

    /**
     *
     * @return true if this word is described by an adverb
     */
    public boolean hasAdverb() {
        return describedBy != null;
    }

}
