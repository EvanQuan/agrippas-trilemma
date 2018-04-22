package game.system.parsing.words;

/**
 * Represents a preposition.
 * <p>
 * Must relate to an object {@link Noun}.
 * <p>
 * May relate a subject {@link Noun}.
 * <p>
 * <code>
 * Put [subject] under [object]
 * </code>
 *
 * @author Evan Quan
 *
 */
public class Preposition extends Word {
    Noun subject;
    Noun object;

}
