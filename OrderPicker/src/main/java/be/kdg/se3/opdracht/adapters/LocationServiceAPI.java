package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.domain.Location;
import be.kdg.se3.opdracht.application.services.LocationService;
import be.kdg.se3.services.orderpicking.LocationServiceProxy;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;

/**
 * Adapts the LocationServiceProxy to the domains LocationService
 */
public class LocationServiceAPI implements LocationService {

    private Logger logger = LoggerFactory.getLogger(LocationServiceAPI.class);
    private LocationServiceProxy proxy = new LocationServiceProxy();

    public LocationServiceAPI() {
    }

    @Override
    public Location analyse(String itemId) throws AdapterException {
        Location location;
        try {
            logger.debug("Converting JSON respons to Location");
            String url = "www.services4se3.com/locationservice/" + itemId;

            JSONObject result = new JSONObject(proxy.get(url));
            String storageRoom = result.getString("storageRoom");
            int hallway = result.getInt("hallway");
            int rack = result.getInt("rack");
            location = new Location(storageRoom, hallway, rack);
            logger.info("Received respons: " + location.toString());
        } catch (Exception e) {
            if (e instanceof JSONException) {
                return null;
            }
            throw new AdapterException("Unable to call LocationServiceProxy", e);
        }
        return location;
    }
}


