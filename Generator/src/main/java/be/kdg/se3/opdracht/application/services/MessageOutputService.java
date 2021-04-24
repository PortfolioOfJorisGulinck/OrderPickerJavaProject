package be.kdg.se3.opdracht.application.services;

import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;

/**
 * A service that can be used to send messages to a communication interface (e.g. message queue)
 */
public interface MessageOutputService {
    /**
     * Setup communication with the service (new connection,...)
     */
    void initialize() throws AdapterException;

    /**
     * End communication (release connection,...)
     */
    void shutdown() throws AdapterException;


    void sendOrder(OrderDTO msg) throws AdapterException;


    void sendAnnulation(AnnulationDTO msg) throws AdapterException;

}
