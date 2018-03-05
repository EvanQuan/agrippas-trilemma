package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import game.object.location.Exit;
import game.object.location.Location;

/**
 *
 * @author Evan Quan
 * @since March 4, 2018
 *
 */
public class ExitTest {

    private static Location westRoom;
    private static Location eastRoom;
    private static String westRoomName = "West Room";
    private static String eastRoomName = "East Room";
    private static String westRoomDescription = "This is west of the East Room";
    private static String eastRoomDescription = "This is east of the West Room";

    @Before
    public void setUp() throws Exception {
        westRoom = new Location(westRoomName, westRoomDescription);
        eastRoom = new Location(eastRoomName, eastRoomDescription);
    }

    /**
     * Check that exits can be instantiated and the rooms mutually connected
     */
    @Test
    public void testAddDefaultExit() {
        // Create and add exit
        Exit westExit = Exit.getExit(Exit.TYPE_DEFAULT, Exit.KIND_DEFAULT, Exit.DIRECTION_EAST, eastRoom);
        westRoom.addExit(westExit);

        // Retrieve exit and exit information
        Exit exit = westRoom.getExits().get(0);
        assertNotNull(exit);
        int direction = exit.getDirection();
        assertEquals(Exit.DIRECTION_EAST, direction);

        // Retrieve exit location and information
        Location room = exit.getLeadsTo();
        assertNotNull(room);
        assertEquals(eastRoomName, room.getSingleName());
        assertEquals(eastRoomDescription, room.getDescription());
    }

    /**
     * Check thats mutual exits can be created automatically through addMutualExit()
     */
    @Test
    public void testAddMutualDefaultExit() {
        westRoom.addMutualExit(Exit.TYPE_DEFAULT, Exit.KIND_DEFAULT, Exit.DIRECTION_EAST, eastRoom);

        // Retrieve west exit information
        Exit westExit = westRoom.getExits().get(0);
        assertNotNull(westExit);
        int eastDirection = westExit.getDirection();
        assertEquals(Exit.DIRECTION_EAST, eastDirection);

        // Retrieve west exit location information
        Location shouldBeEastRoom = westExit.getLeadsTo();
        assertNotNull(shouldBeEastRoom);
        assertEquals(eastRoom, shouldBeEastRoom);
        assertEquals(eastRoomName, shouldBeEastRoom.getSingleName());
        assertEquals(eastRoomDescription, shouldBeEastRoom.getDescription());

        // Retrieve east exit information
        Exit eastExit = eastRoom.getExits().get(0);
        assertNotNull(eastExit);
        int westDirection = eastExit.getDirection();
        assertEquals(Exit.DIRECTION_WEST, westDirection);

        // Retrieve east exit location information
        Location shouldBeWestRoom = eastExit.getLeadsTo();
        assertNotNull(shouldBeWestRoom);
        assertEquals(westRoom, shouldBeWestRoom);
        assertEquals(westRoomName, shouldBeWestRoom.getSingleName());
        assertEquals(westRoomDescription, shouldBeWestRoom.getDescription());
    }

}
