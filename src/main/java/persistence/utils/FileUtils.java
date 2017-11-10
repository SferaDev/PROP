package persistence.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type File controller.
 */
public class FileUtils {
    /**
     * Read from file string.
     *
     * @param path the path
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public static String readFromFile(String path) throws FileNotFoundException {
        return new Scanner(new File(path)).useDelimiter("\\Z").next();
    }

    /**
     * Write to file.
     *
     * @param path    the path
     * @param content the content
     */
    public static void writeToFile(String path, String content) {
        try {
            FileWriter writer = new FileWriter(new File(path), false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create file.
     *
     * @param path the path
     */
    public static void createFile(String path) {
        Path pathToFile = Paths.get(path);
        try {
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * List files array list.
     *
     * @param path the path
     * @return the array list
     */
    public static ArrayList<String> listFiles(String path) {
        ArrayList<String> result = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    result.add(listOfFile.getName());
                }
            }
        }
        return result;
    }

    /**
     * Delete file.
     *
     * @param path the path
     */
    public static void deleteFile(String path) {
        new File(path).delete();
    }
}
