package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Folder {

    // File already has getpath and getdir
    private File rootDirectory;
    private File currentDirectory;

    public Folder() {
        this.rootDirectory = new File(System.getProperty("user.dir"), "data");
        this.currentDirectory = rootDirectory;
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
