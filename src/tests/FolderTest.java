package tests;

import model.Folder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Folder class.
 * Tests certain methods from the Folder class.
 * Uses JUnit 5.
 *
 * @author Anthony Chapkin
 */
public class FolderTest {

    /**
        Folder field that is used by all tests.
     **/
    private Folder folder;

    /**
     * Creates a new Folder object for each test to use.
     *
     * @author Anthony Chapkin
     **/
    @BeforeEach
    public void setUp() {
        folder = new Folder();
    }

    /**
     * Tests listFiles with an empty directory.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     * @author Anthony Chapkin
     **/
    @Test
    public void testListFiles1(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());

        final String expected = Arrays.toString(tmpDir.toFile().listFiles());
        final String actual = Arrays.toString(folder.listFiles());

        assertEquals(expected, actual,
                String.format("The directory should contain:%n%s%nThe directory contains:%n%s%n",
                        expected, actual));
    }

    /**
     * Tests listFiles with a directory containing a
     * file and a directory.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     **/
    @Test
    public void testListFiles2(@TempDir Path tmpDir) {
        try {
            Files.createFile(tmpDir.resolve("some-file.txt"));
            Files.createDirectory(tmpDir.resolve("some-dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final String expected = Arrays.toString(tmpDir.toFile().listFiles());
        final String actual = Arrays.toString(folder.listFiles());

        assertEquals(expected, actual,
                String.format("The directory should contain:%n%s%nThe directory contains:%n%s%n",
                        expected, actual));
    }

    /**
     * Tests listFiles with a directory containing a
     * file and a directory, and with a FileFilter that
     * doesn't accept directories.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     **/
    @Test
    public void testListFiles3(@TempDir Path tmpDir) {
        try {
            Files.createFile(tmpDir.resolve("some-file.txt"));
            Files.createDirectory(tmpDir.resolve("some-dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final FileFilter filter = File::isFile;
        folder.setFilter(filter);

        final String expected = Arrays.toString(tmpDir.toFile().listFiles(filter));
        final String actual = Arrays.toString(folder.listFiles());

        assertEquals(expected, actual,
                String.format("The directory should contain:%n%s%nThe directory contains:%n%s%n",
                        expected, actual));
    }

    /**
     * Tests listFiles with a directory containing a
     * file and a directory, and with a FileFilter that
     * only accepts directories.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     **/
    @Test
    public void testListFiles4(@TempDir Path tmpDir) {
        try {
            Files.createFile(tmpDir.resolve("some-file.txt"));
            Files.createDirectory(tmpDir.resolve("some-dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final FileFilter filter = File::isDirectory;
        folder.setFilter(filter);

        final String expected = Arrays.toString(tmpDir.toFile().listFiles(filter));
        final String actual = Arrays.toString(folder.listFiles());

        assertEquals(expected, actual,
                String.format("The directory should contain:%n%s%nThe directory contains:%n%s%n",
                        expected, actual));
    }

    /**
     * Tests that goNext throws an exception if it can't
     * find a File. Passes a String file name to goNext.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoNext1(@TempDir Path tmpDir) {
        try {
            Files.createFile(tmpDir.resolve("some-file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        assertThrows(IllegalArgumentException.class, () -> folder.goNext("some-dir"),
                "IllegalArgumentException was not thrown.%n");
    }

    /**
     * Tests goNext with a txt file. Passes a String file name
     * to goNext.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoNext2(@TempDir Path tmpDir) {
        final Path someFilePath = tmpDir.resolve("some-file.txt");
        try {
            Files.createFile(someFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final File expected = someFilePath.toFile();
        final File actual1 = folder.goNext(someFilePath.toFile().getName());

        assertEquals(expected, actual1,
                String.format("Should return File %s when we don't go a directory." +
                                " Returned %s instead.%n",
                        expected, actual1));

        final File actual2 = folder.getCurrentFileObject();

        assertEquals(expected, actual2,
                String.format("The current File should be %s. Instead it is %s.%n",
                        expected, actual2));
    }

    /**
     * Tests goNext with a directory. Passes a String file name
     * to goNext.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoNext3(@TempDir Path tmpDir) {
        final Path someDirPath = tmpDir.resolve("some-dir");
        try {
            Files.createDirectory(someDirPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final File actual1 = folder.goNext(someDirPath.toFile().getName());

        assertNull(actual1, String.format("Should return null when we go to a Directory." +
                        " Returned %s instead.%n",
                actual1));

        final File expected = someDirPath.toFile();
        final File actual2 = folder.getCurrentFileObject();

        assertEquals(expected, actual2,
                String.format("The current File should be %s. Instead it is %s.%n",
                        expected, actual2));
    }

    /**
     * Tests that goNext throws an exception if it can't
     * find a File. Passes an int index to goNext.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoNext4(@TempDir Path tmpDir) {
        try {
            Files.createFile(tmpDir.resolve("some-file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> folder.goNext(1),
                "ArrayIndexOutOfBoundsException was not thrown.%n");
    }

    /**
     * Tests goNext with a txt file. Passes an int index
     * to goNext.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoNext5(@TempDir Path tmpDir) {
        final Path someFilePath = tmpDir.resolve("some-file.txt");
        try {
            Files.createFile(someFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final File expected = someFilePath.toFile();
        final File actual1 = folder.goNext(0);

        assertEquals(expected, actual1,
                String.format("Should return File %s when we don't go a directory." +
                                " Returned %s instead.%n",
                        expected, actual1));

        final File actual2 = folder.getCurrentFileObject();

        assertEquals(expected, actual2,
                String.format("The current File should be %s. Instead it is %s.%n",
                        expected, actual2));
    }

    /**
     * Tests goNext with a directory. Passes an int
     * index to goNext.
     *
     * @param tmpDir A Path Object to a temporary directory
     *               used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoNext6(@TempDir Path tmpDir) {
        final Path someDirPath = tmpDir.resolve("some-dir");
        try {
            Files.createDirectory(someDirPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());

        final File actual1 = folder.goNext(0);

        assertNull(actual1, String.format("Should return null when we go to a Directory." +
                        " Returned %s instead.%n",
                actual1));

        final File expected = someDirPath.toFile();
        final File actual2 = folder.getCurrentFileObject();

        assertEquals(expected, actual2,
                String.format("The current File should be %s. Instead it is %s.%n",
                        expected, actual2));
    }

    /**
     * Tests createFolder by passing in a String directory
     * name that doesn't yet exist.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateFolder1(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());
        folder.createFolder("some-dir");

        assertTrue(Files.exists(tmpDir.resolve("some-dir")),
                "File was not created.");
        assertTrue(Files.isDirectory(tmpDir.resolve("some-dir")),
                "File is not a directory.");
    }

    /**
     * Tests createFolder by passing in a String directory
     * name that already exists.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateFolder2(@TempDir Path tmpDir) {
        try {
            Files.createDirectory(tmpDir.resolve("some-dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());
        folder.createFolder("some-dir");

        assertDoesNotThrow(() -> folder.createFolder("some-dir"),
                "Should not throw an exception if folder already exists.");
    }

    /**
     * Tests goRoot by first setting the current File
     * to another directory.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoRoot(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());
        folder.goRoot();

        assertEquals(new File("src/data/"), folder.getCurrentFileObject(),
                "Should set the current file back to the data directory.");
    }

    /**
     * Tests goBack by first setting current File
     * to not the root directory.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoBack1(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());
        folder.goBack();

        final File expected = tmpDir.getParent().toFile();
        final File actual = folder.getCurrentFileObject();

        assertEquals(expected, actual,
                String.format("The current File should be %s. Instead it is %s.%n",
                        expected, actual));
    }

    /**
     * Tests goBack by leaving the current File
     * set at the root directory.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testGoBack2() {
        folder.goBack();

        final File expected = new File("src/data");
        final File actual = folder.getCurrentFileObject();

        assertEquals(expected, actual,
                String.format("The current File should be the root %s. Instead it is %s.%n",
                        expected, actual));
    }

    /**
     * Tests createProject by setting current File to a
     * directory, and passing in a String file name.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateProject1(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());
        final FileWriter projectWriter = folder.createProject("some-file");

        assertNull(projectWriter,
                "FileWriter returned should be null if closed.");

        assertTrue(Files.exists(tmpDir.resolve("some-file.json")),
                "Json file does not exist in current directory");

    }

    /**
     * Tests createProject by setting current File to a non-directory file,
     * and passing in a String file name.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateProject2(@TempDir Path tmpDir) {
        final Path someFilePath = tmpDir.resolve("some-file.txt");
        try {
            Files.createFile(someFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(someFilePath.toFile());
        final FileWriter projectWriter = folder.createProject("another-file");

        assertNull(projectWriter,
                "FileWriter returned should be null if closed.");

        assertFalse(Files.exists(someFilePath.resolve("another-file.json")),
                "Json file should not be created in another file.");

    }

    /**
     * Tests createProject by setting current File to a
     * directory, and passing in a String file name and
     * a boolean to not close FileWriter.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateProject3(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());
        final FileWriter projectWriter = folder.createProject("some-file", false);

        assertNotNull(projectWriter,
                "FileWriter returned should be not be null if not closed.");

        assertDoesNotThrow(projectWriter::close,
                "If FileWriter was not closed, we should be able to close it.");

        assertTrue(Files.exists(tmpDir.resolve("some-file.json")),
                "Json file does not exist in current directory");

    }

    /**
     * Tests createProject by setting current File to a non-directory file,
     * and passing in a String file name and a boolean to not close FileWriter.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateProject4(@TempDir Path tmpDir) {
        final Path someFilePath = tmpDir.resolve("some-file.txt");
        try {
            Files.createFile(someFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(someFilePath.toFile());
        final FileWriter projectWriter = folder.createProject("another-file", false);

        assertNull(projectWriter,
                "FileWriter returned should be null if file was not created.");

        assertFalse(Files.exists(someFilePath.resolve("another-file.json")),
                "Json file should not be created in another file.");

    }

    /**
     * Tests createProject by setting current File to a
     * directory, and passing in a String file name and
     * a boolean to close FileWriter.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testCreateProject5(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());
        final FileWriter projectWriter = folder.createProject("some-file", true);

        assertNull(projectWriter,
                "FileWriter returned should be null if closed.");

        assertTrue(Files.exists(tmpDir.resolve("some-file.json")),
                "Json file does not exist in current directory");

    }

    /**
     * Tests deleteFile by using it on a file, and a
     * directory with a nested file. Passes the Strings
     * of the file and directory names.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testDeleteFile1(@TempDir Path tmpDir) {
        final Path someFilePath = tmpDir.resolve("some-file.txt");
        final Path someDirPath = tmpDir.resolve("some-dir");
        try {
            Files.createFile(someFilePath);
            Files.createDirectory(someDirPath);
            Files.createFile(someDirPath.resolve("another-file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());
        folder.deleteFile(someFilePath.toFile().getName());
        folder.deleteFile(someDirPath.toFile().getName());

        assertFalse(Files.exists(someFilePath),
                ".txt file was not deleted.");

        assertFalse(Files.exists(someDirPath),
                "Directory file was not deleted.");

    }

    /**
     * Tests deleteFile by trying to delete a nonexistent
     * file. Passes the String of the file name.
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testDeleteFile2(@TempDir Path tmpDir) {

        folder.setCurrentFileObject(tmpDir.toFile());
        folder.deleteFile(tmpDir.resolve("some-file.txt").toFile().getName());

        assertDoesNotThrow(() -> folder.deleteFile(tmpDir.resolve("some-file.txt").toFile().getName()),
                "Should not throw an exception on attempt to delete a file that does not exist.");

    }

    /**
     * Tests deleteFile by using it on a file, and a
     * directory with a nested file. Passes the int
     * indexes of the file and directory.
     *
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testDeleteFile3(@TempDir Path tmpDir) {
        final Path someFilePath = tmpDir.resolve("some-file.txt");
        final Path someDirPath = tmpDir.resolve("some-dir");
        try {
            Files.createFile(someFilePath);
            Files.createDirectory(someDirPath);
            Files.createFile(someDirPath.resolve("another-file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folder.setCurrentFileObject(tmpDir.toFile());
        folder.deleteFile(1);
        folder.deleteFile(0);

        assertFalse(Files.exists(someFilePath),
                ".txt file was not deleted.");

        assertFalse(Files.exists(someDirPath),
                "Directory file was not deleted.");

    }

    /**
     * Tests deleteFile by trying to delete a nonexistent
     * file at a some index. Passes in an int index.
     * @param tmpDir A Path Object to a temporary directory
     *                used for testing.
     *
     * @author Anthony Chapkin
     */
    @Test
    public void testDeleteFile4(@TempDir Path tmpDir) {
        folder.setCurrentFileObject(tmpDir.toFile());

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> folder.deleteFile(0),
                "Should throw ArrayIndexOutOfBoundsException if index out of bounds.");

    }


}
