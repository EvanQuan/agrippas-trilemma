package game.system.parsing.words;

import java.util.HashSet;
import java.util.Arrays;

/**
 * Represents a preposition.
 * <p>
 * Must relate to an object {@link Noun}.
 * <p>
 * May relate a subject {@link Noun}.
 * <p>
 * <code>
 * [verb] [subject] under [object]
 * </code>
 *
 * @author Evan Quan
 *
 */
@Deprecated
public class Preposition extends Word {

    // TODO after more. Valid ones that are actually used in commands, and invalid ones for better error prompts.
    public static final HashSet<String> VALID_LIST = new HashSet<>(Arrays.asList(new String[] {"over", "under", "on", "between", "behind", "in", "to", "with", "across"}));

    Noun subject;
    Noun object;

    public static boolean isValid(String word) {
        return VALID_LIST.contains(word);
    }

    public Preposition(String name) {
        super(name);
    }

}
