package game.system.parsing.words;

/**
 * Represents a verb.
 * <p>
 * Must act upon an object {@link Noun}
 * <p>
 * May by acted by a subject {@link Noun} and/or be described by an
 * {@link Adjective}
 *
 * @author Evan Quan
 *
 */
public class Verb extends DescribableByAdverb {
    Noun subject;
    Noun object;
    Noun adjective;
}
