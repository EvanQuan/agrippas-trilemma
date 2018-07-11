package game.system.parsing.words;

/**
 * Describes a noun, giving extra information about it.
 * <p>
 * For example:<br>
 * - an <b>exciting</b> adventure<br>
 * - a <b>green</b> apple<br>
 * - a <b>tidy</b> room
 *
 * @author Evan Quan
 *
 */
public class Adjective extends DescribableByAdverb {

    Noun describes;

    /**
     * Complete constructor
     *
     * @param name
     * @param valid
     * @param describedBy
     * @param describes
     */
    public Adjective(String name, Adverb describedBy, Noun describes) {
        super(name, describedBy);
        this.describes = describes;
    }

    /**
     *
     * @return the noun that this adjective describes
     */
    public Noun getNoun() {
        return describes;
    }

    /**
     *
     * @param noun
     *            - the noun that this adjective describes
     */
    public void setNoun(Noun noun) {
        this.describes = noun;
    }

}
