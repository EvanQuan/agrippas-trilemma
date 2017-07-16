public class Room {
    private Coordinates roomCoordinates;

    public Room() {
        System.out.println("Room needs coordinates");
    }
    public Room(int x, int y, int z) {
        Coordinates(x,y,z);
    }
    public Coordinates getCoordinates() {
        return(roomCoordinates);
    }
}

// Wrapper class
public class Coordinates {
    private int x;
    private int y;
    private int z;
    public Coordinates() {
        System.out.println("Coordinates need values");
    }
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
