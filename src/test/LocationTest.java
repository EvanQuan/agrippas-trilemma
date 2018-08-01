package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.object.location.Location;

/**
 *
 * @author Evan Quan
 *
 */
public class LocationTest {

    private static Location l;
    private static String defaultSingleName = "Primary single name";
    private static String defaultDescription = "Primary description";

    @Before
    public void setUp() {
        l = new Location(defaultSingleName, defaultDescription);
    }

    /**
     *
     */
    @Test
    public void testAddSingleName() {
        String addedName = "Added name";
        l.addSingleName(addedName);
        String primaryName = l.getSingleName();
        String addedNameResult = l.getSingleName(1);
        assertEquals(defaultSingleName, primaryName);
        assertEquals(addedName, addedNameResult);
    }

    /**
     *
     */
    @Test
    public void testGetDescriptionDefault() {
        assertEquals(defaultDescription, l.getDescription());
    }

    /**
     * Check if can get default name
     */
    @Test
    public void testGetSingleNameDefault() {
        assertEquals(defaultSingleName, l.getSingleName());
    }

    /**
     * Check that descriptions can be iterated over forwards
     */
    @Test
    public void testSetNextDescription() {
        String d1 = "1";
        String d2 = "2";
        l.addDescription(d1);
        l.addDescription(d2);
        assertEquals(defaultDescription, l.getDescription());
        l.setNextDescription();
        assertEquals(d1, l.getDescription());
        l.setNextDescription();
        assertEquals(d2, l.getDescription());
        l.setNextDescription();
        assertEquals(d2, l.getDescription());
    }

    /**
     * Check that descriptions can be iterated over backwards
     */
    @Test
    public void testSetPreviousDescription() {
        String d1 = "1";
        String d2 = "2";
        l.addDescription(d1);
        l.addDescription(d2);
        l.setNextDescription(); // d1
        l.setNextDescription(); // d2
        assertEquals(d2, l.getDescription());
        l.setPreviousDescription(); // d1
        assertEquals(d1, l.getDescription());
        l.setPreviousDescription(); // default
        assertEquals(defaultDescription, l.getDescription());
        l.setPreviousDescription();
        assertEquals(defaultDescription, l.getDescription());
    }

}
