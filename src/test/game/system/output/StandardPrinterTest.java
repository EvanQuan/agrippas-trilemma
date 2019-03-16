package test.game.system.output;

import game.system.output.ConsolePrintBuffer;
import game.system.output.SemanticColor;

/**
 * Not a formal JUnit test. Just to see the colors
 *
 * @author Evan Quan
 *
 */
public class StandardPrinterTest {

    public static void main(String[] args) {
        ConsolePrintBuffer out = new ConsolePrintBuffer();
        out.setWrapWidth(120);
        // LoremGenerator l = new LoremGenerator();
        // String message = l.generate(100, true);
        for (SemanticColor color : SemanticColor.values()) {
            // out.println(message, color);
        }
    }
}
