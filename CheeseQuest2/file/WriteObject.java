package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import game.system.*;

/**
 * Writes object to file in current directory
 * Credits to Mkyong.com
 * https://www.mkyong.com/java/how-to-write-an-object-to-file-in-java/
 */
public class WriteObject extends FileObject {
    /**
    * Default WriteObject constructor
    */
    public WriteObject(int saveNum) {
        super(saveNum);
    }

    /**
     * Save object to save file
     * @param Object object to be saved
     * @param int    save   save number
     */
    public void serialize(Object object) {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        String name = object.getClass().getSimpleName();

        try {

            makeDirectory();
            fout = new FileOutputStream(getSave());
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);

            // System.out.println("Done");

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

    /**
     * Creates director of saveNum if it does not exist
     */
    public void makeDirectory() {
        // If the directory does not exist, create it
        if (!directoryExists()) {
            try {
                getDirectory().mkdirs();
            } catch(SecurityException se) {
                se.printStackTrace();
            }
        }
    }
}
