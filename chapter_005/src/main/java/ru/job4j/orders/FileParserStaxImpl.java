package ru.job4j.orders;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Parse XML using Stax.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public class FileParserStaxImpl implements FileParser {

    /**
     * Path to file.
     */
    private String filePath;

    /**
     * Store for orders.
     */
    private OrderBookStore orderBookStore;

    /**
     * XML input factory.
     */
    private XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();

    /**
     * XML stream reader.
     */
    private XMLStreamReader streamReader;

    /**
     * Constructor.
     *
     * @param filePath path to file
     * @param orderBookStore book store
     */
    public FileParserStaxImpl(String filePath, OrderBookStore orderBookStore) {
        this.filePath = filePath;
        this.orderBookStore = orderBookStore;
    }

    /**
     * Parses given file.
     */
    @Override
    public void parseFile() {
        initEventReader();
        try {
            while (this.streamReader.hasNext()) {
                getNextElement();
                if (this.streamReader.hasName()) {
                    Order order = getOrderFromXmlElement();
                    if (order.getVolume() > 0) {
                        this.orderBookStore.addOrderInBookStore(order);
                    } else {
                        this.orderBookStore.deleteOrderFromBookStore(order);
                    }
                }
            }
        } catch (XMLStreamException xse) {
            xse.printStackTrace();
        }

    }

    /**
     * Creates an order from XML element.
     *
     * @return an order
     */
    private Order getOrderFromXmlElement() {
        Order order;
        if (this.streamReader.getLocalName().equals("AddOrder")) {
            order = composeOrder();
        } else {
            order = new Order(this.streamReader.getAttributeValue(0),
                    Integer.parseInt(this.streamReader.getAttributeValue(1)));
        }
        return order;
    }

    /**
     * Composes an order.
     *
     * @return an order
     */
    private Order composeOrder() {
        Order order = null;
        if (!this.streamReader.isEndElement()) {
            order = new Order(this.streamReader.getAttributeValue(0),
                    Integer.parseInt(this.streamReader.getAttributeValue(4)));
            if (this.streamReader.getAttributeValue(1).equals("SELL")) {
                order.setBuyOperation(false);
            }
            order.setPrice(this.streamReader.getAttributeValue(2).trim());
            order.setVolume(Integer.parseInt(this.streamReader.getAttributeValue(3)));
        }
        return order;
    }

    /**
     * Gets the next element from steam.
     *
     * @throws XMLStreamException
     */
    private void getNextElement() throws XMLStreamException {
        int event = this.streamReader.next();
        while (event != XMLEvent.END_DOCUMENT && (event != XMLEvent.START_ELEMENT || this.streamReader.getLocalName().equals("Orders"))) {
            event = this.streamReader.next();
        }
    }

    /**
     * Initiates event reader.
     */
    private void initEventReader() {
        if (streamReader == null) {
            try {
                this.streamReader = xmlInputFactory.createXMLStreamReader(new BufferedInputStream(new FileInputStream(this.filePath)));
            } catch (XMLStreamException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}