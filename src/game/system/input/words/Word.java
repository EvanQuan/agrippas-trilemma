package game.system.input.words;

import game.object.item.background.character.Player;
import game.system.input.PlayerCommand;
import util.CollectionUtils;
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
            Set.of("but", "except")
    );

    /**
     * Situate one object phrase directionally in relation to another object
     * phrase
     */
    public static final HashSet<String> DIRECTIONAL_PREPOSITIONS = new HashSet<>(
            Set.of("over", "under", "on", "between", "behind", "in", "across")
    );

    /**
     * Connect 2 object phrases together (in some sense)
     */
    public static final HashSet<String> JOINING_PREPOSITIONS = new HashSet<>(
            Set.of("with")
    );

    /**
     * The first object phrase is being "verbed" from the player to the 2nd
     * object phrase.
     */
    public static final HashSet<String> MOVEMENT_PREPOSITIONS = new HashSet<>(
            Set.of("to")
    );

    /**
     * First object phrase is "owned by" or "belongs to" the second object
     * phrase.
     */
    public static final HashSet<String> BELONGING_PREPOSITIONS =
            new HashSet<>(Set.of("of")
    );

    /**
     * All prepositions used to separate object phrases.
     */
    public static final HashSet<String> OBJECT_PHRASE_SEPARATING_PREPOSITION
        = CollectionUtils.mergeSets(new Set[] {EXCLUDING_PREPOSITIONS,
            DIRECTIONAL_PREPOSITIONS, JOINING_PREPOSITIONS,
            MOVEMENT_PREPOSITIONS});


    public static final HashSet<String> ARTICLES = new HashSet<>(Set.of("the",
            "this", "that")
    );

    public static final HashSet<String> PLAYER_ARTICLES = new HashSet<>(
            Set.of("my")
    );

    public static final HashSet<String> QUANTIFIERS = new HashSet<>(
            Set.of("a", "all")
    );

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
        return ACTION_SEPARATORS.contains(word.toLowerCase());
    }

    /**
     * @param word
     * @return true if the specified word is recognized as a valid article.
     */
    public static boolean isArticle(String word) {
        return ARTICLES.contains(word.toLowerCase())
                || PLAYER_ARTICLES.contains(word.toLowerCase());
    }

    public static boolean isDeterminer(String word) {
        return isArticle(word.toLowerCase()) || isQuantifier(word.toLowerCase());
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid
     * directional preposition.
     */
    public static boolean isDirectionalPreposition(String word) {
        return DIRECTIONAL_PREPOSITIONS.contains(word.toLowerCase());
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid
     * joining preposition.
     */
    public static boolean isJoiningPreposition(String word) {
        return JOINING_PREPOSITIONS.contains(word.toLowerCase());
    }

    /**
     * @param word
     * @return true if the specified word is recognized as a valid
     * movement preposition.
     */
    public static boolean isMovementPreposition(String word) {
        return MOVEMENT_PREPOSITIONS.contains(word.toLowerCase());
    }

    /**
     * These types of prepositions signify separating direct and indirect
     * object phrases. This excludes belonging prepositions.
     *
     * @param word
     * @return true if the specified word is recognized as a valid preposition
     * for separating object phrases.
     */
    public static boolean isObjectPhraseSeparatingPreposition(String word) {
        return OBJECT_PHRASE_SEPARATING_PREPOSITION.contains(word.toLowerCase());
    }

    /**
     * @param word
     * @return
     */
    public static boolean isQuantifier(String word) {
        return QUANTIFIERS.contains(word.toLowerCase())
                || TextUtils.isInteger(word.toLowerCase());
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
