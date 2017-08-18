package game.menu;

import java.io.*;
import java.util.*;
import file.*;

import game.system.*;

/**
 * Prompts to load game save
 * Uses ReadObject to detect and load games
 */
public class LoadMenu extends Menu {

    public static final int MAXIMUM_SAVES = 5;
    private static LoadMenu loadMenu = new LoadMenu();
    private ArrayList<String> existingSave;
    private ArrayList<String> validSave;
    private ReadObject reader;


    /**
     * Default LoadMenu constructor
     */
    private LoadMenu() {
        existingSave = new ArrayList<String>();
        validSave = new ArrayList<String>();
        for (int i = 1; i <= MAXIMUM_SAVES; i++) {
            validSave.add(Integer.toString(i));
        }
    }

    public static LoadMenu getInstance() {
        return loadMenu;
    }

    @Override
    public void outputPrompt() {
        outputln("Load Game");
        String gameName;
        for (int i = 0; i < validSave.size(); i++) {
            if (existingSave.contains(validSave.get(i))) {
                gameName = "Load game"; // Game exists
            } else {
                gameName = "New game"; // Game does not exist
            }
            outputln("    " + i + ". " + gameName);
        }
    }

    @Override
    public void processInput() {
        if (verbEquals(existingSave)) {
            loadGame(Integer.parseInt(getVerb()));
            changeToGameMenu();
        } else if (verbEquals(validSave)) {

        } else {

        }
    }

    /**
     * Checks if save file exists within valid saves
     */
    public void determineExistingSaves() {
        existingSave.clear();
        for (int i = 1; i <= MAXIMUM_SAVES; i++) {
            reader.setSaveNum(i);
            if (reader.saveExists()) {
                existingSave.add(Integer.toString(i));
            }
        }
    }

    /**
     * Sets the GameMenu's world to saveNum
     * @param int saveNum of world to load
     */
    public void loadGame(int saveNum) {
        reader.setSaveNum(saveNum);
        World world = (World) reader.deserialize();
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setWorld(world);
    }
}
