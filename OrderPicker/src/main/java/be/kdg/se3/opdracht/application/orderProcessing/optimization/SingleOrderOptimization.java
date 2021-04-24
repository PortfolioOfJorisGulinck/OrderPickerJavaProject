package be.kdg.se3.opdracht.application.orderProcessing.optimization;

import be.kdg.se3.opdracht.application.domain.Item;
import be.kdg.se3.opdracht.application.domain.Order;
import be.kdg.se3.opdracht.application.orderProcessing.OutgoingQueue;

import java.util.Collections;

/**
 * Optimization algorithm for the list of {@link Item} in a {@link Order} in the {@link OutgoingQueue}
 */
public class SingleOrderOptimization implements Optimization<Order> {

    @Override
    public Order optimize(Order order) {
        Collections.shuffle(order.getItems());
        return order;
    }
}
