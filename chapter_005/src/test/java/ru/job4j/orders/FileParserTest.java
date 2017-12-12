package ru.job4j.orders;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for parsers.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class FileParserTest {
    /**
     * Path to file.
     */
    private String filePath = "/Users/alexey/Documents/JavaClasses/orders.xml";

    /**
     * Measures time to full file processing using BufferedReader.
     *
     * @throws IOException
     */
    @Test
    public void testBuffReader() throws IOException {
        OrderBookStore orderBookStore = new OrderBookStore();
        FileParser parser = new FileParserBufferedReaderImpl(this.filePath, orderBookStore);
        long startTime = System.currentTimeMillis();
        parser.parseFile();
        orderBookStore.printAllBooks();
        System.out.println(String.format("Buffered reader %d ms", System.currentTimeMillis() - startTime));
    }

    /**
     * Measures time to full file processing using Stax.
     */
    @Test
    public void testStax() {
        OrderBookStore orderBookStore = new OrderBookStore();
        FileParser parser = new FileParserStaxImpl(this.filePath, orderBookStore);
        long startTime = System.currentTimeMillis();
        parser.parseFile();
        orderBookStore.printAllBooks();
        System.out.println(String.format("Stax reader %d ms", System.currentTimeMillis() - startTime));

    }

    /**
     * Adds one order and checks that order exists in ladder.
     */
    @Test
    public void whenAddOneOrderThenBidHasOneOrder() {
        Order orderOne = new Order("b1", 1, true, 1, BigDecimal.valueOf(10.0d));
        OrderBook orderBook = new OrderBook("b1");

        orderBook.addOrder(orderOne);
        orderBook.print();

        assertThat(orderBook.getBid().get(BigDecimal.valueOf(10.0d)).getTotalVolume(), is(1));
    }

    /**
     * Adds and deletes the order and checks that ladder is empty.
     */
    @Test
    public void whenAddOneOrderAndDeleteThenNoOrderInLadder() {
        Order orderOne = new Order("b1", 1, false, 10, BigDecimal.valueOf(10.0d));
        OrderBook orderBook = new OrderBook("b1");

        orderBook.addOrder(orderOne);
        orderBook.deleteOrder(orderOne);
        orderBook.print();

        assertThat(orderBook.getAsk().get(BigDecimal.valueOf(10.0d)).getTotalVolume(), is(0));
    }

    /**
     * Adds one sell and one buy order with the same amount and checks that no orders in ladder.
     */
    @Test
    public void whenBuyOrderExactMatchedThenGetEmptyLadder() {
        Order orderOne = new Order("b1", 1, false, 10, BigDecimal.valueOf(10.0d));
        Order orderTwo = new Order("b1", 2, true, 10, BigDecimal.valueOf(10.0d));
        OrderBook orderBook = new OrderBook("b1");

        orderBook.addOrder(orderOne);
        orderBook.addOrder(orderTwo);
        orderBook.print();

        assertThat(orderBook.getBid().isEmpty(), is(true));
    }

    /**
     * Adds one sell and one buy order with greater amount then checks that bid has decreased amount.
     */
    @Test
    public void whenBuyOrderGreaterThanSellThenGetBidDecreased() {
        Order orderOne = new Order("b1", 1, false, 10, BigDecimal.valueOf(10.0d));
        Order orderTwo = new Order("b1", 2, true, 15, BigDecimal.valueOf(10.0d));
        OrderBook orderBook = new OrderBook("b1");

        orderBook.addOrder(orderOne);
        orderBook.addOrder(orderTwo);
        orderBook.print();

        assertThat(orderBook.getBid().isEmpty(), is(false));
    }

    /**
     * Adds one buy and one sell order with greater amount then checks that ask has order with initial amount.
     */
    @Test
    public void whenBuyOrderGreaterMatchedThenGetOneBid() {
        Order orderOne = new Order("b1", 1, true, 10, BigDecimal.valueOf(10.0d));
        Order orderTwo = new Order("b1", 2, false, 15, BigDecimal.valueOf(10.0d));
        OrderBook orderBook = new OrderBook("b1");

        orderBook.addOrder(orderOne);
        orderBook.addOrder(orderTwo);
        orderBook.print();

        assertThat(orderBook.getAsk().get(BigDecimal.valueOf(10.0d)).getTotalVolume(), is(15));
    }
}