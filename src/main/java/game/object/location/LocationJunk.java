package game.object.location;

// public class LocationJunk {

// private Location northRoom;
// private Location eastRoom;
// private Location westRoom;
// private Location southRoom;
// private Location upRoom;
// private Location downRoom;
// private boolean northBlocked;
//
// private boolean eastBlocked;
// private boolean westBlocked;
// private boolean southBlocked;
// private boolean upBlocked;
// private boolean downBlocked;
// private String defaultBlockedReason;
//
// private String northBlockedReason;
// private String eastBlockedReason;
// private String westBlockedReason;
// private String southBlockedReason;
// private String upBlockedReason;
// private String downBlockedReason;
//
// public LocationJunk() {
// northBlocked = false;
// eastBlocked = false;
// westBlocked = false;
// southBlocked = false;
//
// defaultBlockedReason = "which is blocked";
// northBlockedReason = defaultBlockedReason;
// eastBlockedReason = defaultBlockedReason;
// westBlockedReason = defaultBlockedReason;
// southBlockedReason = defaultBlockedReason;
// upBlockedReason = defaultBlockedReason;
// downBlockedReason = defaultBlockedReason;
// }
//
// public String getDownBlockedReason() {
// return downBlockedReason;
// }
//
// /**
// * Returns value of downRoom
// *
// * @return
// */
// public Location getDownRoom() {
// return downRoom;
// }
//
// public String getEastBlockedReason() {
// return eastBlockedReason;
// }
//
// /**
// * Returns value of eastRoom
// *
// * @return
// */
// public Location getEastRoom() {
// return eastRoom;
// }
//
// public String getNorthBlockedReason() {
// return northBlockedReason;
// }
//
// /**
// * Returns value of northRoom
// *
// * @return
// */
// public Location getNorthRoom() {
// return northRoom;
// }
//
// public String getSouthBlockedReason() {
// return southBlockedReason;
// }
//
// /**
// * Returns value of southRoom
// *
// * @return
// */
// public Location getSouthRoom() {
// return southRoom;
// }
//
// public String getUpBlockedReason() {
// return upBlockedReason;
// }
//
// /**
// * Returns value of upRoom
// *
// * @return
// */
// public Location getUpRoom() {
// return upRoom;
// }
//
// public String getWestBlockedReason() {
// return westBlockedReason;
// }
//
// /**
// * Returns value of westRoom
// *
// * @return
// */
// public Location getWestRoom() {
// return westRoom;
// }
//
// public boolean hasDownRoom() {
// return downRoom != null;
// }
//
// public boolean hasEastRoom() {
// return eastRoom != null;
// }
//
// public boolean hasNorthRoom() {
// return northRoom != null;
// }
//
// public boolean hasSouthRoom() {
// return southRoom != null;
// }
//
// public boolean hasUpRoom() {
// return upRoom != null;
// }
//
// public boolean hasWestRoom() {
// return westRoom != null;
// }
//
// public boolean isAbove(Location room) {
// return (this.equals(room.upRoom));
// }
//
// public boolean isBelow(Location room) {
// return (this.equals(room.downRoom));
// }
//
// public boolean isDownBlocked() {
// return downBlocked;
// }
//
// public boolean isEastBlocked() {
// return eastBlocked;
// }
//
// public boolean isEastOf(Location room) {
// return (this.equals(room.eastRoom));
// }
//
// /**
// * Returns value of northBlocked
// *
// * @return
// */
// public boolean isNorthBlocked() {
// return northBlocked;
// }
//
// public boolean isNorthOf(Location room) {
// return (this.equals(room.northRoom));
// }
//
// public boolean isSouthBlocked() {
// return southBlocked;
// }
//
// public boolean isSouthOf(Location room) {
// return (this.equals(room.southRoom));
// }
//
// public boolean isUpBlocked() {
// return upBlocked;
// }
//
// public boolean isWestBlocked() {
// return westBlocked;
// }
//
// public boolean isWestOf(Location room) {
// return (this.equals(room.westRoom));
// }
//
// /**
// * Output adjacent rooms
// */
// public void outputAdjacentRooms() {
// if (hasNorthRoom()) {
// outputNorthRoom();
// }
// if (hasEastRoom()) {
// outputEastRoom();
// }
// if (hasWestRoom()) {
// outputWestRoom();
// }
// if (hasSouthRoom()) {
// outputSouthRoom();
// }
// if (hasUpRoom()) {
// outputUpRoom();
// }
// if (hasDownRoom()) {
// outputDownRoom();
// }
// }
//
// /**
// * Output description of the room First call is different from subsequent
// calls
// */
// public void outputDescription() {
// printTitleln(getSingleName());
// if (visited) {
// outputFirstDecription();
// visited = false;
// } else {
// outputRepeatDescription();
// }
// }
//
// public void outputDirectionRoom(String direction, Location room) {
// printDirection(direction);
// print(" is ");
// printLocation(room.getAdjacentName());
// }
//
// public void outputDownRoom() {
// outputDirectionRoom("Down", downRoom);
// if (isDownBlocked()) {
// print(", " + downBlockedReason);
// }
// println(".");
// }
//
// public void outputEastRoom() {
// outputDirectionRoom("East", eastRoom);
// if (isEastBlocked()) {
// print(", " + eastBlockedReason);
// }
// println(".");
// }
//
// /**
// * First description defaults to be same as repeated description
// *
// * @return
// */
// public void outputFirstDecription() {
// outputRepeatDescription();
// }
//
// /**
// * Output items in room
// */
// public void outputItems() {
// // TODO
// if (!items.isEmpty()) {
// print("There is ");
// int i = 0; // Iterator used for establishing last item in inventory
// int count;
// for (Collectible item : items.getObjectSet()) {
// count = items.getCount(item);
// if (count == 1) {
// print("a");
// if (startsWithVowel(item.getSingleName())) {
// print("n");
// }
// printItem(" " + item.getSingleName());
// } else {
// print(Integer.toString(count));
// printItem(" " + item.getPluralName());
// }
// if ((i == items.getObjectCount() - 2) && (items.getObjectCount() > 1)) { //
// second last
// print(" and ");
// } else if (i == items.getObjectCount() - 1) { // last
// println(" here.");
// } else {
// print(", ");
// }
// i++;
// }
// }
// }
//
// public void outputNorthRoom() {
// outputDirectionRoom("North", northRoom);
// if (isNorthBlocked()) {
// print(", " + northBlockedReason);
// }
// println(".");
//
// }
//
// public void outputRepeatDescription() {
// println("There is nothing particular about your surroundings.");
// }
//
// public void outputSouthRoom() {
// outputDirectionRoom("South", southRoom);
// if (isSouthBlocked()) {
// print(", " + southBlockedReason);
// }
// println(".");
// }
//
// public void outputUpRoom() {
// outputDirectionRoom("Up", upRoom);
// if (isUpBlocked()) {
// print(", " + upBlockedReason);
// }
// println(".");
// }
//
// public void outputWestRoom() {
// outputDirectionRoom("West", westRoom);
// if (isWestBlocked()) {
// print(", " + westBlockedReason);
// }
// println(".");
// }
//
// public void setDownBlocked(boolean downBlocked) {
// this.downBlocked = downBlocked;
// }
//
// public void setDownBlockedReason(String downBlockedReason) {
// this.downBlockedReason = downBlockedReason;
// }
//
// /**
// * Sets new value of downRoom
// *
// * @param
// */
// public void setDownRoom(Location downRoom) {
// this.downRoom = downRoom;
// }
//
// public void setEastBlocked(boolean eastBlocked) {
// this.eastBlocked = eastBlocked;
// }
//
// public void setEastBlockedReason(String eastBlockedReason) {
// this.eastBlockedReason = eastBlockedReason;
// }
//
// /**
// * Sets new value of eastRoom
// *
// * @param
// */
// public void setEastRoom(Location eastRoom) {
// this.eastRoom = eastRoom;
// }
//
// public void setNorthBlocked(boolean northBlocked) {
// this.northBlocked = northBlocked;
// }
//
// public void setNorthBlockedReason(String northBlockedReason) {
// this.northBlockedReason = northBlockedReason;
// }
//
// /**
// * Sets new value of northRoom
// *
// * @param
// */
// public void setNorthRoom(Location northRoom) {
// this.northRoom = northRoom;
// }
//
// public void setSouthBlocked(boolean southBlocked) {
// this.southBlocked = southBlocked;
// }
//
// public void setSouthBlockedReason(String southBlockedReason) {
// this.southBlockedReason = southBlockedReason;
// }
//
// /**
// * Sets new value of southRoom
// *
// * @param
// */
// public void setSouthRoom(Location southRoom) {
// this.southRoom = southRoom;
// }
//
// public void setUpBlocked(boolean upBlocked) {
// this.upBlocked = upBlocked;
// }
//
// public void setUpBlockedReason(String upBlockedReason) {
// this.upBlockedReason = upBlockedReason;
// }
//
// /**
// * Sets new value of upRoom
// *
// * @param
// */
// public void setUpRoom(Location upRoom) {
// this.upRoom = upRoom;
// }
//
// public void setWestBlocked(boolean westBlocked) {
// this.westBlocked = westBlocked;
// }
//
// public void setWestBlockedReason(String westBlockedReason) {
// this.westBlockedReason = westBlockedReason;
// }
//
// /**
// * Sets new value of westRoom
// *
// * @param
// */
// public void setWestRoom(Location westRoom) {
// this.westRoom = westRoom;
// }
// }
