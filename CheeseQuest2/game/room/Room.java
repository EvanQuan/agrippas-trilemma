package room;

import java.util.HashMap;
import item.*;
import person.*;
import system.*;

public class Room {
	private Coordinates coordinates;
	private Inventory inv;

	public Room(int x, int y, int z) {
		coordinates = new Coordinates(x,y,z);
	}
	public Coordinates getCoordinates() {
		return(roomCoordinates);
	}

	public boolean isNorthOf(Coordinates inCoordinates) {
		return(coordinates.getZ() > inCoordinates.getZ());
	}
	public boolean isSouthOf(Coordinates inCoordinates) {
		return(coordinates.getZ() < inCoordinates.getZ());
	}
	public boolean isEastOf(Coordinates inCoordinates) {
		return(coordinates.getX() > inCoordinates.getX());
	}
	public boolean isWestOf(Coordinates inCoordinates) {
		return(coordinates.getX() < inCoordinates.getX());
	}
	public boolean isTopOf(Coordinates inCoordinates) {
		return(coordinates.getY() > inCoordinates.getY());
	}
	public boolean equals(Coordinates inCoordinates) {
		return(
			coordinates.getX() == inCoordinates.getX() &&
			coorindates.getY() == inCoordinates.getY() &&
			coordinates.getZ() == inCoordinates.getZ()
		);
	}
}
