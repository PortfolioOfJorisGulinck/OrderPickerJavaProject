package be.kdg.se3.opdracht;

import be.kdg.se3.opdracht.adapters.JAXBMessageFormatter;
import be.kdg.se3.opdracht.adapters.LocationServiceAPI;
import be.kdg.se3.opdracht.adapters.RabbitMQ;
import be.kdg.se3.opdracht.application.orderProcessing.caching.InMemoryCache;
import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.application.domain.Location;
import be.kdg.se3.opdracht.application.domain.Order;
import be.kdg.se3.opdracht.application.orderProcessing.caching.FailedOrdersMemoryCache;
import be.kdg.se3.opdracht.application.orderProcessing.caching.LocationMemoryCache;
import be.kdg.se3.opdracht.application.orderProcessing.OrderHandler;
import be.kdg.se3.opdracht.application.orderProcessing.OutgoingQueue;
import be.kdg.se3.opdracht.application.orderProcessing.optimization.GroupOrdersOptimization;
import be.kdg.se3.opdracht.application.orderProcessing.optimization.Optimization;
import be.kdg.se3.opdracht.application.orderProcessing.optimization.SingleOrderOptimization;
import be.kdg.se3.opdracht.application.properties.Properties;
import be.kdg.se3.opdracht.application.services.MessageInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static be.kdg.se3.opdracht.application.properties.Properties.FLUSH_CYCLE_ITEM_MEMORY_CACHE;
import static be.kdg.se3.opdracht.application.properties.Properties.REPROCESS_CYCLE_FAILED_ORDERS;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        MessageInputService inputService = new RabbitMQ(Properties.CONNECTIONSTRING, Properties.QUEUE_NAME, Properties.USERNAME,
                Properties.PASSWORD, new JAXBMessageFormatter());

        Optimization<Order> singleOptimization = new SingleOrderOptimization();
        Optimization<List<Order>> groupOptimization = new GroupOrdersOptimization();
        OutgoingQueue outgoingQueue = new OutgoingQueue(singleOptimization, groupOptimization);

        InMemoryCache<OrderDTO> failedOrders = new FailedOrdersMemoryCache();
        InMemoryCache<Location> locationInMemoryCache = new LocationMemoryCache();

        OrderHandler orderHandler = new OrderHandler(inputService, outgoingQueue, failedOrders, locationInMemoryCache);
        orderHandler.setLocationService(new LocationServiceAPI());
        orderHandler.start();

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            LOGGER.info("Clearing in memory cache, cache consisted of {} locations", locationInMemoryCache.getSize());
            locationInMemoryCache.clearCache();
        }, 0, FLUSH_CYCLE_ITEM_MEMORY_CACHE, TimeUnit.SECONDS);

        final ScheduledExecutorService schedulerFailedOrders = Executors.newScheduledThreadPool(1);
        schedulerFailedOrders.scheduleAtFixedRate(() -> {
            LOGGER.info("Re-processing failed orders, failed order cache consisted of {} orders", failedOrders.getSize());
            orderHandler.processAllFailedOrders();
        }, 0, REPROCESS_CYCLE_FAILED_ORDERS, TimeUnit.SECONDS);
    }
}

