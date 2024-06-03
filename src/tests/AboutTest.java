package tests;

import model.About;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AboutTest {

    private About about;

    @BeforeEach
    void setUp() {
        about = new About();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0.0, about.getVersion(), "Default version should be 0.0");
        assertEquals("N/A", about.getGroupName(), "Default group name should be N/A");
        assertTrue(about.getDevelopers().length == 0, "Default developers array should be empty");
    }

    @Test
    void testUpdateAboutSuccessful() throws Exception {

        about.updateAbout();

        assertEquals(1.0, about.getVersion(), "Version should be updated to 1.0");
        assertEquals("ChatGPTatHome", about.getGroupName(), "Group name should be updated to ChatGPTatHome");
        assertEquals(4, about.getDevelopers().length, "Developers array should have 4 entries");
        assertEquals("Anthony Chapkin", about.getDevelopers()[0], "First developer should be Anthony Chapkin");
        assertEquals("Hai Duong", about.getDevelopers()[1], "Second developer should be Hai Duong");
        assertEquals("Jeremiah Brenio", about.getDevelopers()[2], "Third developer should be Jeremiah Brenio");
        assertEquals("Windie Le", about.getDevelopers()[3], "Fourth developer should be Windie Le");
    }

    @Test
    public void testVersion() {
        about.updateAbout();
        assertEquals(1.0, about.getVersion(), "Version should be 1.0");
    }

    @Test
    public void testDevelopers() {
        about.updateAbout();
        String[] expectedDevelopers = {"Anthony Chapkin", "Hai Duong", "Jeremiah Brenio", "Windie Le"};
        assertArrayEquals(expectedDevelopers, about.getDevelopers(), "Developers array should match the expected value");
    }

    @Test
    public void testGroupName() {
        about.updateAbout();
        assertEquals("ChatGPTatHome", about.getGroupName(), "Group name should be 'ChatGPTatHome'");
    }


}



