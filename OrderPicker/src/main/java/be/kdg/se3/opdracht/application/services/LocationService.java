package be.kdg.se3.opdracht.application.services;

import be.kdg.se3.opdracht.application.domain.Location;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;

public interface LocationService {
    /**
     * Synchroneous call that returns a {@link Location} of the supplied itemId
     */
    Location analyse(String itemId) throws AdapterException;
}
