package game.system.input.words;

import game.object.item.background.character.Player;
import game.system.input.PlayerCommand;
import util.TextUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a word. A word must contain one or more alphanumeric characters.
 *
 * @author Evan Quan
 */
public abstract class Word {

    public static final HashSet<String> EXCLUDING_PREPOSITIONS = new HashSet<>(
            Set.of("but", "except"));
    public static final HashSet<String> PREPOSITIONS = new HashSet<>(Set.of(
            "over", "under", "on", "between", "behind", "in", "to", "with",
            "across"));

    public static final HashSet<String> BELONGING_PREPOSITIONS =
            new HashSet<>(Set.of("of"));

    public static final HashSet<String> ARTICLES = new HashSet<>(Set.of("the",
            "this", "that"));

    public static final HashSet<String> QUANTIFIERS = new HashSet<>(Set.of(
            "a", "all"));

    /**
     * PlayerAction separators separates {@link PlayerCommand} playerActions.
     * These are used for receiveInput multi-playerAction stringCommands.
     */
    public static final HashSet<String> ACTION_SEPARATORS = new HashSet(Set.of
            (",", "then", "."));

    /**
     * Robust {@link Player}
     */
//    public static final HashSet<String> VERBS;

    /**
     * @param word
     * @return true if the specified word is recognized as a valid action
     * separator.
     */
    public static boolean isActionSeparator(String word) {
        return ACTION_SEPARATORS.contains(word);
    }

    /**
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
        return PREPOSITIONS.contains(word)
                || EXCLUDING_PREPOSITIONS.contains(word);
    }

    /**
     * @param word
     * @return
     */
    public static boolean isQuantifier(String word) {
        return QUANTIFIERS.contains(word) || TextUtils.isInteger(word);
    }

    /**
     * This is used for separating object phrases from their owners.
     *
     * @param word
     * @return true if the specified word is recognized as a valid belonging
     * preposition.
     */
    public static boolean isBelongingPreposition(String word) {
        return BELONGING_PREPOSITIONS.contains(word);
    }

    /**
     * TODO. This is for resolving the indeterminism of receiveInput
     * multi-playerAction stringCommands.
     *
     * @param word
     * @return false, until actually implemented
     */
    public static boolean isVerb(String word) {
        return false;
    }

}
