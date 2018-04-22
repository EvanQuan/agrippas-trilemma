package game.system.parsing;

import game.system.parsing.words.Noun;
import game.system.parsing.words.Verb;

/**
 * Represents a player command.
 * <p>
 * Contains an action {@link Verb} that determines what action is to be taken.
 * <p>
 * May contain a object {@link Noun} if the verb is applied to that object.<br>
 *
 * @author Evan Quan
 *
 */
public class PlayerCommand {

    private String command;

    private Verb action;
    private Noun object;

    /**
     * Creates a Player Command instanc
     *
     * @param command
     * @param action
     * @param object
     */
    public PlayerCommand(String command, Verb action, Noun object) {
        this.command = command;
        this.action = action;
        this.object = object;
    }

    /**
     *
     * @return the {@link Verb} that determines what action is to be taken.
     */
    public Verb getAction() {
        return action;
    }

    /**
     *
     * @return the {@link String} that the player inputted that represents this
     *         command
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @return the {@link Noun} that determines what the verb is applied to. Can be
     *         null if there is no object.
     */
    public Noun getObject() {
        return object;
    }

}
