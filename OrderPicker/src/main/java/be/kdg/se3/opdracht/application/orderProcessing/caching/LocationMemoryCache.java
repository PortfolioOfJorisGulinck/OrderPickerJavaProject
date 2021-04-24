package be.kdg.se3.opdracht.application.orderProcessing.caching;

import be.kdg.se3.opdracht.adapters.LocationServiceAPI;
import be.kdg.se3.opdracht.application.domain.Location;
import be.kdg.se3.opdracht.application.domain.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Caching of the {@link Location} of the processed {@link Item} by the {@link LocationServiceAPI}
 */
public class LocationMemoryCache implements InMemoryCache<Location>{

    private static Map<Integer, Location> items = new HashMap<>();

    public LocationMemoryCache() {
    }

    @Override
    public void clearCache() {
        items.clear();
    }

    @Override
    public Location getValue(int productId) {
        return items.get(productId);
    }

    @Override
    public void addValue(int productId, Location location) {
        items.put(productId, location);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void removeValue(int key) {
        items.remove(key);
    }

    @Override
    public Collection<Location> getValues() {
        return items.values();
    }
}
