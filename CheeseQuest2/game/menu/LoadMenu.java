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
    /**
     * Enumerated order of saves
     */
    private ArrayList<Integer> saveNums;
    /**
     * String names of saves in order
     */
    private ArrayList<String> saveNames;
    /**
     * Information about saves in order
     * Current room
     *
     */
    private ArrayList<String> saveInfo;
    private SaveManager saver;

    /**
     * Default LoadMenu constructor
     */
    private LoadMenu() {
        saveNums = new ArrayList<Integer>();
        saveNames = new ArrayList<String>();
        saveInfo = new ArrayList<String>();
        saver = SaveManager.getInstance();
        initSaves();
    }

    public static LoadMenu getInstance() {
        if (instance == null) {
            instance = new LoadMenu();
        }
        return instance;
    }

    /**
     * Outputs numerical list of existing save files by name and information
     * and New game option
     */
    @Override
    public void outputPrompt() {
        initSaves();
        outputlnTitle(LOAD_GAME);
        String gameName;
        for (int i = 0; i < saveNums.size(); i++) {
            outputItem(saveNums.get(i));
            outputln(". " + saveNames.get(i));
            outputln("     " + saveInfo.get(i));
        }
        // New game
        outputItem(saveNums.size() + 1);
        outputln(". " + "New game");
        outputln();
        outputPlayer("Load");
        output(" or ");
        outputPlayer("Delete");
        output(" a save file, or ");
        outputPlayer("Return");
        output(" to the ");
        if (MenuManager.getInstance().getLastMenu().equals(MainMenu.getInstance())) {
            outputRoom("Main Menu");
            outputln(".");
        } else {
            outputln("the game.");
        }
    }

    @Override
    public void processInput() {
        if (inputStartsWithStrip(LOAD_OPTIONS)) {
            if (inputEquals(saveNums)) { // Load prexisting game by number
                loadGame(Integer.parseInt(stripInput()));
                changeToGameMenu();
            } else if (inputEquals(saveNames)){ // Load prexisting game by name
                loadGame(getInputString());
                changeToGameMenu();
            } else if (inputEquals(saveNums.size())) { // Create new game
                int saveNum = Integer.parseInt(stripInput());
                createGame(saveNum);
                loadGame(saveNum); // load game created
                changeToGameMenu();
            } else {
                outputInvalidLoad();
            }
        } else if (inputStartsWithStrip(DELETE_OPTIONS)) {
            if (inputStartsWith(saveNums)) { // Delete game by number
                int saveNum = Integer.parseInt(stripInput());
                SaveManager saver = SaveManager.getInstance();
                saver.setCurrentSave(saveNum);
                boolean deleted = saver.deleteSave();
                if (deleted) {
                    outputDeleted();
                } else {
                    outputNotDeleted();
                }
            } else if (inputStartsWith(saveNames)){

            } else {
                outputNotDeleted();
            }
            outputPrompt();
        } else if (inputStartsWithStrip(RETURN_TO_PREVIOUS_MENU)) {
            changeToLastMenu();
        } else {
            outputInvalidDelete();
            outputPrompt();
        }
    }






    /**
     * Sets the GameMenu's world to saveNum
     * @param int saveNum of world to load
     */
    public void loadGame(int saveNum) {
        saver.setCurrentSave(saveNum);
        World world = (World) saver.restore();
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setWorld(world);
    }
    public void loadGame(String saveName) {
    }

    /**
     * Create new game, and sets gameMenu's world to new game
     * @param String saveName of the world to create
     */
    public void createGame(String saveName) {
        World world = new World(); // New default world
        try {
            saver.setCurrentSave(saveName);
            saver.save(world);
        } catch (InvalidSaveNameException e) {
            outputInvalidSaveName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // GameMenu gameMenu = GameMenu.getInstance();
        // gameMenu.setWorld(world);
    }

    // Prompts
    public void outputInvalidLoad() {
        outputPlayer("Load");
        output(" a save file from ");
        outputPlayer(1);
        output(" to ");
        outputPlayer(saveNums.size() + 1);
        outputln(".");
    }
    public void outputInvalidDelete() {
        output("Delete");
        output(" a save file from ");
        outputPlayer(1);
        output(" to ");
        outputPlayer(saveNums.size());
        outputln(".");
    }
    public void outputDeleted() {
        outputln("Save " + stripInput() + "deleted.");
    }

    public void outputNotDeleted() {
        outputln("Save " + stripInput() + " does not exist and cannot be deleted.");
    }
    public void outputInvalidSaveName() {
        output("Save names cannot contain the special characters ");
        for (int i = 0; i < SaveManager.INVALID_CHARACTERS.length - 1; i++) {
            outputPlayer(SaveManager.INVALID_CHARACTERS[i]);
            output(", ");
        }
        if (SaveManager.INVALID_CHARACTERS.length > 1) {
            output("or ");
        }
        outputlnPlayer(SaveManager.INVALID_CHARACTERS[SaveManager.INVALID_CHARACTERS.length - 1]);
    }


    /**
     * Determines valid saves depending on MAXIMUM_SAVES
     */
    // public void determineValidSaves() {
    //     validSaves.clear();
    //     for (int i = 1; i <= MAXIMUM_SAVES; i++) {
    //         validSaves.add(Integer.toString(i));
    //     }
    // }

    /**
     * initializes saves, saveNums, saveNames, saveInfo
     */
    public void initSaves() {
        saveNames.clear();
        saveNums.clear();
        saveInfo.clear();
        SaveManager saver = SaveManager.getInstance();
        World world;
        File[] saves = saver.getSaves();
        for (int i = 1; i <= saves.length; i++) {
            saver.setCurrentSave(saves[i].getName());
            world = (World) saver.restore();
            saveNames.add(saves[i].getName());
            saveNums.add(i);
            saveInfo.add(world.getInfo());
        }
    }
}
