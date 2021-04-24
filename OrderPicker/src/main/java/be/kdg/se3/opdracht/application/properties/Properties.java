package be.kdg.se3.opdracht.application.properties;

import be.kdg.se3.opdracht.application.orderProcessing.caching.LocationMemoryCache;
import be.kdg.se3.opdracht.application.orderProcessing.OutgoingQueue;
import be.kdg.se3.opdracht.application.orderProcessing.caching.FailedOrdersMemoryCache;

/**
 * A generic properties class
 */
public class Properties {
    /**
     * Sets the parameters for making a connection with the RabbitMQ message broker
     */
    public static final String CONNECTIONSTRING = "127.0.0.1";
    public static final String USERNAME = "SE3";
    public static final String PASSWORD = "SE3";
    public static final String QUEUE_NAME = "ordersQueue";

    /**
     * Sets the duration when the {@link LocationMemoryCache} is cleared
     */
    public static final int FLUSH_CYCLE_ITEM_MEMORY_CACHE = 120;

    /**
     * Sets the duration when the {@link FailedOrdersMemoryCache} is reprocessed
     */
    public static final int REPROCESS_CYCLE_FAILED_ORDERS = 25;

    /**
     * Sets the size of the {@link OutgoingQueue} before sending the orders to the mobile app
     */
    public static final int BUFFER_SIZE = 10;
}
