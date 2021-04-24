package be.kdg.se3.opdracht.application.orderProcessing;

import be.kdg.se3.opdracht.application.orderProcessing.optimization.Optimization;
import be.kdg.se3.opdracht.application.domain.Annulation;
import be.kdg.se3.opdracht.application.domain.Order;
import be.kdg.se3.opdracht.application.properties.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * WaitingQueue that collects the {@link Order} before sending to the mobile app
 */
public class OutgoingQueue {

    private static List<Order> outgoingQueue = new ArrayList<>();

    private Optimization<Order> singleOrderOptimization;
    private Optimization<List<Order>> groupOrdersOptimization;

    private Logger logger = LoggerFactory.getLogger(OutgoingQueue.class);

    public OutgoingQueue(Optimization<Order> singleOrderOptimization, Optimization<List<Order>> groupOrdersOptimization) {
        this.singleOrderOptimization = singleOrderOptimization;
        this.groupOrdersOptimization = groupOrdersOptimization;
    }

    public void addOrder(Order order) {
        outgoingQueue.add(order);
        if (outgoingQueue.size() == Properties.BUFFER_SIZE) {
            sendQueue();
        }
    }

    /**
     * Deletes the order from the queue when an annulation message is received
     */
    public void deleteOrder(Annulation annulation) {
        boolean found = false;
        for (Order order : outgoingQueue) {
            if (order.getOrderId() == annulation.getOrderId()) {
                found = true;
                outgoingQueue.remove(order);
            }
        }
        if (!found){
            logger.info("Annulation failed. Could not delete order");
        }
    }

    /**
     * Sends the orders from the queue to the mobile app
     */
    public void sendQueue() {
        for (Order order : outgoingQueue) {
            singleOrderOptimization.optimize(order);
        }
        groupOrdersOptimization.optimize(outgoingQueue);
        for (Order order : outgoingQueue) {
            logger.info("Sending order -> {} to mobile app", order);
        }
        outgoingQueue.clear();
    }
}

