import java.util.*;

import game.system.*;

public class Test extends TextUtility {
    public static void main(String[] args) {
        Test t = new Test();
        ArrayList<String> inList = new ArrayList<String>(Arrays.asList("one","asdfasdfh", "otmoaso asd fha ", "fo", "ash"));

        System.out.println("Before: " + inList);

        t.sortByLongest(inList);
        

        System.out.println("After: " + inList);
    }
}
