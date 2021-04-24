package be.kdg.se3.opdracht.application.orderProcessing;

import be.kdg.se3.opdracht.application.domain.Location;
import be.kdg.se3.opdracht.application.listeners.AnnulationInputListener;
import be.kdg.se3.opdracht.application.listeners.OrderInputListener;
import be.kdg.se3.opdracht.application.orderProcessing.caching.InMemoryCache;
import be.kdg.se3.opdracht.application.dto.*;
import be.kdg.se3.opdracht.application.domain.*;
import be.kdg.se3.opdracht.application.services.LocationService;
import be.kdg.se3.opdracht.application.persistence.StorageService;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.services.MessageInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.valueOf;

/**
 * Organiser of the location coupling process
 * If not {@link LocationService} is set, the message will not be analysed but will be stored
 * If not {@link StorageService} is set, the message will be analysed but not be stored
 */
public class OrderHandler implements OrderInputListener, AnnulationInputListener {

    private MessageInputService messageInputService;
    private OutgoingQueue outgoingQueue;
    private InMemoryCache<Location> locationMemoryCache;
    private InMemoryCache<OrderDTO> failedOrders;
    private StorageService storageService = null;
    private LocationService locationService = null;

    private Logger logger = LoggerFactory.getLogger(OrderHandler.class);

    public OrderHandler(MessageInputService messageInputService, OutgoingQueue outgoingQueue, InMemoryCache<OrderDTO> failedOrders, InMemoryCache<Location> locationMemoryCache) {
        this.messageInputService = messageInputService;
        this.outgoingQueue = outgoingQueue;
        this.failedOrders = failedOrders;
        this.locationMemoryCache = locationMemoryCache;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void start(){
        try {
            messageInputService.initialize( this, this);
        } catch (AdapterException e) {
            logger.error("Unable to initialize adapter", e);
        }
    }

    public void stop(){
        try {
            messageInputService.shutdown();
        } catch (AdapterException e) {
            logger.error("Unable to properly shut down adapter");
        }
    }

    @Override
    public void onReceive(OrderDTO orderDTO) {
        try {
            processOrder(orderDTO);
        } catch (Exception e1) {
            logger.error("Unexpected error during orderMessage handling", e1);
        }
    }

    @Override
    public void onReceive(AnnulationDTO annulationDTO) {
        try {
            Annulation annulation = new Annulation(annulationDTO);
            outgoingQueue.deleteOrder(annulation);
            logger.info("Annulation deleted from outgoingQueue");
        } catch (Exception e1) {
            logger.error("Unexpected error during annulationMessage handling", e1);
        }
    }

    private void processOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO);
        for (Item item : order.getItems()) {
            Location location = locationMemoryCache.getValue(item.getProductId());
            if (location == null) {
                location = getLocationFromProxy(item.getProductId());
                if (location == null) {
                    failedOrders.addValue(orderDTO.getOrderId(), orderDTO);
                    logger.info("Added order {} to failed order cache", order.getOrderId());
                    return;
                } else {
                    locationMemoryCache.addValue(item.getProductId(), location);
                }
            }
            item.setLocation(location);
        }
        if (storageService != null) {
            storageService.saveOrder(order);
        }
        logger.info("Added order to OutgoingQueue {}", order.getOrderId());
        outgoingQueue.addOrder(order);
    }

    private Location getLocationFromProxy(int productId) {
        try {
            return locationService.analyse(valueOf(productId));
        } catch (AdapterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onError(AdapterException e, String message) {
        storageService.saveNotFormattedMessages(message);
        logger.error("Error during formatting of message: " + message, e);
    }

    public void processAllFailedOrders() {
        for (OrderDTO failedOrder : failedOrders.getValues()) {
            processOrder(failedOrder);
            logger.info("Processed failed order - {}", failedOrder.getOrderId());
        }
        failedOrders.clearCache();
    }
}