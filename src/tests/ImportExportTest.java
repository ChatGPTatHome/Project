package tests;

import model.ImportExport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Writer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * This class contains unit tests for the ImportExport class.
 * It tests various methods related to importing and exporting data.
 * The tests cover scenarios such as
 * reading from and writing to a settings.json file,
 * handling exceptions, and verifying the correctness of the data.
 * 
 * @author Jeremiah Brenio
 */
public class ImportExportTest {

    /** Instance of ImportExport class. */
    private ImportExport importExport;

    /** Original content of settings.json file. */
    private static Map<String, String> originalContent;

    /**
     * Preserve the original content of settings.json file.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @BeforeAll
    public static void preserveData() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("./src/data/settings.json");
        originalContent = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
        }.getType());
        reader.close();
    }

    /**
     * Restore the original content of settings.json file.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @AfterAll
    public static void restoreData() throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter("./src/data/settings.json");
        writer.write(gson.toJson(originalContent));
        writer.close();
    }

    /**
     * Set up the test environment.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @BeforeEach
    public void setup() throws IOException {
        importExport = new ImportExport();
    }

    /**
     * Test the default constructor.
     * 
     * @author Jeremiah Brenio
     */
    @Test
    public void testDefaultConstructor() {
        assertEquals("N/A", importExport.getName(), "Should be 'N/A' by default");
        assertEquals("N/A", importExport.getEmail(), "Should be 'N/A' by default");
    }

    /**
     * Create a settings.json file with test data.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @Test
    public void createTestSettings() throws IOException {
        // Manually modify the settings.json file with test data
        String jsonContent = "{\"name\":\"testuser\",\"email\":\"testuser@example.com\"}";
        Writer writer = new FileWriter("./src/data/settings.json");
        writer.write(jsonContent);
        writer.close();
    }

    /**
     * Create a settings.json file with incorrect JSON format.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @Test
    public void createFailSettings() throws IOException {
        // Manually modify the settings.json file to fail
        String jsonContent = "{This is not a correct JSON format}";
        Writer writer = new FileWriter("./src/data/settings.json");
        writer.write(jsonContent);
        writer.close();
    }

    /**
     * Test the pullData method.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @Test
    public void testPullData() throws IOException {
        createTestSettings();
        assertTrue(importExport.pullData("./src/data/settings.json"), "pullData should only read from settings.json");
        assertEquals("testuser", importExport.getName(), "Should be 'testuser'");
        assertEquals("testuser@example.com", importExport.getEmail(), "Should be 'testuser@example.com'");
    }

    /**
     * Test the pullData method with an invalid file path.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testPullDataException1() throws IOException {
        assertFalse(importExport.pullData("./src/"),
                "Should return false because file does not exist");
    }

    /**
     * Test the pullData method with incorrect JSON format.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testPullDataException2() throws IOException {
        createFailSettings();

        // Should be caught by the try-catch block
        importExport.pullData();
        assertEquals("N/A", importExport.getName(), "Should be 'N/A'");
        assertEquals("N/A", importExport.getEmail(), "Should be 'N/A'");
    }

    /**
     * Test the pullData method with an invalid file path.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testPullDataException3() throws IOException {
        createTestSettings();
        importExport.pullData();
        createFailSettings();
        // Should be caught by the try-catch block
        importExport.pullData();
        assertEquals("testuser", importExport.getName(), "Should be 'testuser'");
        assertEquals("testuser@example.com", importExport.getEmail(), "Should be 'testuser@example.com'");
    }

    /**
     * Test the updateSettings method with default data.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testUpdateSettings1() throws IOException {
        importExport.updateSettings();
        assertEquals("N/A", importExport.getName(), "Should be 'N/A'");
        assertEquals("N/A", importExport.getEmail(), "Should be 'N/A'");
        FileReader reader = new FileReader("./src/data/settings.json");
        Gson gson = new Gson();
        Map<String, String> jsonContent = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
        }.getType());
        reader.close();
        assertEquals("N/A", jsonContent.get("name"), "Should be 'N/A'");
        assertEquals("N/A", jsonContent.get("email"), "Should be 'N/A'");
    }

    /**
     * Test the updateSettings method with test data.
     * 
     * @throws IOException if an I/O error occurs.
     * @author Jeremiah Brenio
     */
    @Test
    public void testUpdateSettings2() throws IOException {
        createTestSettings();
        importExport.pullData();
        importExport.updateSettings();
        assertEquals("testuser", importExport.getName(), "Should be 'testuser'");
        assertEquals("testuser@example.com", importExport.getEmail(), "Should be 'testuser@example.com'");
        FileReader reader = new FileReader("./src/data/settings.json");
        Gson gson = new Gson();
        Map<String, String> jsonContent = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
        }.getType());
        reader.close();
        assertEquals("testuser", jsonContent.get("name"), "Should be 'testuser'");
        assertEquals("testuser@example.com", jsonContent.get("email"), "Should be 'testuser@example.com'");
    }

    /**
     * Test the pushData method with default data.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testPushData1() throws IOException {
        File testPath = new File("./src/test");
        testPath.mkdir();
        importExport.pushData(testPath.getPath());

        FileReader reader = new FileReader(testPath.getPath() + "/settings.json");
        Gson gson = new Gson();
        Map<String, String> jsonContent = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
        }.getType());
        assertEquals("N/A", jsonContent.get("name"), "Should be 'N/A'");
        assertEquals("N/A", jsonContent.get("email"), "Should be 'N/A'");
        reader.close();
        File toDelete = new File(testPath.getPath() + "/settings.json");
        toDelete.delete();
        testPath.delete();
    }

    /**
     * Test the pushData method with test data.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testPushData2() throws IOException {
        File testPath = new File("./src/test");
        testPath.mkdir();
        importExport.setName("testuser");
        importExport.setEmail("testuser@example.com");
        importExport.pushData(testPath.getPath());

        FileReader reader = new FileReader(testPath.getPath() + "/settings.json");
        Gson gson = new Gson();
        Map<String, String> jsonContent = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
        }.getType());
        assertEquals("testuser", jsonContent.get("name"), "Should be 'testuser'");
        assertEquals("testuser@example.com", jsonContent.get("email"), "Should be 'testuser@example.com'");
        reader.close();
        File toDelete = new File(testPath.getPath() + "/settings.json");
        toDelete.delete();
        testPath.delete();
    }

    /**
     * Test the pushData method with an invalid file path.
     * 
     * @throws IOException
     * @author Jeremiah Brenio
     */
    @Test
    public void testPushDataException() throws IOException {
        File failFile = new File("nonexistent");
        assertEquals(false, failFile.exists(), "This file should not exist");
        importExport.pushData(failFile.toString());
        File settingsFile = new File(failFile.toString() + "/settings.json");
        assertEquals(false, settingsFile.exists(), "settings.json should not be created");
    }

}
