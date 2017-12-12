package ru.job4j.orders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Parse XML using BufferedReader.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class FileParserBufferedReaderImpl implements FileParser {

    /**
     * Buffered reader.
     */
    private BufferedReader reader;

    /**
     * Stor for orders.
     */
    private OrderBookStore orderBookStore;

    /**
     * Constructor.
     *
     * @param filePath path to file
     * @param orderBookStore book store
     * @throws IOException
     */
    public FileParserBufferedReaderImpl(String filePath, OrderBookStore orderBookStore) throws IOException {
        this.reader = new BufferedReader(Files.newBufferedReader(Paths.get(filePath), Charset.defaultCharset()));
        this.orderBookStore = orderBookStore;
    }

    /**
     * Parses the file.
     */
    @Override
    public void parseFile() {
        String line;
        try {
            while ((line = this.reader.readLine()) != null) {
                if (line.startsWith("<Add")) {
                    Order order = this.orderBookStore.createOrderFromString(line);
                    this.orderBookStore.fillAddOrderFromString(line, order);
                    orderBookStore.addOrderInBookStore(order);
                } else if (line.startsWith("<Del")) {
                    Order order = this.orderBookStore.createOrderFromString(line);
                    orderBookStore.deleteOrderFromBookStore(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
