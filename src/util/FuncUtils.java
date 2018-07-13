package util;

/**
 * Has utility functions for objects
 *
 * @author Quan
 *
 */
public abstract class FuncUtils {

    /**
     *
     * @param one
     * @param two
     * @return true if both objects equal each other, or if both are null
     */
    public static boolean nullablesEqual(Object one, Object two) {
        return (one == null && two == null) || (one != null && two != null && one.equals(two));
    }

}
