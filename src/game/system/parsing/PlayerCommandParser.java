package game.system.parsing;

import java.util.ArrayList;

import game.system.parsing.words.Noun;
import game.system.parsing.words.Verb;
import game.system.parsing.words.Word;

/**
 * Parses a string into a {@link PlayerCommand}
 *
 * @author Evan Quan
 *
 */
public abstract class PlayerCommandParser {

    /**
     * TODO Creates a player command from a list of words. Depending on the relation
     * between words, the action {@link Verb} and object {@link Noun} are
     * determined.
     *
     * @param words
     * @return
     */
    private PlayerCommand createPlayerCommand(String command, ArrayList<Word> words) {
        Verb action = new Verb();
        Noun object = new Noun();
        return new PlayerCommand(command, action, object);
    }

    /**
     * TODO Splits the input string into tokens, each representing a word of the
     * command.
     *
     * @param input
     *            - command String
     * @return list of all words and their associated relations to one another. The
     *         order of the tokens are preserved from that of the input string.
     */
    private ArrayList<Word> createWords(String command) {
        ArrayList<Word> words = new ArrayList<Word>();

        return words;
    }

    /**
     * Parse input text into words and apply their appropriate meanings and
     * relationships. Accepts imperative statements.
     *
     * @param command
     *            - String to parse into words
     * @return command that represents the player {@link PlayerCommand}
     */
    public PlayerCommand parse(String command) {
        // words contains all words. It should contain all tokens in the same order as
        // the input string
        ArrayList<Word> words = createWords(command);
        // Command is used to tell game what to do
        PlayerCommand playerCommand = createPlayerCommand(command, words);
        return playerCommand;
    }
}
