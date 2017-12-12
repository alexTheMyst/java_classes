package ru.job4j.orders;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Book of orders has two maps to store orders (buy and sell) and
 * two ladders (bid, ask).
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class OrderBook {

    /**
     * Book name.
     */
    private String name;

    /**
     * Orders store.
     */
    private Map<Integer, OrderGroup> orders = new HashMap<>(20_000);

    /**
     * Bid ladder.
     */
    private Map<BigDecimal, OrderGroup> bid = new TreeMap<>(Comparator.naturalOrder());

    /**
     * Ask ladder.
     */
    private Map<BigDecimal, OrderGroup> ask = new TreeMap<>(Comparator.reverseOrder());

    /**
     * Constructor.
     *
     * @param name book name
     */
    public OrderBook(String name) {
        this.name = name;
    }

    /**
     * Add orders to book.
     *
     * @param order an order
     */
    public void addOrder(Order order) {
        if (order.isBuyOperation()) {
            processOrder(order, this.bid, this.ask, this.orders);
        } else {
            processOrder(order, this.ask, this.bid, this.orders);
        }
    }

    /**
     * Bid getter.
     *
     * @return bid ladder.
     */
    public Map<BigDecimal, OrderGroup> getBid() {
        return bid;
    }

    /**
     * Ask getter.
     *
     * @return ask ladder.
     */
    public Map<BigDecimal, OrderGroup> getAsk() {
        return ask;
    }

    /**
     * Processes an order.
     *
     * @param order          an order
     * @param ladder         ladder for new order
     * @param oppositeLadder opposite ladder
     * @param orders         orders store
     */
    private void processOrder(Order order,
                              Map<BigDecimal, OrderGroup> ladder,
                              Map<BigDecimal, OrderGroup> oppositeLadder,
                              Map<Integer, OrderGroup> orders
    ) {
        Order matchingOrder = (order.isBuyOperation())
                ? findMatchingSellOrder(order, oppositeLadder)
                : findMatchingBuyOrder(order, oppositeLadder);

        if (matchingOrder.isDefault()) {
            addOrderToGroup(orders, ladder, order);
        } else if (order.getVolume() == matchingOrder.getVolume()) {
            this.deleteOrder(matchingOrder);
        } else {
            Order buyOrder = (order.isBuyOperation()) ? order : matchingOrder;
            Order sellOrder = (!order.isBuyOperation()) ? order : matchingOrder;
            executeOrder(buyOrder, sellOrder);
            addOrderToGroup(orders, ladder, order);
        }
    }

    /**
     * Executes order.
     *
     * @param buyOrder  buy order
     * @param sellOrder sell order
     */
    private void executeOrder(Order buyOrder, Order sellOrder) {
        int newVolume = buyOrder.getVolume() - sellOrder.getVolume();
        buyOrder.setVolume(newVolume);
        this.deleteOrder(sellOrder);
    }

    /**
     * Finds matching buy order.
     *
     * @param order  an order
     * @param ladder buy ladder
     * @return order
     */
    private Order findMatchingBuyOrder(Order order, Map<BigDecimal, OrderGroup> ladder) {
        Order resultOrder = new Order();
        if (ladder.containsKey(order.getPrice())) {
            OrderGroup orderGroup = ladder.get(order.getPrice());
            TreeMap<Integer, Order> orders = (TreeMap<Integer, Order>) orderGroup.getOrders();
            for (Map.Entry<Integer, Order> orderEntry : orders.entrySet()) {
                int volume = order.getVolume();
                int entryVolume = orderEntry.getValue().getVolume();
                if (entryVolume >= volume) {
                    resultOrder = orderEntry.getValue();
                    break;
                } else {
                    break;
                }
            }
        }
        return resultOrder;
    }

    /**
     * Finds matching sell order.
     *
     * @param order  an order
     * @param ladder buy ladder
     * @return order
     */
    private Order findMatchingSellOrder(Order order, Map<BigDecimal, OrderGroup> ladder) {
        Order resultOrder = new Order();
        if (ladder.containsKey(order.getPrice())) {
            OrderGroup orderGroup = ladder.get(order.getPrice());
            TreeMap<Integer, Order> orders = (TreeMap<Integer, Order>) orderGroup.getOrders();
            for (Map.Entry<Integer, Order> orderEntry : orders.entrySet()) {
                if (orderEntry.getValue().getVolume() <= order.getVolume()) {
                    resultOrder = orderEntry.getValue();
                    break;
                } else {
                    break;
                }
            }
        }
        return resultOrder;
    }

    /**
     * Adds an order to group.
     *
     * @param orders orders store
     * @param ladder ladder
     * @param order  order
     */
    private void addOrderToGroup(Map<Integer, OrderGroup> orders,
                                 Map<BigDecimal, OrderGroup> ladder,
                                 Order order) {
        if (ladder.containsKey(order.getPrice())) {
            ladder.get(order.getPrice()).addOrder(order);
            orders.put(order.getId(), ladder.get(order.getPrice()));
        } else {
            OrderGroup newGroup = createNewOrderGroup(order);
            newGroup.addOrder(order);
            ladder.put(order.getPrice(), newGroup);
            orders.put(order.getId(), newGroup);
        }
    }

    /**
     * Creates new order group.
     *
     * @param order an order
     * @return order group
     */
    private OrderGroup createNewOrderGroup(Order order) {
        Map<Integer, Order> orders = new TreeMap<>();
        return new OrderGroup(order.getPrice(), orders);
    }

    /**
     * Deletes given order.
     *
     * @param order an order
     */
    public void deleteOrder(Order order) {
        int id = order.getId();
        if (this.orders.containsKey(id)) {
            this.orders.get(id).deleteOrder(id);
            this.orders.remove(id);
        }
    }

    /**
     * Prints this order book contents.
     */
    public void print() {
        System.out.println(this.name);
        List<Map.Entry<BigDecimal, OrderGroup>> bid = this.bid.entrySet().stream().filter(e -> e.getValue().getTotalVolume() > 0).collect(Collectors.toList());
        List<Map.Entry<BigDecimal, OrderGroup>> ask = this.ask.entrySet().stream().filter(e -> e.getValue().getTotalVolume() > 0).collect(Collectors.toList());
        Iterator<Map.Entry<BigDecimal, OrderGroup>> bidIter = bid.iterator();
        Iterator<Map.Entry<BigDecimal, OrderGroup>> askIter = ask.iterator();
        System.out.printf("%15s\t%15s%n", "BID", "ASK");
        while (bidIter.hasNext() || askIter.hasNext()) {
            String bidString = "------";
            String askString = "------";
            if (bidIter.hasNext()) {
                Map.Entry<BigDecimal, OrderGroup> me = bidIter.next();
                bidString = String.format("%d@%.2f", me.getValue().getTotalVolume(), me.getKey());
            }
            if (askIter.hasNext()) {
                Map.Entry<BigDecimal, OrderGroup> me = askIter.next();
                askString = String.format("%d@%.2f", me.getValue().getTotalVolume(), me.getKey());
            }
            System.out.printf("%15s\t%15s%n", bidString, askString);
        }
    }
}