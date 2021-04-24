package be.kdg.se3.opdracht.application.listeners;

import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;

public interface AnnulationInputListener {
    /**
     * Called when a new message is availabe
     * @param annulationDTO the message converted from the wire format to a DTO
     */
    void onReceive(AnnulationDTO annulationDTO);

    /**
     * Called when the incoming message could not be converted to a DTO
     */
    void onError(AdapterException e, String message);
}
