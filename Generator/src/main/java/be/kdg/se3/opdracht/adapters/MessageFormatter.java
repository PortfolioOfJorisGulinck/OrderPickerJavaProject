package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.dto.OrderDTO;

/**
 * Abstraction for the conversion of outgoing {@link OrderDTO} and {@link AnnulationDTO} to XML strings
 */
public interface MessageFormatter {

    String formatOrder(OrderDTO order)throws AdapterException;

    String formatAnnulation(AnnulationDTO annulate)throws AdapterException;
}
