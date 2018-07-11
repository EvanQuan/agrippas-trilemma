package game.system.parsing.words;

/**
 * Used to connect phrases, clauses, and sentences.
 * <p>
 * For example:<br>
 * - and<br>
 * - because<br>
 * - but<br>
 * - for<br>
 * - if<br>
 * - or<br>
 * - when<br>
 * - except<br>
 *
 * @author Evan Quan
 *
 */
public class Conjunction extends Word {

    public static final String[] valid = { "and", "but", "except" };

    public Conjunction(String name) {
        super(name);
    }

}
