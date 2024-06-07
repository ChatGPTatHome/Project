package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.FileFilter;

/**
 * Wrapper for java.io.File that keeps track of a
 * root and current directory where root is hard-coded
 * at ./src/data
 * 
 * @author Hai Duong
 * @author Jeremiah Brenio - Two methods coded.
 */
public class Folder {
    private File rootDirectory;
    private File currentDirectory;
    private File[] listFilesOutput;
    private FileFilter filter;

    /**
     * Constructs a Folder object.
     * 
     * @author Hai Duong
     */
    public Folder() {
        this.rootDirectory = new File("src/data/");
        this.currentDirectory = this.rootDirectory;
        this.listFilesOutput = null;
        this.filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return true;
            }
        };
    }

    /**
     * Sets the filter so Folder.list() filters its output.
     * 
     * @param filter the filter.
     * @author Hai Duong
     */
    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }

    /**
     * Lists all files and folders in the current directory.
     * 
     * @return the files and folders in current directory.
     * @author Hai Duong
     */
    public File[] listFiles() {
        if (!this.isDirectory())
            return new File[] {};

        this.listFilesOutput = this.currentDirectory.listFiles(this.filter);
        Arrays.sort(this.listFilesOutput, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int folderComparison = 0;
                if (o1.isDirectory())
                    folderComparison--;
                if (o2.isDirectory())
                    folderComparison++;

                return (folderComparison == 0) ? o1.compareTo(o2) : folderComparison;
            }
        });
        return this.listFilesOutput;
    }

    /**
     * Checks if the current directory is a folder.
     * 
     * @return true if current directory is folder, false otherwise.
     * @author Hai Duong
     */
    public boolean isDirectory() {
        return this.currentDirectory.isDirectory();
    }

    /**
     * Goes into the given directory. If the current File is not a directory,
     * this method returns the current File object instead of null.
     * 
     * @param target the directory/file to go into.
     * @return the File object if the current File is not a directory. otherwise null.
     * @author Hai Duong
     */
    public File goNext(String target) {
        this.listFilesOutput = null;

        File nextFile = new File(this.currentDirectory, target);
        if (!nextFile.exists())
            throw new IllegalArgumentException("Directory/File does not exist");
        this.currentDirectory = nextFile;

        return !this.isDirectory() ? this.getCurrentFileObject() : null;
    }

    /**
     * Goes into the given directory. If the current File is not a directory,
     * this method returns the current File object instead of null.
     * 
     * @param index the directory/file index to go into.
     * @return the File object if the current File is not a directory. otherwise null.
     * @author Hai Duong
     */
    public File goNext(int index) {
        File nextFile = this.listFiles()[index];
        if (!nextFile.exists())
            throw new IllegalArgumentException("Directory/File does not exist");
        this.currentDirectory = nextFile;

        this.listFilesOutput = null;

        return !this.isDirectory() ? this.getCurrentFileObject() : null;
    }

    /**
     * Creates a folder with the given name.
     * 
     * @param directory
     * @author Hai Duong
     */
    public void createFolder(String directory) {
        this.listFilesOutput = null;
        File childDirectory = new File(this.currentDirectory, directory);
        if (!childDirectory.exists())
            childDirectory.mkdir();
    }

    /**
     * Resets the current directory to the root directory.
     * 
     * @author Hai Duong
     */
    public void goRoot() {
        this.listFilesOutput = null;
        this.currentDirectory = this.rootDirectory;
    }

    /**
     * Goes to parent directory if the current directory
     * is not already the root directory.
     * 
     * @author Hai Duong
     */
    public void goBack() {
        if (this.currentDirectory.equals(this.rootDirectory))
            return;

        this.listFilesOutput = null;

        this.currentDirectory = this.currentDirectory.getParentFile();
    }

    /**
     * Gets the current directory as File object.
     * 
     * @return the current File object.
     * @author Hai Duong
     */
    public File getCurrentFileObject() {
        return this.currentDirectory;
    }

    /**
     * Sets the current file object.
     *
     * @param file The new current file object.
     * @author Jeremiah Brenio
     */
    public void setCurrentFileObject(File file) {
        this.currentDirectory = file;
    }

    /**
     * Sets the current file object.
     *
     * @param path The filename of the new current file object.
     * @author Jeremiah Brenio
     */
    public void setCurrentFileObject(String path) {
        this.currentDirectory = new File(this.currentDirectory, path);
    }

    /**
     * Gets a file object from within the current directory.
     * 
     * @param item the name (including extension) of the file object to get.
     * @return the obtained file object
     * @author Hai Duong
     */
    public File getChildFileObject(String item) {
        return new File(this.currentDirectory, item);
    }

    /**
     * Gets a file object from within the current directory.
     * 
     * @param index the index (based on Folder.list) of the file object to get.
     * @return the obtained file object
     * @author Hai Duong
     */
    public File getChildFileObject(int index) {
        return this.listFiles()[index];
    }

    /**
     * Creates a completely empty file with json extension.
     * 
     * @param projectName the file name.
     * @return null
     * @author Hai Duong
     */
    public FileWriter createProject(String projectName) {
        return this.createProject(projectName, true);
    }

    /**
     * Creates a project and returns a FileWriter if it is not closed.
     * 
     * @param projectName The name of the project.
     * @param closeWriter Whether or not to close the FileWriter.
     * @return The FileWriter if it is not closed. Null otherwise
     * @author Hai Duong
     */
    public FileWriter createProject(String projectName, boolean closeWriter) {
        File projectFile = new File(this.currentDirectory, projectName + ".json");
        try {
            projectFile.createNewFile();
            FileWriter writer = new FileWriter(projectFile);
            if (closeWriter) {
                writer.close();
                return null;
            }
            return writer;
        } catch (IOException err) {
            return null;
        }
    }

    /**
     * Deletes the given file within the current directory.
     * 
     * @param item the name of the file (including extension if any).
     * @author Hai Duong
     */
    public void deleteFile(String item) {
        Folder.deleteFile(new File(this.currentDirectory, item));
    }

    /**
     * Deletes the given file within the current directory.
     * 
     * @param index the index of the file (based on Folder.list).
     * @author Hai Duong
     */
    public void deleteFile(int index) {
        Folder.deleteFile(this.listFiles()[index]);
    }

    /**
     * Recursively deletes all files in a directory and
     * deletes the directory itself. If the given file
     * is just a file then that file will be deleted.
     * 
     * @param file the file or directory to delete
     * @author Hai Duong
     */
    private static void deleteFile(File file) {
        if (file.delete() || !file.exists())
            return;

        for (File innerFile : file.listFiles())
            Folder.deleteFile(innerFile);

        file.delete();
    }
}
