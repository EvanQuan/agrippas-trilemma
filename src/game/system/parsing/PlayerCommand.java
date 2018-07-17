package game.system.parsing;

import java.util.ArrayList;

import util.FuncUtils;

/**
 * Represents a command that the player has issued to the game in a form of a
 * {@link String}. A player command is composed of a series of {@link Action}s
 * that are sequentially ordered.
 *
 * @author Evan Quan
 *
 */
public class PlayerCommand {

    /**
     * Represents the player command directly as a string, unaltered.
     */
    private String string;

    /**
     * Series of actions in the order they were issued.
     */
    ArrayList<Action> actions;

    /**
     * Creates a Player Command instance.
     *
     * @param string
     */
    public PlayerCommand(String string) {
        this.string = string;
        actions = new ArrayList<>();
    }

    /**
     * Add a single action to this commands list of actions.
     * 
     * @param action
     */
    public void addAction(Action action) {
        this.actions.add(action);
    }

    public boolean contains(Action action) {
        return this.actions.contains(action);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PlayerCommand) {
            return hasSameString((PlayerCommand) other) && hasSameActions((PlayerCommand) other);
        }
        return false;
    }

    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public String getString() {
        return this.string;
    }

    public boolean hasActions() {
        return this.actions != null && !this.actions.isEmpty();
    }

    public boolean hasSameActions(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.actions, other.getActions());
    }

    public boolean hasSameString(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.string, other.getString());
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        String actions = "";
        if (hasActions()) {
            for (Action action : this.actions) {
                actions += "\t" + action + "\n";
            }
        }
        return "[string: " + string + "\nactions: " + actions + "]";
    }
}
