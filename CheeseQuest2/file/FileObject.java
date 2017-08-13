package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import game.system.*;

public abstract class FileObject {
    public static final String SAVE_DIRECTORY = "saves";
    public static final String FILE_EXTENTION = "txt";
    public static final String FILE_NAME = "World";
    private int saveNum;
    private File directory;
    private File save;

    public FileObject(int saveNum) {
        setSaveNum(saveNum);
    }

    /**
     * Returns value of saveNum
     * @return
     */
    public int getSaveNum() {
        return this.saveNum;
    }

    /**
     * Returns value of directory
     * @return
     */
    public File getDirectory() {
        return this.directory;
    }

    /**
     * Returns value of save
     * @return
     */
    public File getSave() {
        return this.save;
    }

    /**
     * Sets new value of saveNum
     * @param int saveNum
     */
    public void setSaveNum(int saveNum) {
        this.saveNum = saveNum;
        this.directory = new File(SAVE_DIRECTORY + "/" + saveNum);
        save = new File(SAVE_DIRECTORY + "/" + saveNum + "/" + FILE_NAME + "." + FILE_EXTENTION);
    }

    /**
     * Checks if save directory exists
     * @param  int saveNum       [description]
     * @return     [description]
     */
    public boolean directoryExists() {
        return directory.exists();
    }

    /**
     * Checks if save file exists
     * @param  int saveNum       [description]
     * @return     [description]
     */
    public boolean saveExists() {
        return save.exists();
    }
}
