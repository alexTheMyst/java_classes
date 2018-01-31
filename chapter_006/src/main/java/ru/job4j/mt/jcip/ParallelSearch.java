package ru.job4j.mt.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Search for given text in file system,
 * starting from given root directory
 * in the files with given extensions.
 *
 * @author alexey
 * @version 30.01.18
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * Directory where search started.
     */
    private final String root;
    /**
     * Text to search.
     */
    private final String text;
    /**
     * File extensions to search.
     */
    private final List<String> extensions;
    /**
     * Files where search the text.
     */
    @GuardedBy("this")
    private final List<Path> filesToSearch;

    /**
     * Results store.
     */
    @GuardedBy("this")
    private final List<String> result;

    /**
     * Constructor.
     *
     * @param root       directory here search started
     * @param text       text to find
     * @param extensions files with the extensions to process
     */
    public ParallelSearch(String root, String text, List<String> extensions) {
        this.root = root;
        this.text = text;
        this.extensions = extensions;
        this.filesToSearch = new ArrayList<>();
        this.result = new ArrayList<>();
    }


    /**
     * Results getter.
     *
     * @return results
     */
    public List<String> getResult() throws InterruptedException, IOException {
        findMatchedFiles();
        parseFiles();
        synchronized (this) {
            return result;
        }
    }

    /**
     * Parse all files from filesToSearch
     *
     * @throws InterruptedException some InterruptedException
     */
    private void parseFiles() throws InterruptedException {
        synchronized (this) {
            for (Path path : this.filesToSearch) {
                TextSearcher textSearcher = new TextSearcher(path, this.text, this.result);
                Thread thread = new Thread(textSearcher);
                thread.start();
                thread.join();
            }
        }
    }

    /**
     * Gets all files and filters for given extensions.
     *
     * @throws IOException some IO exception
     */
    private void findMatchedFiles() throws IOException {
        synchronized (this) {
            findAllFiles(root)
                    .filter(path -> Files.isRegularFile(path))
                    .filter(this::fileMatchByExtension)
                    .forEach(this.filesToSearch::add);
        }
    }

    /**
     * Gets stream of all files from all subdirectories.
     *
     * @param startDir directory to start
     * @return stream of all files from all subdirectories
     * @throws IOException some IO exception
     */
    private Stream<Path> findAllFiles(final String startDir) throws IOException {
        return Files.walk(Paths.get(startDir));
    }

    /**
     * Checks that given file extension is in the list.
     *
     * @param path path to file
     * @return true if files extension is in the list or false otherwise
     */
    private boolean fileMatchByExtension(Path path) {
        boolean result = false;
        for (String ext : this.extensions) {
            if (ext.equals(getExtension(path))) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Gets extension from given path.
     *
     * @param path path to file
     * @return files extension
     */
    private String getExtension(Path path) {
        return path.toString().substring(path.toString().lastIndexOf('.') + 1);
    }
}