package be.kdg.se3.opdracht.application.orderProcessing.optimization;

import be.kdg.se3.opdracht.application.domain.Order;
import be.kdg.se3.opdracht.application.orderProcessing.OutgoingQueue;

import java.util.Collections;
import java.util.List;

/**
 * optimization algorithm for the List of {@link Order} in the {@link OutgoingQueue}
 */
public class GroupOrdersOptimization implements Optimization<List<Order>> {

    @Override
    public List<Order> optimize(List<Order> orders) {
        Collections.shuffle(orders);
        return orders;
    }
}
