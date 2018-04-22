package game.system.parsing.words;

/**
 * A determiner is a word that introduces a {@link Noun}.
 * <p>
 * - a/an <br>
 * - the <br>
 * - every <br>
 * - this <br>
 * - those <br>
 * - many <br>
 *
 * @author Evan Quan
 *
 */
public class Determiner extends Word {

    Noun noun;

    public Noun getNoun() {
        return noun;
    }
}
