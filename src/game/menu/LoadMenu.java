package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;
import game.system.save.InvalidSaveNameException;
import game.system.save.InvalidSaveNumException;
import game.system.save.Save;
import game.system.save.SaveManager;

import java.util.ArrayList;

/**
 * Prompts the user to load a game save.
 * Uses ReadObject to detect and load games
 */
public class LoadMenu extends Menu {

    public static final String LOAD_GAME = "Load game";
    // public static final String NEW_GAME = "New game";
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
        saveNums = new ArrayList<>();
        saveNames = new ArrayList<>();
        saveInfo = new ArrayList<>();
        saver = SaveManager.getInstance();
        updateSaves();
    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some corresponding output to this menu's currently set
     * {@link IPrintBuffer}.
     *
     * @param playerCommand to processInput
     */
    @Override
    public void processInput(PlayerCommand playerCommand) {
        // TODO
    }

    /**
     * Create all valid stringCommands for this menu. Use addStringCommand().
     */
    @Override
    protected void initializeCommands() {
//        addCommand(stringCommands, new String[]{"create",
//                "c",
//                "create new game",
//                "create game",
//                "create new"}, () -> createNewGame());
//        addCommand(stringCommands, new String[]{"load",
//                "l"}, () -> loadGame());
//        addCommand(stringCommands, new String[] {"delete",
//                "d"}, () -> deleteGame());
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
    public void printMainPrompt() {
        updateSaves();
//        printTitleln(LOAD_GAME);
        String gameName;
        ArrayList<Save> saves = saver.getSaves();
        for (int i = 0; i < saves.size(); i++) {
//            printItem(i + 1);
//            print(". ");
            outputSaveInfo(saves.get(i));
        }
//        println();
//        printPlayer("Create");
//        print(", ");
//        printPlayer("Load");
//        print(", or ");
//        printPlayer("Delete");
//        print(" a game, or ");
//        printPlayer("Return");
//        print(" to the ");
        if (MenuManager.getPreviousMenu().equals(MainMenu.getInstance())) {
//            printLocation("Main Menu");
//            println(".");
        } else {
//            println("the game.");
        }
        // outputlnRoom(splitCamelCaseToString(MenuManager.getInstance().getPreviousMenu().getClass().getSimpleName()));
    }
    public void outputSaveInfo(Save save) {
//        println(save.getName());
//        printItem("     Room: ");
//        print(save.getRoom().getSingleName());
//        printItem("     Turns: ");
//        print(save.getTurnCount());
//        printItem("     Version: ");
//        println(save.getVersion());
    }

    public void processInput() {
        // System.out.println("Before strip");
        // System.out.println("inputString: " + getInputString());
        // System.out.println("inputWords: " + Arrays.asList(getInputWords()));
        // System.out.println("verb: " + getVerb());
//        if (inputStartsWithStrip(CREATE_OPTIONS)) {
//            if (inputRemains()) {
//                outputExcessCommand();
//            } else {
//                changeToCreateGameMenu();
//            }
//        } else if (inputStartsWithStrip(LOAD_OPTIONS)) {
//            // System.out.println("After load strip");
//            // System.out.println("inputString: " + getInputString());
//            // System.out.println("inputWords: " + Arrays.asList(getInputWords()));
//            // System.out.println("verb: " + getVerb());
//            // System.out.println("saveNums.size() + 1 :" + (saveNums.size() + 1));
//            if (!inputRemains()) {
//                outputIncompleteCommandAndReprompt();
//            } else if (inputEquals(saveNums)) { // Load game by number
//                loadGame(Integer.parseInt(stripInput()));
//                changeToGameMenu();
//            } else if (inputEquals(saveNames)){ // Load game by name
//                loadGame(getInputString());
//                changeToGameMenu();
//            } else {
//                printInvalidInput("load");
//                printMainPrompt();
//            }
//        } else if (inputStartsWithStrip(DELETE_OPTIONS)) {
//
//            // System.out.println("saveNames" + saveNames);
//            // System.out.println("After delete strip");
//            // System.out.println("inputString: " + getInputString());
//            // System.out.println("inputWords: " + Arrays.asList(getInputWords()));
//            // System.out.println("verb: " + getVerb());
//            if (!inputRemains()) {
//                outputIncompleteCommand();
//            } else if (inputStartsWith(saveNums)) { // Delete game by number
//                int saveNum = Integer.parseInt(stripInput());
//                try {
//                    saver.setCurrentSave(saveNum - 1);
//                    saver.deleteSave();
//                    outputDeleted();
//                } catch (InvalidSaveNumException e) {
//                    outputNotDeleted();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else if (inputEquals(saveNames)){
//                String saveName = getInputString();
//                try {
//                    saver.setCurrentSave(saveName);
//                    saver.deleteSave();
//                    outputDeleted();
//                } catch (InvalidSaveNameException e) {
//                    outputNotDeleted();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                outputNotDeleted();
//            }
//            println();
//            printMainPrompt();
//        } else if (inputStartsWithStrip(returnToPreviousMenu)) {
//            changeToPreviousMenu();
//        } else { //
//            println("You cannot do that.");
//            printMainPrompt();
//        }
    }






    /**
     * Sets the GameMenu's world to saveNum
     * @param saveNum of save to load
     */
    public void loadGame(int saveNum) {
        try {
            saver.setCurrentSave(saveNum);
        } catch (InvalidSaveNumException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("LoadMenu, loadGame(int):");
            e.printStackTrace();
        }
        Save save = (Save) saver.restore();
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setSave(save);
    }
    /**
     * Sets the GameMenu's world to saveName
     * @param saveName of save to load
     */
    public void loadGame(String saveName) {
        try {
            saver.setCurrentSave(saveName);
        } catch (InvalidSaveNameException e) {
            System.out.println("LoadMenu, loadGame(String)");
            e.printStackTrace();
        }
        Save save = (Save) saver.restore();
        GameMenu gameMenu = GameMenu.getInstance();
        gameMenu.setSave(save);
    }



    // Prompts
    public void outputInvalid(String action) {
//        if (saveNums.size() == 0) {
//            print("There are no games to ");
//            printPlayer(toTitleCase(playerAction));
//            println(".");
//        } else {
//            print("The only game");
//            if (saveNums.size() > 1) {
//                print("s");
//            }
//            print(" to " + playerAction.toLowerCase() + " ");
//            if (saveNums.size() > 1) {
//                print("are from ");
//            } else {
//                print("is ");
//            }
//            printPlayer(1);
//            if (saveNums.size() > 1) {
//                print(" to ");
//                printPlayer(saveNums.size());
//            }
//            println(".");
//        }

    }

    public void outputDeleted() {
//        print("Save \"");
//        printItem(saver.getCurrentSaveName());
//        println("\" deleted.");
    }

    public void outputNotDeleted() {
//        print("Save \"");
//        printItem(getInputString());
//        println("\" does not exist and cannot be deleted.");
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
     * Updates saveNums, saveNames (for user receiveInput comparison)
     */
    public void updateSaves() {
        // outputln("updateSaves() started");
        saveNames.clear();
        saveNums.clear();
        Save save;
        ArrayList<Save> saves = saver.getSaves();
        // ArrayList<String> saveNames = saver.getSaveNames();
        // outputln("saves: " + Arrays.asList(saves));
        for (int i = 1; i <= saves.size(); i++) {
            // try {
            save = saves.get(i - 1);
            saveNames.add(save.getName());
            saveNums.add(i);
                // outputln("i: " + i + " | name: " + name);
                // saver.setCurrentSave(name);
                // save = (Save) saver.restore();
                // saveInfo.add(save.getInfo());
            // } catch (InvalidSaveNameException e) {
            //     e.printStackTrace();
            // } catch (Exception e) {
            //     e.printStackTrace();
            // }
        }
    }


    public ArrayList<String> getSaveNames() {
        return this.saveNames;
    }
    public ArrayList<Integer> getSaveNums() {
        return this.saveNums;
    }

}
