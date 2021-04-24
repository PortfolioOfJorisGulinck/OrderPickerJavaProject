package be.kdg.se3.opdracht.application.listeners;

import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;

public interface OrderInputListener {
    /**
     * Called when a new message is availabe
     * @param orderDTO the message converted from the wire format to a DTO
     */
    void onReceive(OrderDTO orderDTO);

    /**
     * Called when the incoming message could not be converted to a DTO
     */
    void onError(AdapterException e, String message);
}
