public class Item {
	public static String DESCRIPTION;
	public static String[] NAME;
	public static String[] NAMES;
	private int count;
	public Item() {
		count = 1;
	}
	public Item(int inCount) {
		setCount(inCount);
	}
	public int getCount() {
		return(count);
	}
	public String getDescription() {
		return(DESCRIPTION);
	}
	public String getName() {
		return(NAME);
	}
	public String getNames() {
		return(NAMES);
	}
	public void addCount(int inCount) {
		if ((count + inCount) < 0) {
			count = 0;
		} else {
			count += inCount;
		}
	}
	public void setCount(int inCount) {
		if (inCount >= 0) {
			count = inCount;
		}
	}
}
