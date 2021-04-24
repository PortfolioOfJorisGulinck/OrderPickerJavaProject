package be.kdg.se3.opdracht.application.persistence;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.domain.Annulation;
import be.kdg.se3.opdracht.application.domain.Order;

/**
 * Plug-in point for services that supply storage of {@link Order} and {@link Annulation} objects
 */
public interface StorageService {

    void saveOrder(Order order) throws AdapterException;

    void saveAnnulation(Annulation annulation) throws AdapterException;
}