package ru.job4j.orders;

import java.math.BigDecimal;

/**
 * Represents an order.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class Order {
    /**
     * Default book name.
     */
    final static String DEFAULT_BOOK_NAME = "void";

    /**
     * Default id.
     */
    final static int DEFAULT_ID = 0;

    /**
     * Book name.
     */
    private String bookName;

    /**
     * Order id.
     */
    private int id;

    /**
     * Represents order type, true = buy order, false = sell order.
     */
    private boolean isBuyOperation = true;

    /**
     * Order price.
     */
    private BigDecimal price;

    /**
     * Order volume.
     */
    private int volume;

    /**
     * Default constructor.
     */
    public Order() {
        this(DEFAULT_BOOK_NAME, DEFAULT_ID);
    }

    /**
     * Creates an order with given bookName and id.
     *
     * @param bookName book name
     * @param id       id
     */
    public Order(String bookName, int id) {
        this(bookName, id, true, 0, BigDecimal.ZERO);
    }

    /**
     * Creates an order.
     *
     * @param bookName       book name
     * @param id             id
     * @param isBuyOperation is current order a buy operation
     * @param value          order value
     * @param price          order price
     */
    public Order(String bookName, int id, boolean isBuyOperation, int value, BigDecimal price) {
        this.bookName = bookName;
        this.id = id;
        this.isBuyOperation = isBuyOperation;
        this.volume = value;
        this.price = price;
    }

    /**
     * Id getter.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for bookName.
     *
     * @return bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Getter for isBuyOperation.
     *
     * @return isBuyOperation
     */
    public boolean isBuyOperation() {
        return isBuyOperation;
    }

    /**
     * Getter for price.
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter for volume.
     *
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Setter for isBuyOperation
     *
     * @param buyOperation boolean
     */
    public void setBuyOperation(boolean buyOperation) {
        isBuyOperation = buyOperation;
    }

    /**
     * Setter for price.
     *
     * @param price order price
     */
    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    /**
     * Setter for price.
     *
     * @param price order price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    /**
     * Setter for volume.
     *
     * @param volume order volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Checks that order has default id and bookName.
     *
     * @return true if order has default id and bookName and false otherwise
     */
    public boolean isDefault() {
        return this.id == Order.DEFAULT_ID && this.bookName.toLowerCase().equals(Order.DEFAULT_BOOK_NAME.toLowerCase());
    }

    /**
     * Creates order string representation.
     *
     * @return order string representation
     */
    @Override
    public String toString() {
        return "Order{"
                + "bookName='"
                + bookName
                + '\''
                + ", id="
                + id
                + ", isBuyOperation="
                + isBuyOperation
                + ", price="
                + price
                + ", volume="
                + volume
                + '}';
    }
}