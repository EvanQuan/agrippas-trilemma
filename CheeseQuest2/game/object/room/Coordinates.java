package game.object.room;

/**
 * Coordinates represent 3D space
 * x = west - east
 * y = down - up
 * z = south - north
 */
public class Coordinates {
		private int x;
		private int y;
		private int z;

		public Coordinates(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		public int getX() {
			return(x);
		}
		public int getY() {
			return(y);
		}
		public int getZ() {
			return(z);
		}
		public String toString() {
			String s = x + " " + y + " " + z;
			return(s);
		}
	}
