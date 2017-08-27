package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.SecurityException;
import game.system.*;

/**
 * Reads object from file
 * Credits to Mkyong.com
 * https://www.mkyong.com/java/how-to-read-an-object-from-file-in-java/
 */
public static class ReadObject extends FileObject {

    /**
     * Get object from file of filename of saveNum
     * @param  String filename      name of file to retrieve object
     * @return        object from file
     */
    public static Object deserialize() {
        Object object = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            // fin = new FileInputStream("saves/" + saveNum + "/" + filename + ".save");
            fin = new FileInputStream(getSave());
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

    // public Address deserialzeAddressJDK7(String filename) {
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
