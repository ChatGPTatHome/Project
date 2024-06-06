package tests;

import model.About;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the About class.
 * This class ensures that the About class correctly initializes
 * and updates its properties such as version, group name, and developers list.
 * Each test checks specific aspects of the About class after default initialization
 * and after calling the update method.
 *
 * @author Windie Le
 */
class AboutTest {

    private About about;

    /**
     * Sets up the test environment before each test.
     * This method initializes the About object that will be used in each test.
     */
    @BeforeEach
    void setUp() {
        about = new About();
    }

    /**
     * Tests the default constructor of the About class.
     * Verifies that the newly created About object has the expected default values for its properties.
     */
    @Test
    void testDefaultConstructor() {
        assertEquals(0.0, about.getVersion(), "Default version should be 0.0");
        assertEquals("N/A", about.getGroupName(), "Default group name should be N/A");
        assertTrue(about.getDevelopers().length == 0, "Default developers array should be empty");
    }

    /**
     * Tests the updateAbout method in the About class.
     * After invoking this method, verifies that all About properties are updated to expected values,
     * including version, group name, and developers list.
     */
    @Test
    void testUpdateAboutSuccessful() {

        about.updateAbout();

        assertEquals(1.0, about.getVersion(), "Version should be updated to 1.0");
        assertEquals("ChatGPTatHome", about.getGroupName(), "Group name should be updated to ChatGPTatHome");
        assertEquals(4, about.getDevelopers().length, "Developers array should have 4 entries");
        assertEquals("Anthony Chapkin", about.getDevelopers()[0], "First developer should be Anthony Chapkin");
        assertEquals("Hai Duong", about.getDevelopers()[1], "Second developer should be Hai Duong");
        assertEquals("Jeremiah Brenio", about.getDevelopers()[2], "Third developer should be Jeremiah Brenio");
        assertEquals("Windie Le", about.getDevelopers()[3], "Fourth developer should be Windie Le");
    }

    /**
     * Tests the version property of the About class after the updateAbout method is called.
     * Verifies the version is updated correctly.
     */
    @Test
    public void testVersion() {
        about.updateAbout();
        assertEquals(1.0, about.getVersion(), "Version should be 1.0");
    }

    /**
     * Tests the developers property of the About class after the updateAbout method is called.
     * Verifies the developers list is updated correctly and matches the expected array.
     */
    @Test
    public void testDevelopers() {
        about.updateAbout();
        String[] expectedDevelopers = {"Anthony Chapkin", "Hai Duong", "Jeremiah Brenio", "Windie Le"};
        assertArrayEquals(expectedDevelopers, about.getDevelopers(), "Developers array should match the expected value");
    }

    /**
     * Tests the group name property of the About class after the updateAbout method is called.
     * Verifies the group name is updated correctly.
     */
    @Test
    public void testGroupName() {
        about.updateAbout();
        assertEquals("ChatGPTatHome", about.getGroupName(), "Group name should be 'ChatGPTatHome'");
    }

}



