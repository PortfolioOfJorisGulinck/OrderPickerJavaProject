package be.kdg.se3.opdracht.application.services;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.listeners.AnnulationInputListener;
import be.kdg.se3.opdracht.application.listeners.OrderInputListener;

/**
 * A service that can be used to receive messages from a communication interface (e.g. message queue)
 */
public interface MessageInputService {

    /**
     * Setup communication with the service (new connection,...)
     * @param orderInputListener calkback interface for receiving ordermessages from the queue
     * @param annulationInputListener calkback interface for receiving annulationmessages from the queue
     */
    void initialize(OrderInputListener orderInputListener, AnnulationInputListener annulationInputListener) throws AdapterException;

    /**
     * End communication (release connection,...)
     */
    void shutdown() throws AdapterException;

}
