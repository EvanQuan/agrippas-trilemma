package game.system.parsing.words;

import java.util.ArrayList;

/**
 * Object phrases describe an object, or {@link Noun}. They conform to the
 * following grammar:
 * <p>
 * {@link Article}? {@link Adjective}* {@link Noun}
 *
 * @author Evan Quan
 *
 */
public class ObjectPhrase {

    private Article article;
    private ArrayList<Adjective> adjectives;
    private Noun noun;

    public ObjectPhrase(Noun noun) {
        adjectives = new ArrayList<Adjective>();
        this.noun = noun;
    }

    public ArrayList<Adjective> getAdjectives() {
        return this.adjectives;
    }

    public Article getArticle() {
        return this.article;
    }

    public Noun getNoun() {
        return this.noun;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
