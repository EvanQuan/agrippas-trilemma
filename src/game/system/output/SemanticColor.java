package game.system.output;

import java.awt.Color;
import java.util.Map;

/**
 * SemanticColor for {@link IPrintBuffer}. These colors are semantically valued and link to what their use represents.
 * Implementations of IPrintBuffer decide what colors these color values correspond to.
 *
 * @author Evan Quan
 *
 */
public enum SemanticColor {
    CHARACTER,
    DIRECTION,
    ITEM,
    DEFAULT,
    LOCATION,
    PLAYER,
    SPEECH,
    TITLE;


    /**
     * Linking semantic and console color values
     */
    private static final Map<SemanticColor, ConsoleColor> consoleMap = Map.ofEntries(
        Map.entry(CHARACTER, ConsoleColor.BLUE_BRIGHT),
        Map.entry(DIRECTION, ConsoleColor.YELLOW_BRIGHT),
        Map.entry(ITEM, ConsoleColor.RED_BRIGHT),
        Map.entry(DEFAULT, ConsoleColor.RESET),
        Map.entry(LOCATION, ConsoleColor.PURPLE_BRIGHT),
        Map.entry(PLAYER, ConsoleColor.CYAN_BRIGHT),
        Map.entry(SPEECH, ConsoleColor.GREEN_BRIGHT),
        Map.entry(TITLE, ConsoleColor.PURPLE_BOLD_BRIGHT)
    );

    /**
     * Linking semantic and console color values
     */
    private static final Map<SemanticColor, Color> guiMap = Map.ofEntries(
        Map.entry(CHARACTER , new Color(8, 132, 255)),
        Map.entry(DIRECTION, new Color(255, 207, 6)),
        Map.entry(ITEM, new Color(185, 0, 0)),
        Map.entry(DEFAULT, Color.BLACK),
        Map.entry(LOCATION, new Color(120, 57, 145)),
        Map.entry(PLAYER, new Color(0, 252, 255)),
        Map.entry(SPEECH, new Color(19, 200, 0)),
        Map.entry(TITLE, new Color(120, 57, 145))
    );
    /**
     * Convert a SemanticColor to a {@link ConsoleColor}
     * @param semanticColor
     * @return
     */
    public static ConsoleColor toConsoleColor(SemanticColor semanticColor) {
        return consoleMap.get(semanticColor);
    }

    /**
     * Convert a SemanticColor to a {@link Color}
     * @param semanticColor
     * @return
     */
    public static Color toGUIColor(SemanticColor semanticColor) {
        return guiMap.get(semanticColor);
    }
}
