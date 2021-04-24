package be.kdg.se3.opdracht.application.persistence;

import be.kdg.se3.opdracht.application.domain.Order;

/**
 * Plug-in point for services that supply storage of {@link Order} objects
 */
public interface StorageService {
    /**
     * After this call, the order should be guaranteed to be saved in persistent storage
     */
    void saveOrder(Order order);

    /**
     * Saves the messages that could not be formatted in persistent storage
     */
    void saveNotFormattedMessages(String message);

}