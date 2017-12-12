package ru.job4j.orders;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Represents group of orders.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class OrderGroup {

    /**
     * Price for group.
     */
    private BigDecimal groupPrice;

    /**
     * Store for orders.
     */
    private Map<Integer, Order> orders;

    /**
     * Sum ov volumes for orders in group.
     */
    private int totalVolume = 0;

    /**
     * Constructor.
     *
     * @param groupPrice group price
     * @param orders map for store
     */
    public OrderGroup(BigDecimal groupPrice, Map<Integer, Order>  orders) {
        this.groupPrice = groupPrice;
        this.orders = orders;
    }

    /**
     * Adds an order to group.
     *
     * @param order an order
     */
    void addOrder(Order order) {
        this.orders.put(order.getId(), order);
        this.totalVolume += order.getVolume();
    }

    /**
     * Deletes given order.
     *
     * @param order an order
     */
    void deleteOrder(Order order) {
        deleteOrder(order.getId());
    }

    /**
     * Deletes the order for given order id.
     *
     * @param orderId order id
     */
    void deleteOrder(int orderId) {
        if (this.orders.get(orderId) != null) {
            this.totalVolume -= this.orders.get(orderId).getVolume();
            this.orders.remove(orderId);
        }
    }

    /**
     * Getter for orders.
     *
     * @return orders map
     */
    public Map<Integer, Order> getOrders() {
        return orders;
    }

    /**
     * Group price getter.
     *
     * @return group price
     */
    public BigDecimal getGroupPrice() {
        return groupPrice;
    }

    /**
     * Total volume getter.
     *
     * @return total volume
     */
    public int getTotalVolume() {
        return totalVolume;
    }

    /**
     * Creates string representation.
     *
     * @return string
     */
    @Override
    public String toString() {
        return this.totalVolume
                + "@"
                + this.groupPrice;
    }
}