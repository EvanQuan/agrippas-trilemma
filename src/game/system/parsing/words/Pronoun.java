package game.system.parsing.words;

/**
 * Used in place of a {@link Noun} that has already been mentioned or that is
 * already known, often to avoid repeating the noun.
 * <p>
 * For example:<br>
 * - take <b>it</b><br>
 * - attack <b>him</b><br>
 * - give <b>her</b> the gold<br>
 *
 * @author Evan Quan
 *
 */
public class Pronoun {

    private Noun refersTo;

    public Noun getNoun() {
        return refersTo;
    }
}
