/**
 * Debug
 */
public static class Debug {
	public static boolean on = false;

	public static void toggle() {
		if (on) {
			on = false;
		} else {
			on = true;
		}
	}
}
