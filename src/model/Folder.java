package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.FileFilter;

public class Folder {
    private File rootDirectory;
    private File currentDirectory;
    private File[] currentListDirectory;
    private FileFilter filter;

    public Folder() {
        this.rootDirectory = new File("src/data/");
        this.currentDirectory = this.rootDirectory;
        this.currentListDirectory = null;
        this.filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return true;
            }
        };
    }

    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }

    public File[] list() {
        if (this.currentListDirectory == null) {
            this.currentListDirectory = this.currentDirectory.listFiles(this.filter);
            Arrays.sort(this.currentListDirectory, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    int folderComparison = 0;
                    if (o1.isDirectory())
                        folderComparison = 1;
                    if (o2.isDirectory())
                        folderComparison = -1;

                    return (folderComparison == 0) ? o1.compareTo(o2) : folderComparison;
                }
            });
        }
        return this.currentListDirectory;
    }

    public boolean isDirectory() {
        return this.currentDirectory.isDirectory();
    }

    public boolean enterDirectory(String directory) {
        this.currentListDirectory = null;
        this.currentDirectory = new File(this.currentDirectory, directory);
        return this.currentDirectory.exists();
    }

    public void createFolder() {
        if (!this.currentDirectory.exists())
            this.currentDirectory.mkdir();
    }

    public void createFolder(String directory) {
        this.enterDirectory(directory);
        this.createFolder();
    }

    public void resetDirectory() {
        this.currentListDirectory = null;
        this.currentDirectory = this.rootDirectory;
    }

    public void goBackDirectory() {
        this.currentListDirectory = null;
        if (this.currentDirectory.equals(this.rootDirectory))
            return;

        this.currentDirectory = this.currentDirectory.getParentFile();
    }

    public File getCurrentFileObject() {
        return this.currentDirectory;
    }

    public File getFileObject(String item) {
        return new File(this.currentDirectory, item);
    }

    public File getFileObject(int index) {
        return this.list()[index];
    }

    public FileWriter createProject(String projectName) {
        return this.createProject(projectName, true);
    }

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
}
