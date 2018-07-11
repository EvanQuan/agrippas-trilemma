package game.system.parsing.words;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Determines a specific noun
 * <p>
 * There is only 1 article in English:<br>
 * - the <br>
 * Demonstratives will count as Articles for the purposes of this game:<br>
 * - this<br>
 * - that
 *
 * @author Evan Quan
 *
 */
public class Article extends Determiner {

    public static final HashSet<String> VALID_LIST = new HashSet<>(Arrays.asList(new String[] {"the", "this", "that"}));

    public static boolean isValid(String word) {
        return VALID_LIST.contains(word);
    }
    /**
     * Complete constructor
     *
     * @param name
     * @param noun
     */
    public Article(String name, Noun noun) {
        super(name, noun);
        // TODO Auto-generated constructor stub
    }

}
