/*
 * Function
 * - Sets frame's title to closing message
 * - Pauses window
 * - Disposes window
 *
 * Changelog
 * 1.0.0 - August 9, 2017
 * - Implemented
 */
package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * @author Evan Quan
 * @version 1.0.0
 * @since 2017.08.09
 */
public class MainWindowListener extends WindowAdapter {
    public static final String CLOSE_MESSAGE = "Quitting game...";
    public static final String CLOSING_ERROR_MESSAGE = "ERROR: Closing delay was interrupted.";
    public static final int CLOSE_TIME = 500; // Delay in millliseconds

    /**
     * Default MainWindowListener constructor
     */
    public MainWindowListener() {
    }
    /**
     * Open window
     * @param WindowEvent anEvent
     */
    // public void windowOpened(WindowEvent anEvent) {
    //
    // }
    // Closes window
    // Causes small delay and sets frame title to close message
    public void windowClosing(WindowEvent anEvent) {
        JFrame aFrame = (JFrame) anEvent.getWindow();
        // Close delay
        try {
            aFrame.setTitle(CLOSE_MESSAGE);
            Thread.sleep(CLOSE_TIME);
        } catch (InterruptedException anException) {
            System.out.println(CLOSING_ERROR_MESSAGE);
        }
        // Close window
        aFrame.setVisible(false);
        aFrame.dispose();
    }
}
