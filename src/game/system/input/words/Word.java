package game.system.input.words;

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
        Set.of("with", "about", "against")
    );

    /**
     * The first object phrase is being "verbed" from the player to the 2nd
     * object phrase.
     */
    public static final HashSet<String> MOVEMENT_PREPOSITIONS = new HashSet<>(
        Set.of("to", "at", "through")
    );

    /**
     * First object phrase is "owned by" or "belongs to" the second object
     * phrase.
     */
    public static final HashSet<String> BELONGING_PREPOSITIONS = new HashSet<>(
        Set.of("of")
    );

    /**
     * All prepositions used to separate object phrases.
     */
    public static final HashSet<String> OBJECT_PHRASE_SEPARATING_PREPOSITION
        = CollectionUtils.mergeSets(
            new Set[] {EXCLUDING_PREPOSITIONS, DIRECTIONAL_PREPOSITIONS,
                JOINING_PREPOSITIONS, MOVEMENT_PREPOSITIONS}
    );


    /**
     * These sort of determiners do not give information about the quantity
     * of objects they refer to.
     */
    public static final HashSet<String> GENERAL_ARTICLES = new HashSet<>(
        Set.of("the", "this", "that")
    );

    /**
     * These refer to objects in the player's possession.
     */
    public static final HashSet<String> PLAYER_ARTICLES = new HashSet<>(
        Set.of("my")
    );

    /**
     * For creating {@link PlayerCommand}s, all articles are treated the same.
     */
    public static final HashSet<String> ARTICLES = CollectionUtils.mergeSets(
        new Set[] {GENERAL_ARTICLES, PLAYER_ARTICLES}
    );

    /**
     * A type of determiner that quantifies the object phrase. While
     * numerical values directly quantify an object phrase (which is valid),
     * these quantifier words can also give a quantity as well.
     * Example:
     * all is used to signify all objects (in inventory, or room)
     * a/an is equivalent to 1
     * the is excluded because the noun in the object phrase determines the
     * quantity.
     */
    public static final HashSet<String> QUANTIFIERS = new HashSet<>(
        Set.of("a", "an", "all")
    );

    /**
     * Action verbs that have a direct object phrase attached directly to them,
     * but never an indirect object phrase.
     * Examples:
     * eat cake, take gold
     */
    public static final HashSet<String> NON_INDIRECT_TRANSITIVE_VERBS
        = new HashSet<>(
            Set.of("eat", "take", "get", "drop", "remove", "hit")
    );

    /**
     * Action verbs that have a direct object phrase attached directly to them,
     * and sometimes optionally an indirect object phrase.
     * Examples:
     * go (to) west, use key (on door)
     */
    public static final HashSet<String> OPTIONALLY_INDIRECT_TRANSITIVE_VERBS
        = new HashSet<>(
            Set.of("go", "use", "move", "walk", "run", "travel")

    );

    /**
     * Transitive verbs that REQUIRE an indirect object phrase for the
     * command to fully to make sense, although without an indirect object
     * phrase, it will still parse correctly.
     * Examples:
     * give gold to guard (give gold -> give gold to who?)
     */
    public static final HashSet<String> MANDATORY_INDIRECT_TRANSITIVE_VERBS
        = new HashSet<>(
            Set.of("give")

    );

    /**
     * Action verbs that cannot attach directly to a directly object phrase to
     * make complete sense. They need the help of an object phrase separating
     * preposition (and thus an indirect object phrase to make sense)
     * Examples:
     * Die, quit
     */
    public static final HashSet<String> INTRANSITIVE_VERBS = new HashSet<>(
        Set.of("die", "quit", "jump", "look", "examine")
    );

    public static final HashSet<String> VERBS =CollectionUtils.mergeSets(
            new Set[] {NON_INDIRECT_TRANSITIVE_VERBS,
                    OPTIONALLY_INDIRECT_TRANSITIVE_VERBS,
                    MANDATORY_INDIRECT_TRANSITIVE_VERBS,
                    INTRANSITIVE_VERBS
            }
    );

    /**
     * PlayerAction separators separates {@link PlayerCommand} playerActions.
     * These are used for receiveInput multi-playerAction stringCommands.
     */
    public static final HashSet<String> ACTION_SEPARATORS = new HashSet(
            Set.of(",", "and", "then", ".")
    );


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
        return ARTICLES.contains(word.toLowerCase());
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid
     * determiner, which is either an article or quantifier.
     */
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
        return BELONGING_PREPOSITIONS.contains(word.toLowerCase());
    }

    /**
     * All words that end with "ly" are treated as adverbs for parsing purposes.
     *
     * @param word
     * @return true if the specified word is recognized as valid adverb.
     */
    public static boolean isAdverb(String word) {
        return word.toLowerCase().endsWith("ly");
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid verb.
     */
    public static boolean isVerb(String word) {
        return VERBS.contains(word.toLowerCase());
    }

    public static boolean isNonIndirectTransitiveVerb(String word) {
        return NON_INDIRECT_TRANSITIVE_VERBS.contains(word.toLowerCase());
    }

    public static boolean isIndirectTransitiveVerb(String word) {
        return MANDATORY_INDIRECT_TRANSITIVE_VERBS.contains(word.toLowerCase());
    }

    public static boolean isOptionallyIndirectTransitiveVerb(String word) {
        return OPTIONALLY_INDIRECT_TRANSITIVE_VERBS.contains(word.toLowerCase());
    }

    public static boolean isIntransitiveVerb(String word) {
        return INTRANSITIVE_VERBS.contains(word.toLowerCase());
    }
}
