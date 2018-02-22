import java.io.File;
import file.ReadObject;
import file.WriteObject;
import game.system.*;

public class SaveTest {

    public static void main(String[] args) {
        WriteObject wo = new WriteObject(0);
        ReadObject ro = new ReadObject(0);

        World world1 = new World();
        System.out.println("world1: " + world1);

        System.out.println("Serializing world1...\n\n");
        wo.serialize(world1);

        // System.out.println("ro.deserialize(\"String\")");
        System.out.println("Deserializing world1 into world2...\n\n");
        World world2 = (World) ro.deserialize();
        System.out.println("world2: " + world2);

    }
}
