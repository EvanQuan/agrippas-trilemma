package game.system.parsing.words;

import java.util.ArrayList;

/**
 * Object phrases describe an object, or Noun. They conform to the
 * following grammar:
 * <p>
 * Article? Adjective* Noun
 *
 * @author Evan Quan
 *
 */
public class ObjectPhrase {

    private String article;
    private ArrayList<String> adjectives;
    private String noun;

    public ObjectPhrase() {
    }

    public void setAdjectives(ArrayList<String> adjectives) {
        this.adjectives = adjectives;
    }

    /**
     *
     * @return a shallow copy of this phrase's adjectives. This is so that the adjective list can
     * be modified for parsing purposes if need be without actually affecting the actual adjectives.
     */
    public ArrayList<String> getAdjectives() {
        return new ArrayList<>(this.adjectives);
    }

    public String getArticle() {
        return this.article;
    }

    public String getNoun() {
        return this.noun;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }
}
