package filesprocessing.sourcedirHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a factory that creates a file array froma directory
 *
 * @author guyna25
 */

public class FileArrayFactory {

    /**
     * The FIle object of the directory to be used
     */

    private File directory;

    /**
     * Constructor for this class
     *
     * @param pathToDirectory the path to the cirectory which contains the files
     */

    public FileArrayFactory(String pathToDirectory) {
        directory = new File(pathToDirectory);
    }

    /**
     * This method creates an array of files from a given directory
     *
     * @return An array of files
     */

    public ArrayList<File> createFileArray() {
        if (directory.isDirectory()) {
            ArrayList<File> returnedFileArray = new ArrayList<>();
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile()) {
                    returnedFileArray.add(file);
                }
            }
            return returnedFileArray;
        }
        System.err.println("ERROR: File isn't a directory");
        return null;
    }
}
