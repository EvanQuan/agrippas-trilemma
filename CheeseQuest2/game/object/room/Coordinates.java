package game.object.room;

/**
 * Coordinates represent 3D space
 * x = west - east
 * y = down - up
 * z = south - north
 */
public class Coordinates {
	private Room northRoom;
	private Room eastRoom;
	private Room westRoom;
	private Room southRoom;
	private Room upRoom;
	private Room downRoom;

	public Coordinates() {
	}

	/**
	* Returns value of northRoom
	* @return
	*/
	public Room getNorthRoom() {
		return northRoom;
	}

	/**
	* Returns value of eastRoom
	* @return
	*/
	public Room getEastRoom() {
		return eastRoom;
	}

	/**
	* Returns value of westRoom
	* @return
	*/
	public Room getWestRoom() {
		return westRoom;
	}

	/**
	* Returns value of southRoom
	* @return
	*/
	public Room getSouthRoom() {
		return southRoom;
	}

	/**
	* Returns value of upRoom
	* @return
	*/
	public Room getUpRoom() {
		return upRoom;
	}

	/**
	* Returns value of downRoom
	* @return
	*/
	public Room getDownRoom() {
		return downRoom;
	}

	/**
	* Sets new value of northRoom
	* @param
	*/
	public void setNorthRoom(Room northRoom) {
		this.northRoom = northRoom;
	}

	/**
	* Sets new value of eastRoom
	* @param
	*/
	public void setEastRoom(Room eastRoom) {
		this.eastRoom = eastRoom;
	}

	/**
	* Sets new value of westRoom
	* @param
	*/
	public void setWestRoom(Room westRoom) {
		this.westRoom = westRoom;
	}

	/**
	* Sets new value of southRoom
	* @param
	*/
	public void setSouthRoom(Room southRoom) {
		this.southRoom = southRoom;
	}

	/**
	* Sets new value of upRoom
	* @param
	*/
	public void setUpRoom(Room upRoom) {
		this.upRoom = upRoom;
	}

	/**
	* Sets new value of downRoom
	* @param
	*/
	public void setDownRoom(Room downRoom) {
		this.downRoom = downRoom;
	}

	/**
	* Create string representation of Coordinates for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Coordinates [northRoom=" + northRoom + ", eastRoom=" + eastRoom + ", westRoom=" + westRoom + ", southRoom=" + southRoom + ", upRoom=" + upRoom + ", downRoom=" + downRoom + "]";
	}
}
