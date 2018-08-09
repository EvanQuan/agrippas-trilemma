package game.system.input;

import game.system.input.words.ObjectPhrase;
import game.system.input.words.VerbPhrase;
import game.system.input.words.Word;
import util.CollectionUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parses an input string into a {@link PlayerCommand}. The parser abides
 * by the following grammar rules:
 * <p>
 * 1. The dictionary of all possible verbs, adjectives, direct objects, and
 * indirect objects is not known.<br> - The game handles the validity of these
 * words, not the parser.<br> 2. The first word of the input is always a
 * verb.<br> - Player stringCommands are 2nd person imperative statements.<br>
 * 3. Indirect object phrases are always preceded by a preposition.<br> 4.
 * Direct object phrases are always positioned before indirect object
 * phrases.<br> 5. The dictionary of all possible prepositions is known.<br> 6.
 * The dictionary of all possible determiners is known.<br> 7. The dictionary of
 * all possible verbs is known.<br>
 *
 * <p>
 * <b>TODO</b>
 * </p>
 * Multiple playerAction stringCommands, such as:<br> Multiverb stringCommands:
 * (look up, eat pie, go west)<br> Verbsharing stringCommands: (eat pie, potato,
 * cake)<br> Object pronouns (this may not be implemented here): (take pie, eat
 * it)<br>
 * <br>
 * With the current implementation, an indeterminism problem arises in trying to
 * parse these kind of stringCommands without a dictionary of valid verbs. As a
 * bonus this would allow for verbs to be modified with adverbs<br> For this to
 * be implemented, what needs to be done:<br> - A verb dictionary<br> -
 * lexicalAnalysis() needs to recognize commas at the end of words as their own
 * tokens<br> - syntacticalAnalys() needs to separate playerActions by
 * separators<br> - incomplete playerActions need to be able to "fill in the
 * gaps" from context of previously parsed playerActions in the same
 * command<br>
 * <p>
 * Alternatively...
 * </p>
 * Subsequent actions can be reparsed (or backtracked) depending on the verb.
 *
 * @author Evan Quan
 */
public abstract class PlayerInputParser {

    // NOTE: For now, only "," as end punctuation will count, as quotes are
    // causing problems with syntactical analysis
    /**
     * Defines the type of punctuation that can exist at the start of a word
     * that will split and count as its own token.
     */
    // public static final char[] START_PUNCTUATION = { '\'', '"' };
    public static final char[] START_PUNCTUATION = {};
    /**
     * Defines the type of punctuation that can exist at the end of a word that
     * will split and count as its own token.
     */
    // public static final char[] END_PUNCTUATION = { '\'', '"', ',' };
    public static final char[] END_PUNCTUATION = {',', '.'};

    public static final String[] VALID_PREPOSITIONS = {};

    /**
     * Splits token by punctuation and adds punctuation components to
     * tokens.<br> - Double and single quotes at the start or end of words<br> -
     * Commas after a word<br> - Other punctuation and symbols are stripped and
     * ignored.
     *
     * @param tokens
     * @param token
     */
    public static void addToken(ArrayList<String> tokens, String token) {

        char firstChar = token.charAt(0);
        if (CollectionUtils.contains(START_PUNCTUATION, firstChar)) {
            tokens.add(Character.toString(firstChar));
            token = token.substring(1);
        }

        boolean changedLastChar = false;
        String endQuote = "";
        char lastChar = token.charAt(token.length() - 1);
        if (CollectionUtils.contains(END_PUNCTUATION, lastChar)) {
            endQuote = Character.toString(lastChar);
            token = token.substring(0, token.length() - 1);
            changedLastChar = true;
        }

        tokens.add(token);
        // End quote is added after word to preserve token order
        if (changedLastChar) {
            tokens.add(endQuote);
        }
    }

    /**
     * Find an objective phrase from a list of tokens. Can be either a direct or
     * indirect object phrase. This modifies the tokens argument (may be changed
     * later if needed).
     *
     * @param tokens
     * @return object phrase that is composed of all token components, or null
     * if tokens is empty. Since this is recursively called for owners, an
     * ObjectPhrase without an owner will have a null owner.
     */
    public static ObjectPhrase getObjectPhrase(ArrayList<String> tokens) {
        if (tokens.isEmpty()) {
            return null;
        }
        ObjectPhrase objectPhrase = new ObjectPhrase();
        // Scan for an determiner. If one is found, remove it and parse the
        // rest of the input.
        // NOTE: The preposition must be the first word in the list for it to
        // make sense grammatically. If a determiner is preceded with another
        // word, be it another determiner or not, it will be counted as an
        // adjective.
        if (Word.isDeterminer(tokens.get(0))) {
            objectPhrase.setDeterminer(tokens.remove(0));
        }

        // Scan for a belonging preposition (of), which determines if this
        // object phrase is owned by an other object phrase.
        // thisTokens is composed of tokens to be used for this object phrase
        // and not its owner (if any).
        ArrayList<String> thisTokens = new ArrayList<>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (Word.isBelongingPreposition(token)) {
                objectPhrase.setBelongingPreposition(token);
                break;
            } else {
                thisTokens.add(token);
            }
        }

        // Add remaining tokens after belonging preposition to be parsed
        // recursively for owner(s).
        ArrayList<String> ownerTokens = new ArrayList<>();
        for (i++; i < tokens.size(); i++) {
            ownerTokens.add(tokens.get(i));
        }
        objectPhrase.setOwner(getObjectPhrase(ownerTokens));

        // The last word in the input is the object. Remove it and parse
        // the rest of the input.
        if (!thisTokens.isEmpty()) {
            // If no more tokens remain, then the last word is not a noun
            objectPhrase.setNoun(thisTokens.remove(thisTokens.size() - 1));
        }
        // If any input remains, they are adjectives which modify the
        // object.
        // TODO: This WILL need to change once multiple stringCommands separated
        // by commas with a single verb is implemented. Either here, or in
        // syntactical analysis.
        ArrayList<String> adjectives = new ArrayList<>();
        for (i = 0; i < thisTokens.size(); i++) {
            adjectives.add(thisTokens.get(i));
        }
        objectPhrase.setAdjectives(adjectives);
        return objectPhrase;
    }

    /**
     * <b>Step 1: Lexical Analysis</b>
     * <p>
     * Splits the input string into tokens, each representing a word of
     * the command. The tokens are in the same order as they appear in the
     * input string. Each character of punctuation counts as its own
     * token only if it is a single or double quote around a word, or a comma
     * after a word.
     *
     * @param input - input String
     * @return list of all tokens.
     */
    public static ArrayList<String> lexicalAnalysis(String input) {
        // NOTE: There's probably a better way to do this that doesn't use Scanner.
        // aka. Split by spaces, then map reduce.
        Scanner in = new Scanner(input);
        ArrayList<String> tokens = new ArrayList<String>();

        // Add all tokens
        while (in.hasNext()) {
            String token = in.next();
            addToken(tokens, token);
        }
        in.close();
        return tokens;
        // Right, now just using basic split by spaces. May need to change this when
        // things get
        // more complicated
        // return new ArrayList<>(Arrays.asList(input.split(" ")));
    }

    /**
     * Parse input text into words and apply their appropriate meanings
     * and relationships. Accepts only imperative statements.
     *
     * @param input - String to parse into words
     * @return command that represents the player {@link PlayerCommand}
     */
    public static PlayerCommand parse(String input) {
        // Add unaltered input to PlayerCommand
        PlayerCommand playerCommand = new PlayerCommand(input);
        // https://groups.google.com/forum/#!topic/rec.arts.int-fiction/VpsWZdWRnlA
        ArrayList<String> tokens = lexicalAnalysis(input);
        ArrayList<ArrayList<String>> actionTokens =
                splitTokensByActions(tokens);

        for (ArrayList<String> action : actionTokens) {
            syntacticalAnalysis(playerCommand, action);
        }

        syntacticalCleanup(playerCommand);

        return playerCommand;
    }

    /**
     * For multi-playerAction stringCommands, playerAction separators define the
     * number of playerActions that are present in a command. Single
     * syntacticalAnalysis() assumes an ArrayList of tokens is a single
     * playerAction, we need to make an ArrayList of ArrayLists (playerActions).
     * Separators are not included in any token array.
     *
     * TODO: Optimization: redo this so it doesn't need to traverse the
     * tokens twice
     *
     * @param tokens
     * @return
     */
    public static ArrayList<ArrayList<String>> splitTokensByActions(
            ArrayList<String> tokens) {
        // Each ArrayList sublist separated by separators counts as its own
        // playerAction
        // Find the number of playerActions and track what index the
        // playerActions are separated by.
        ArrayList<Integer> separatorIndices = new ArrayList<>();
        ArrayList<ArrayList<String>> actionTokens = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (Word.isActionSeparator(tokens.get(i))) {
                separatorIndices.add(i);
            }
        }
        // Separate each playerAction, defined by separatorIndices, into its
        // own ArrayList of tokens. PlayerAction separators are not included
        // in the arrays.
        int startIndex = 0;
        int endIndex;
        for (int i : separatorIndices) {
            endIndex = i; // excludes separator token
            actionTokens.add(new ArrayList<>(tokens.subList(startIndex, endIndex)));
            startIndex = endIndex + 1; // skips over separator token
        }
        // Add remaining tokens until end of tokens.
        endIndex = tokens.size();
        actionTokens.add(new ArrayList<>(tokens.subList(startIndex, endIndex)));

        return actionTokens;
    }

    /**
     * <b>Step 2: Syntactical Analysis</b>
     * <p>
     * Takes a sequence of tokens and sees whether the sequences matches a known
     * correct sentence structure.
     * <p>
     * <b>Grammar Rules</b><br>
     * <p>
     * 0. The dictionary of all possible verbs, adjectives, direct objects, and
     * indirect objects is <b>not</b> known.<br> 1. The first world of the
     * input is a always a verb unless<br> - it is a valid determiner,
     * which then the verb phrase is skipped and the indirect object phrase is
     * parsed.<br> - it is a preposition, which then the verb phrase and
     * indirect object phrase is skipped and the preposition and indirect object
     * phrase are parsed.<br> 2. Indirect object phrases are always preceded by
     * a preposition.<br> 3. Direct object phrases are always positioned before
     * indirect object phrases.<br> 4. The dictionary of all possible
     * Prepositions is known.<br> 5. The dictionary of all possible determiners
     * is known.<br>
     *
     * @param playerCommand
     * @param tokens
     * @return
     */
    public static void syntacticalAnalysis(PlayerCommand playerCommand,
                                           ArrayList<String> tokens) {
        if (tokens.isEmpty()) {
            // This happens when the player input string an empty string
            // This is also the base case to stop ObjectPhrase owner recursion
            return;
        }

        // TODO when multi-playerAction stringCommands are implemented, make
        // this part a loop for every separator section.

        PlayerAction playerAction = new PlayerAction();

        String first = tokens.get(0);
        if (Word.isVerb(first)) {
            // 0. The first word is a verb. Remove it and parse the rest of the
            // input. No adverbs are allowed as it would not be
            // possible to distinguish between the end of the verb phrase and
            // the start of the proceeding indirect/direct object
            // phrase without a dictionary of all possible verbs.
            tokens.remove(0);
            playerAction.setVerbPhrase(new VerbPhrase(first));
        }
        // 1. Scan for a preposition. If one is found, remove it.
        // Parse the input preceding the preposition as a direct object
        // phrase. Parse the input following the preposition as an
        // indirect object phrase. For the sake of how the PlayerCommand will
        // be parsed in the game, the preposition is added to the indirect
        // object phrase.

        // Add first tokens before preposition (if any) to direct tokens.
        // If there is a preposition, store it by itself.
        ArrayList<String> directTokens = new ArrayList<>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (Word.isObjectPhraseSeparatingPreposition(token)) {
                playerAction.setPreposition(token);
                break;
            } else {
                directTokens.add(token);
            }
        }
        // Add remaining tokens after preposition (if any) to indirect tokens.
        ArrayList<String> indirectTokens = new ArrayList<>();
        for (i++; i < tokens.size(); i++) {
            indirectTokens.add(tokens.get(i));
        }

        // Create the object phrases from the token lists
        playerAction.setDirectObjectPhrase(getObjectPhrase(directTokens));
        playerAction.setIndirectObjectPhrase(getObjectPhrase(indirectTokens));

        // Add complete playerAction to player command
        playerCommand.addAction(playerAction);
    }

    /**
     * Modify all actions in a {@link PlayerCommand} so that inferred
     * components can be added to each of them. In more detail, this ensures
     * that all valid multi-actions work by ensuring each one has the verb
     * phrases, prepositions, direct and indirect object phrases necessary.
     *
     * @param playerCommand
     */
    public static void syntacticalCleanup(PlayerCommand playerCommand) {
        if (playerCommand.isEmpty()) {
            return;
        }
        ArrayList<PlayerAction> actions = playerCommand.getPlayerActions();

        fixSyntaxForward(actions);
        // 2. Copy preposition and indirect object phrase backwards. If a new
        // preposition or indirect object phrase is found, set that as the
        // new preposition or indirect object phrase to copy backwards.
//        String preposition = null;
//        ObjectPhrase indirect = null;
//        for (Pla)
    }


    /**
     * Copy verb phrase forward if it has one. If a new verb phrase is
     * found with an accompanying direct object phrase, set that as the new
     * verb to copy forward. The accompanying direct object phrase is
     * needed because otherwise, the verb is being treated as an
     * intransitive verb, which cannot transfer onto a list of direct
     * object verbs.
     *
     * Prepositions are also added if the initial verb has a preposition.
     * Direct object phrases are changed in indirect object phrases to have
     * the preposition refer to the object phrase. If a new preposition is
     * found, switch to the new preposition to copy.
     *
     * @param actions
     */
    public static void fixSyntaxForward(ArrayList<PlayerAction> actions) {
        String verbToCopy = null;
        String prepositionToCopy = null;
        ObjectPhrase directToTransfer = null;
        for (PlayerAction action : actions) {
            if (action.hasVerbPhrase()
                    && (action.hasDirectObjectPhrase() || action.hasIndirectObjectPhrase())) {
                verbToCopy = action.getVerbPhrase().getVerb();
            } else if (action.hasVerbPhrase()) {
                verbToCopy = null;
                // stop copying verb as the verb is stopping intransitive verb
            }

            if (verbToCopy != null) {
                if (action.hasPreposition() && action.hasIndirectObjectPhrase()) {
                    prepositionToCopy = action.getPreposition(); // swap
                } // else continue copying same preposition
            } else {
                prepositionToCopy = null; // stop copying prepositions
            }
            if (verbToCopy != null) {
                action.setVerbPhrase(verbToCopy);
            }
            if (prepositionToCopy != null) {
                action.setPreposition(prepositionToCopy);
                directToTransfer = action.getDirectObjectPhrase();
                if (directToTransfer != null) {
                    action.setIndirectObjectPhrase(action.getDirectObjectPhrase());
                    action.setDirectObjectPhrase(null);
                }
            }
        }
    }

    // /**
    // * <b>Part 3: Translation</b>
    // * <p>
    // * TODO Creates a player command from a list of words. Depending on the
    // relation
    // * between words, the playerAction {@link Verb} and object {@link Noun} are
    // * determined.
    // *
    // * @param input
    // * - original input string
    // * @param statement
    // * @return
    // */
    // private static PlayerCommand translation(String input, Sentence statement) {
    // // Index tracking
    // int actionIndex = 0;
    // int objectIndex = 0;
    // // 1. The first word of the command should either be a verb, or a shortcut
    // // represents some playerAction
    //
    // // return new PlayerCommand(command, playerAction, object);
    // return null;
    // }
}
