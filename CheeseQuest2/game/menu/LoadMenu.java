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
    public static final String LOAD_GAME = "Load game";
    public static final String NEW_GAME = "New game";
    public static final String[] RETURN_TO_PREVIOUS_MENU = {"return","r"};
    public static final String[] LOAD_OPTIONS = {"load","l"};
    public static final String[] DELETE_OPTIONS = {"delete","d"};
    private static LoadMenu instance;
    private ArrayList<String> validSaves;
    private ArrayList<String> existingSaves;
    private ArrayList<String> saveInfo;

    /**
     * Default LoadMenu constructor
     */
    private LoadMenu() {
        existingSaves = new ArrayList<String>();
        validSaves = new ArrayList<String>();
        saveInfo = new ArrayList<String>();
        determineValidSaves();
        determineExistingSaves();
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
        determineExistingSaves();
        outputlnTitle(LOAD_GAME);
        String gameName;
        for (int i = 0; i < MAXIMUM_SAVES; i++) {
            outputItem((i + 1) + ". ");
            outputln(saveInfo.get(i));
        }
        outputln();
        outputItem("Load");
        output(" or ");
        outputItem("Delete");
        output(" a save file, or ");
        outputItem("Return");
        output(" to the ");
        if (MenuManager.getInstance().getLastMenu().equals(MainMenu.getInstance())) {
            outputln("Main Menu.");
        } else {
            outputln("the game.");
        }
    }

    @Override
    public void processInput() {
        inputStartsWithStrip(LOAD_OPTIONS); // strip away load if added
        if (inputStartsWith(existingSaves)) { // Load preexiting game
            loadGame(Integer.parseInt(stripInput()));
            changeToGameMenu();
        } else if (inputStartsWith(validSaves)) { // Create new game
            createGame(Integer.parseInt(stripInput()));
            loadGame(Integer.parseInt(stripInput())); // load game created
            changeToGameMenu();
        } else if (inputStartsWithStrip(DELETE_OPTIONS)) {
            if (inputStartsWith(existingSaves)) {
                boolean deleted = WriteObject.getInstance().deleteSave();
                if (deleted) {
                    outputDeleted();
                } else {
                    outputNotDeleted();
                }
            } else {
                outputNotDeleted();
            }
        } else if (inputStartsWithStrip(RETURN_TO_PREVIOUS_MENU)) {
            changeToLastMenu();
        } else {
            outputInvalid();
            outputPrompt();
        }
    }

    public void outputInvalid() {
        outputln("Choose a save file from 1 to " + MAXIMUM_SAVES + ".");
    }

    public void outputDeleted() {
        outputln("Save " + stripInput() + "deleted.");
    }

    public void outputNotDeleted() {
        outputln("Save " + stripInput() + " does not exist and cannot be deleted.");
    }


    /**
     * Sets the GameMenu's world to saveNum
     * @param int saveNum of world to load
     */
    public void loadGame(int saveNum) {
        ReadObject reader = ReadObject.getInstance();
        reader.setSaveNum(saveNum);
        World world = (World) reader.deserialize();
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setWorld(world);
    }

    /**
     * Creatse new game, and sets gameMenu's world to new game
     * @param int saveNum of thworld to create
     */
    public void createGame(int saveNum) {
        // World world = new World();
        // WriteObject.setSaveNum(saveNum);
        // WriteObject.serialize(world);
        // GameMenu gameMenu = GameMenu.getInstance();
        // gameMenu.setWorld(world);
    }

    /**
     * Determines valid saves depending on MAXIMUM_SAVES
     */
    public void determineValidSaves() {
        validSaves.clear();
        for (int i = 1; i <= MAXIMUM_SAVES; i++) {
            validSaves.add(Integer.toString(i));
        }
    }

    /**
     * [determineExistingSaves description]
     */
    public void determineExistingSaves() {
        existingSaves.clear();
        saveInfo.clear();
        ReadObject reader = ReadObject.getInstance();
        World world;
        for (int i = 1; i <= MAXIMUM_SAVES; i++) {
            reader.setSaveNum(i);
            if (reader.saveExists()) {
                world = (World) reader.deserialize();
                existingSaves.add(Integer.toString(i));
                saveInfo.add(world.getInfo());
            } else {
                saveInfo.add(NEW_GAME);
            }
        }
    }
}
