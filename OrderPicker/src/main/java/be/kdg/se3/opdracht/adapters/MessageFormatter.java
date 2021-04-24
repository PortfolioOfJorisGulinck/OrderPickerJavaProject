package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.dto.OrderDTO;

/**
 * Abstraction for the conversion of incoming XML strings to OrderDTO's and annulationDTO's
 */
public interface MessageFormatter {

    OrderDTO formatOrder(String messageString) throws AdapterException;

    AnnulationDTO formatAnnulation(String messageString) throws AdapterException;
}
