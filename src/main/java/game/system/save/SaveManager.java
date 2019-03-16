package game.system.save;

import util.TextUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages save files Save world by name Load/Restore by name
 * <p>
 * Influence: Credits to Mkyong.com https://www.mkyong.com/java/how-to-write-an-object-to-file-in-java/
 */
public class SaveManager {
    public static final int MAXIMUM_SAVES = 50;
    public static final int MAX_NAME_LENGTH = 50;
    public static final String SAVE_DIRECTORY = "game/system/saves";
    public static final String FILE_EXTENTION = "cheese";
    // public static final String FILE_NAME = "World";
    public static final HashSet<String> INVALID_CHARACTERS = new HashSet<>(
            Set.of("<", ">", ":", "\"", "/", "\\", "|", "?", "*")
    );
    private static SaveManager instance;
    private String saveName; // current save name
    private File directory;
    private File currentSaveFile;
    private File[] saveFiles;
    private ArrayList<Save> saves;
    // protected ArrayList<String> saveNums;
    // protected ArrayList<String> saveNames;
    // protected ArrayList<String> saveInfo;

    private SaveManager() {
        directory = new File(SAVE_DIRECTORY);
        saves = new ArrayList<Save>();
        // saveNums = new ArrayList<String>();
        // saveNames = new ArrayList<String>();
    }

    public static SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
    }

    /**
     * Checks if save file exists within valid saves https://stackoverflow.com/questions/3154488/how-do-i-iterate-through-the-files-in-a-directory-in-java
     */
    public void updateSaveFiles() {
        this.saveFiles = directory.listFiles();
        if (this.saveFiles == null) {
            this.saveFiles = new File[]{};
        }
    }

    public void updateSaves() {
        updateSaveFiles();
        saves.clear();
        for (File saveFile : this.saveFiles) {
            try {
                setCurrentSave(TextUtils.stripExtension(saveFile.getName()));
                Save save = (Save) restore();
                saves.add(save);
            } catch (InvalidSaveNameException e) {
                System.out.println("SaveManager.getSaves():");
                e.printStackTrace();
            }
        }
    }
    // public void determineSaveNums() {
    //     this.saveFiles = directory.listFiles();
    //     if (this.saveFiles == null) {
    //         this.saveFiles = new File[] {};
    //     }
    //     saveNums.clear();
    //     for (int i = 1; i <= this.saveFiles.length; i++) {
    //         saveNums.add(Integer.toString(i));
    //     }
    //     for (File save : this.saveFiles) {
    //         saveNames.add(toLowerTitleCase(stripExtension(save.getName())));
    //     }
    // }

    /**
     * Serialize object to save file
     *
     * @param object to be saved
     */
    public void save(Object object) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        // String name = object.getClass().getSimpleName();
        String name = saveName;

        try {
            makeDirectory();
            fout = new FileOutputStream(getCurrentSave());
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Deserialize object from file of filename of saveNum.
     *
     * @return object from file
     */
    public Object restore() {
        Object object = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            // fin = new FileInputStream("saves/" + saveNum + "/" + filename + ".save");
            fin = new FileInputStream(getCurrentSave());
            ois = new ObjectInputStream(fin);
            object = ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * Creates director of saveNum if it does not exist
     */
    public void makeDirectory() {
        // If the directory does not exist, create it
        if (!directoryExists()) {
            try {
                getDirectory().mkdirs();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * Delete save file if it exists
     *
     * @return true if file deleted, false if file was not deleted
     */
    public boolean deleteSave() {
        boolean deleted = false;
        try {
            deleted = Files.deleteIfExists(getCurrentSave().toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }
    /**
     * Returns if saveNums is not empty
     */
    // public boolean hasSaveNums() {
    //     return saveNums.size() > 0;
    // }

    /**
     * Returns value of saveName
     *
     * @return
     */
    public String getSaveName() {
        return this.saveName;
    }

    // public String getSaveInfo() {
    //     return this.saveInfo;
    // }

    /**
     * Returns value of directory
     *
     * @return
     */
    public File getDirectory() {
        return this.directory;
    }

    /**
     * Returns value of saves
     *
     * @return
     */
    public File[] getSaveFiles() {
        updateSaveFiles();
        return this.saveFiles;
    }

    /**
     * Returns all saves as ArrayList
     *
     * @return
     */
    public ArrayList<Save> getSaves() {
        updateSaves();
        return this.saves;

    }

    /**
     * Returns value of save
     *
     * @return
     */
    public File getCurrentSave() {
        return this.currentSaveFile;
    }

    /**
     * Sets new value of currentSaveFile by save name
     *
     * @param saveName
     */
    public void setCurrentSave(String saveName) throws InvalidSaveNameException {
        for (String c : INVALID_CHARACTERS) {
            if (saveName.contains(c)) {
                throw new InvalidSaveNameException();
            }
        }
        this.saveName = TextUtils.toLowerTitleCase(saveName);
        this.currentSaveFile = new File(SAVE_DIRECTORY + "/" + this.saveName + "." + FILE_EXTENTION);
    }
    // public ArrayList<String> getSaveNumbers() {
    //     return saveNums;
    // }
    // public ArrayList<String> getSaveNames() {
    //     return saveNames;
    // }

    /**
     * Sets values of currentSaveFile by save number
     *
     * @param saveNum
     */
    public void setCurrentSave(int saveNum) throws InvalidSaveNumException {
        try {
            setCurrentSave(TextUtils.stripExtension(this.saveFiles[saveNum].getName())); // gets name with extension
        } catch (Exception e) {
            throw new InvalidSaveNumException();
        }
    }

    public String getCurrentSaveName() {
        return TextUtils.stripExtension(this.currentSaveFile.getName());
    }

    /**
     * Checks if save directory exists
     *
     * @return true if save file exists.
     */
    public boolean directoryExists() {
        return directory.exists();
    }

    /**
     * Checks if save file exists
     *
     * @return true if save file exists.
     */
    public boolean currentSaveFileExists() {
        return currentSaveFile.exists();
    }

    // public void serializeAddressJDK7(Address address) {
    //
    //     try (ObjectOutputStream oos =
    //             new ObjectOutputStream(new FileOutputStream("c:\\temp\\address2.ser"))) {
    //
    //         oos.writeObject(address);
    //         System.out.println("Done");
    //
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     }
    //
    // }

    // public Address deserializeAddressJDK7(String filename) {
    //
    //     Address address = null;
    //
    //     try (ObjectInputStream ois
    //         = new ObjectInputStream(new FileInputStream(filename))) {
    //
    //         address = (Address) ois.readObject();
    //
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     }
    //
    //     return address;
    //
    // }
}
