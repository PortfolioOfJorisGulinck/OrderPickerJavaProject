package be.kdg.se3.opdracht.application.orderProcessing.optimization;

import be.kdg.se3.opdracht.application.domain.Item;
import be.kdg.se3.opdracht.application.domain.Order;

import be.kdg.se3.opdracht.application.orderProcessing.OutgoingQueue;


/**
 * Abstraction of the optimization algorithm for the {@link Order} and {@link Item} in the {@link OutgoingQueue}
 */
public interface Optimization<TYPE> {
    TYPE optimize(TYPE type);
}
