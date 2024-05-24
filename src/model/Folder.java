package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Folder {

    // File already has getpath and getdir
    private File rootDirectory;
    private File currentDirectory;

    public Folder() {
        this.rootDirectory = new File(System.getProperty("user.dir"), "data");
        this.currentDirectory = this.rootDirectory;
    }

    public String[] list() {
        return this.currentDirectory.list();
    }

    public boolean enterDirectory(String directory) {
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
        this.currentDirectory = this.rootDirectory;
    }

    public void goBackDirectory() {
        if (this.currentDirectory.equals(this.rootDirectory))
            return;

        this.currentDirectory = this.currentDirectory.getParentFile();
    }

    public File getFileObject() {
        return this.currentDirectory;
    }

    public File getFileObject(String item) {
        return new File(this.currentDirectory, item);
    }

    public FileWriter createProject(String projectName) {
        File projectFile = new File(this.currentDirectory, projectName + ".json");
        try {
            projectFile.createNewFile();
            return new FileWriter(projectFile);
        } catch (IOException err) {
            return null;
        }
    }
}
