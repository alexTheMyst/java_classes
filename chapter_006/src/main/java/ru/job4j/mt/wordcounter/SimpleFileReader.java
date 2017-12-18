package ru.job4j.mt.wordcounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads the file with scanner.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 18.12.2017
 */
public class SimpleFileReader {
    /**
     * Path to file as a string.
     */
    private final String pathToFile;

    /**
     * Scanner instance.
     */
    private Scanner scanner;

    /**
     * Constructor.
     *
     * @param pathToFile some path
     */
    public SimpleFileReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    /**
     * Reads the file.
     *
     * @return list of strings from file.
     */
    public List<String> readFile() {
        List<String> result = new ArrayList<>();
        openFile();
        while (this.scanner.hasNext()) {
            result.add(this.scanner.nextLine());
        }
        return result;
    }

    /**
     * Opens file for give path.
     */
    private void openFile() {
        try {
            this.scanner = new Scanner(Files.newBufferedReader(Paths.get(this.pathToFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
