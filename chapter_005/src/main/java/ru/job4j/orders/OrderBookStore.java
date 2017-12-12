package ru.job4j.orders;

import java.util.HashMap;
import java.util.Map;

/**
 * Order books storage.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class OrderBookStore {

    /**
     * Store.
     */
    private Map<String, OrderBook> store = new HashMap<>();

    /**
     * Adds order to the book store.
     *
     * @param order an order
     */
    public void addOrderInBookStore(Order order) {
        if (isOrderNotDefault(order)) {
            String bookName = order.getBookName();
            creteOrderBook(bookName);
            store.get(bookName).addOrder(order);
        }
    }

    /**
     * Deletes an order from the book store.
     *
     * @param order an order
     */
    public void deleteOrderFromBookStore(Order order) {
        if (isOrderNotDefault(order)) {
            String bookName = order.getBookName();
            store.get(bookName).deleteOrder(order);
        }
    }

    /**
     * Checks is given order not default.
     *
     * @param order an order
     * @return true if given order has any book name except default or else otherwise
     */
    private boolean isOrderNotDefault(Order order) {
        return order.getBookName() != "void";
    }

    /**
     * Checks that order book exists, if not creates a new one.
     *
     * @param bookName order book name
     */
    private void creteOrderBook(String bookName) {
        if (!store.containsKey(bookName)) {
            OrderBook orderBook = new OrderBook(bookName);
            store.put(bookName, orderBook);
        }
    }

    /**
     * Overridden toString implementation.
     *
     * @return store string representation
     */
    @Override
    public String toString() {
        return "OrderBookStore{"
                + "store="
                + store
                + '}';
    }

    /**
     * Prints contents os the current book store.
     */
    public void printAllBooks() {
        for (Map.Entry<String, OrderBook> book : store.entrySet()) {
            book.getValue().print();
            System.out.println("");
        }
    }

    /**
     * Fills odrer from string.
     *
     * @param line line to parse
     * @param order order to fill
     */
    public void fillAddOrderFromString(String line, Order order) {
        String operationName = getAddOrderParamValue(line, "operation=");
        order.setBuyOperation(operationName.equalsIgnoreCase("buy"));
        String priceStr = getAddOrderParamValue(line, "price=");
        order.setPrice(priceStr.trim());
        String volumeStr = getAddOrderParamValue(line, "volume=");
        order.setVolume(Integer.parseInt(volumeStr.trim()));
    }

    /**
     * Creates an order from string.
     *
     * @param line some string
     * @return created order
     */
    public Order createOrderFromString(String line) {
        Order order;
        String bookName = getAddOrderParamValue(line, "book=");
        int id = Integer.parseInt(getAddOrderParamValue(line, "orderId="));
        order = new Order(bookName, id);
        return order;
    }

    /**
     * Gets a parameter from the string.
     *
     * @param orderString string representing the order
     * @param paramName param name to find
     * @return paremeter value as a string
     */
    private String getAddOrderParamValue(String orderString, String paramName) {
        int startIndex = orderString.indexOf(paramName) + paramName.length() + 1;
        int endIndex = orderString.indexOf("\"", startIndex);
        return orderString.substring(startIndex, endIndex);
    }
}