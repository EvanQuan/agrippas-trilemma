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
    private static LoadMenu instance;
    private ArrayList<String> validSave;

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
        if (instance == null) {
            instance = new LoadMenu();
        }
        return instance;
    }

    /**
     * Outputs numerical list of existing save files
     * and New game option
     */
    @Override
    public void outputPrompt() {
        outputlnItem("Load Game");
        String gameName;
        int i;
        for (i = 0; i < validSave.size(); i++) {
            if (existingSaves.contains(validSave.get(i))) {
                gameName = "Load game"; // Game exists
            } else {
                gameName = "New game"; // Game does not exist
            }
            outputln("    " + i + ". " + gameName);
        }
        outputln("    " + i + ". New game");
    }

    @Override
    public void processInput() {
        if (verbEquals(existingSave)) { // Load preexiting game
            loadGame(Integer.parseInt(getVerb()));
            changeToGameMenu();
        } else if (verbEquals(validSave)) { // Create new game
            createGame(Integer.parseInt(verVerb()));
            changeToGameMenu();
        } else {
            outputInvalid();
            outputPrompt();
        }
    }

    public void outputInvalid() {
        outputln("Choose a valid save file.");
    }


    /**
     * Sets the GameMenu's world to saveNum
     * @param int saveNum of world to load
     */
    public void loadGame(int saveNum) {
        ReadObject.setSaveNum(saveNum);
        World world = (World) ReadObject.deserialize();
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setWorld(world);
    }

    /**
     * Creatse new game, and sets gameMenu's world to new game
     * @param int saveNum of thworld to create
     */
    public void createGame(int saveNum) {
        World world = new World();
        WriteObject.setSaveNum(saveNum);
        WriteObject.serialize(world);
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setWorld(world);
    }
}
