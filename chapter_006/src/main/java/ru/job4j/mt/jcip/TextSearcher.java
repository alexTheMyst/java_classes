package ru.job4j.mt.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Searches text in the file.
 *
 * @author alexey
 * @version 30.01.18
 */
@ThreadSafe
public class TextSearcher implements Runnable {
    /**
     * Path to file.
     */
    private final Path path;
    /**
     * Text to search.
     */
    private final String stringToSearch;
    /**
     * Results store.
     */
    @GuardedBy("itself")
    private final List<String> results;

    /**
     * Constructor.
     *
     * @param path           Path to file
     * @param stringToSearch Text to search
     */
    public TextSearcher(Path path, String stringToSearch, final List<String> results) {
        this.path = path;
        this.stringToSearch = stringToSearch;
        this.results = results;
    }

    /**
     * Reads file line by line and checks is each line contains the text.
     */
    @Override
    public void run() {
        try (Stream<String> lines = Files.lines(this.path)) {
            Object[] objects = lines.filter(s -> s.contains(this.stringToSearch)).toArray();
            if (objects.length > 0) {
                synchronized (this.results) {
                    this.results.add(this.path.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}