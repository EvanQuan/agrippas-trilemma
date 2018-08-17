package game.menu;

import game.system.input.PlayerCommand;
import game.system.output.IPrintBuffer;
import game.system.output.SemanticColor;
import game.system.save.InvalidSaveNameException;
import game.system.save.InvalidSaveNumException;
import game.system.save.Save;
import game.system.save.SaveManager;

import java.util.ArrayList;

/**
 * Prompts the user to load a game save. Uses ReadObject to detect and load
 * games
 */
public class LoadMenu extends Menu {

    private static final String TITLE = "Load game";
    // public static final String NEW_GAME = "New game";

    /**
     * Singleton instance.
     */
    private static LoadMenu instance;
    /**
     * Enumerated order of saves
     */
    private ArrayList<Integer> saveNumbers;
    /**
     * String names of saves in order
     */
    private ArrayList<String> saveNames;
    /**
     * Information about saves in order Current room
     */
    private ArrayList<String> saveInfo;
    private SaveManager saver;

    /**
     * Default LoadMenu constructor
     */
    private LoadMenu() {
        saveNumbers = new ArrayList<>();
        saveNames = new ArrayList<>();
        saveInfo = new ArrayList<>();
        saver = SaveManager.getInstance();
        updateSaveInformation();
    }

    /**
     * Process a {@link PlayerCommand} as receiveInput. This will set some
     * corresponding output to this menu's currently set {@link IPrintBuffer}.
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

    /**
     * @return singleton instance
     */
    public static LoadMenu getInstance() {
        if (instance == null) {
            instance = new LoadMenu();
        }
        return instance;
    }

    /**
     * Outputs numerical list of existing save files by name and information and
     * New game option
     */
    public void printMainPrompt() {
        out.println(TITLE, SemanticColor.TITLE);
        updateSaveInformation();
        printAllSaveInformation();
        printMenuOptions();
    }

    /**
     * Print what the player can do while in the Load Menu.
     */
    private void printMenuOptions() {
        out.printlns(OPTIONS_SPACING);
        out.print("Load", SemanticColor.PLAYER);
        out.print(", or ");
        out.print("Delete", SemanticColor.PLAYER);
        out.print("a game, or ");
        out.print("Return", SemanticColor.PLAYER);
        out.print(" to the ");
        if (MenuManager.getPreviousMenu().equals(MainMenu.getInstance())) {
            out.print("Main Menu", SemanticColor.LOCATION);
            out.println(".");
        } else {
            out.println("game.");
        }
    }

    /**
     * Updates all save information and prints information about each save.
     */
    private void printAllSaveInformation() {
        ArrayList<Save> saves = saver.getSaves();
        for (int i = 0; i < saves.size(); i++) {
            out.print(i + 1, SemanticColor.PLAYER);
            out.print(". ");
            printSaveInformation(saves.get(i));
        }

    }

    /**
     * Print information about the specified save in a formatted matter.
     *
     * @param save to print information about.
     */
    private void printSaveInformation(Save save) {
        out.println(save.getName(), SemanticColor.TITLE);
        out.print("    Room: ", SemanticColor.LOCATION);
        out.print(save.getRoom().getSingleName());
        out.print("    Turns: ", SemanticColor.ITEM);
        out.print(save.getTurnCount());
        out.print("    Version: ", SemanticColor.DIRECTION);
        out.print(save.getVersion());
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
//            // System.out.println("saveNumbers.size() + 1 :" + (saveNumbers.size() + 1));
//            if (!inputRemains()) {
//                outputIncompleteCommandAndReprompt();
//            } else if (inputEquals(saveNumbers)) { // Load game by number
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
//            } else if (inputStartsWith(saveNumbers)) { // Delete game by number
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
     *
     * @param saveNumber of save to load
     */
    public void loadGame(int saveNumber) {
        try {
            saver.setCurrentSave(saveNumber);
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
     *
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
//        if (saveNumbers.size() == 0) {
//            print("There are no games to ");
//            printPlayer(toTitleCase(playerAction));
//            println(".");
//        } else {
//            print("The only game");
//            if (saveNumbers.size() > 1) {
//                print("s");
//            }
//            print(" to " + playerAction.toLowerCase() + " ");
//            if (saveNumbers.size() > 1) {
//                print("are from ");
//            } else {
//                print("is ");
//            }
//            printPlayer(1);
//            if (saveNumbers.size() > 1) {
//                print(" to ");
//                printPlayer(saveNumbers.size());
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
     * Updates saveNumbers, saveNames (for user receiveInput comparison)
     */
    public void updateSaveInformation() {
        // outputln("updateSaveInformation() started");
        saveNames.clear();
        saveNumbers.clear();
        Save save;
        ArrayList<Save> saves = saver.getSaves();
        // ArrayList<String> saveNames = saver.getSaveNames();
        // outputln("saves: " + Arrays.asList(saves));
        for (int i = 1; i <= saves.size(); i++) {
            // try {
            save = saves.get(i - 1);
            saveNames.add(save.getName());
            saveNumbers.add(i);
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

    public ArrayList<Integer> getSaveNumbers() {
        return this.saveNumbers;
    }

}
