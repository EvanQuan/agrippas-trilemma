package game.system.parsing.words;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents a word. A word must contain one or more alphanumeric characters.
 *
 * @author Evan Quan
 *
 */
public abstract class Word {

    public static final HashSet<String> PREPOSITIONS = new HashSet<>(Arrays.asList(new
            String[]
            {"over", "under", "on", "between", "behind", "in", "to", "with", "across"}));

    public static final HashSet<String> ARTICLES = new HashSet<>(Arrays.asList(new
            String[]
            {"the", "this", "that"}));

    private String name;

    /**
     * Complete constructor. Cannot be instantiated.
     *
     * @param name
     *            - Determines if the word is valid. This is determined by a
     *            combination of the word type and name, and its relation with other
     *            words.
     */
    public Word(String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of this word
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return String representation of word
     */
    public String toString() {
        return name;
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid preposition.
     */
    public static boolean isPreposition(String word) {
        return PREPOSITIONS.contains(word);
    }

    /**
     *
     * @param word
     * @return true if the specified word is recognized as a valid article.
     */
    public static boolean isArticle(String word) {
        return ARTICLES.contains(word);
    }
}
