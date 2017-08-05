import java.io.File;
import file.ReadObject;
import file.WriteObject;

public class SaveTest {

	public static void main(String[] args) {
		WriteObject wo = new WriteObject(0);
		ReadObject ro = new ReadObject(0);

		String inString = "Banana";
		String outString;

		Integer inInt = new Integer(12);
		Integer outInt;

		// System.out.println("wo.serialize(inString);");
		wo.serialize(inString);
		wo.setSaveNum(32);

		// System.out.println("wo.serialize(inInt);");
		wo.serialize(inInt);

		// System.out.println("ro.deserialize(\"String\")");
		outString = (String) ro.deserialize("String");
		ro.setSaveNum(32);

		// System.out.println("ro.deserialize(\"Integer\")");
		outInt = (Integer) ro.deserialize("Integer");

		System.out.println(outString);
		System.out.println(outInt);
	}
}
