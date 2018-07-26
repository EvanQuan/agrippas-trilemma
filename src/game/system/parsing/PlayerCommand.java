package game.system.parsing;

import java.util.ArrayList;

import util.FuncUtils;

/**
 * Represents a command that the player has issued to the game in a form of a
 * {@link String}. A player command is composed of a series of {@link PlayerAction}s
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
     * Series of playerActions in the order they were issued.
     */
    ArrayList<PlayerAction> playerActions;

    /**
     * Creates a Player Command instance.
     *
     * @param string
     */
    public PlayerCommand(String string) {
        this.string = string;
        playerActions = new ArrayList<>();
    }

    /**
     * Add a single playerAction to this commands list of playerActions.
     * 
     * @param playerAction
     */
    public void addAction(PlayerAction playerAction) {
        this.playerActions.add(playerAction);
    }

    public boolean contains(PlayerAction playerAction) {
        return this.playerActions.contains(playerAction);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PlayerCommand) {
            return hasSameString((PlayerCommand) other) && hasSameActions((PlayerCommand) other);
        }
        return false;
    }

    public ArrayList<PlayerAction> getPlayerActions() {
        return this.playerActions;
    }

    public String getString() {
        return this.string;
    }

    public boolean hasActions() {
        return this.playerActions != null && !this.playerActions.isEmpty();
    }

    public boolean hasSameActions(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.playerActions, other.getPlayerActions());
    }

    public boolean hasSameString(PlayerCommand other) {
        return FuncUtils.nullablesEqual(this.string, other.getString());
    }

    public void setPlayerActions(ArrayList<PlayerAction> playerActions) {
        this.playerActions = playerActions;
    }

    @Override
    public String toString() {
        StringBuilder actions = new StringBuilder();
        if (hasActions()) {
            for (PlayerAction playerAction : this.playerActions) {
                actions.append("\t" + playerAction + "\n");
            }
        }
        return "[string: " + string + "\nplayerActions: " + actions + "]";
    }
}
