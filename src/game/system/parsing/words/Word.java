package game.system.parsing.words;

import java.util.HashSet;

import game.object.item.background.character.Player;
import game.system.parsing.PlayerCommand;
import util.ArrayUtils;
import util.TextUtils;

/**
 * Represents a word. A word must contain one or more alphanumeric characters.
 *
 * @author Evan Quan
 *
 */
public abstract class Word {

    public static final HashSet<String> EXCLUDING_PREPOSITIONS = ArrayUtils
            .getHashSet(new String[] { "but", "except" });
    public static final HashSet<String> PREPOSITIONS = ArrayUtils
            .getHashSet(new String[] { "over", "under", "on", "between", "behind", "in", "to", "with", "across" });

    public static final HashSet<String> ARTICLES = ArrayUtils.getHashSet(new String[] { "the", "this", "that" });

    public static final HashSet<String> QUANTIFIERS = ArrayUtils.getHashSet(new String[] { "a", "all" });

    /**
     * Action separators separates {@link PlayerCommand} actions. These are used for
     * parsing multi-action commands.
     */
    public static final HashSet<String> ACTION_SEPARATORS = ArrayUtils.getHashSet(new String[] { ",", "then", "." });

    /**
     * Robust {@link Player}
     */
    public static final HashSet<String> VERBS = ArrayUtils.getHashSet(new String[] { "go" });

    public static boolean isActionSeparator(String word) {
        return ACTION_SEPARATORS.contains(word);
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid article.
     */
    public static boolean isArticle(String word) {
        return ARTICLES.contains(word);
    }

    public static boolean isDeterminer(String word) {
        return isArticle(word) || isQuantifier(word);
    }

    /**
     * This is used for separating direct and indirect object phrases.
     *
     * @param word
     * @return true if the specified word is recognized as a valid preposition.
     */
    public static boolean isPreposition(String word) {
        return PREPOSITIONS.contains(word) || EXCLUDING_PREPOSITIONS.contains(word);
    }

    /**
     *
     * @param word
     * @return
     */
    public static boolean isQuantifier(String word) {
        return QUANTIFIERS.contains(word) || TextUtils.isInteger(word);
    }

    /**
     * TODO. This is for resolving the indeterminism of parsing multi-action
     * commands.
     *
     * @param word
     * @return false, until actually implemented
     */
    public static boolean isVerb(String word) {
        return false;
    }

}
