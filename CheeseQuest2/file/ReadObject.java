package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.SecurityException;

/**
 * Reads object from file
 * Credits to Mkyong.com
 * https://www.mkyong.com/java/how-to-read-an-object-from-file-in-java/
 */
public class ReadObject {

	private int saveNum;

	public ReadObject(int saveNum) {
		this.saveNum = saveNum;
	}
	/**
	 * Get object from file of filename of saveNum
	 * @param  String filename      name of file to retrieve object
	 * @return        object from file
	 */
	public Object deserialize(String filename) {

		Object object = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {

			fin = new FileInputStream("saves/" + saveNum + "/" + filename + ".save");
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
	// 	Address address = null;
	//
	// 	try (ObjectInputStream ois
	// 		= new ObjectInputStream(new FileInputStream(filename))) {
	//
	// 		address = (Address) ois.readObject();
	//
	// 	} catch (Exception ex) {
	// 		ex.printStackTrace();
	// 	}
	//
	// 	return address;
	//
	// }

	/**
	 * Return VALUE of saveNum
	 * @return
	 */
	public int getSaveNum() {
		return saveNum;
	}

	/**
	 * Sets new value of saveNum
	 * @param
	 */
	public void setSaveNum(int saveNum) {
		this.saveNum = saveNum;
	}
}
