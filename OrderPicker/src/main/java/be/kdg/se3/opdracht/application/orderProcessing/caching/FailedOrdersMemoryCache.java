package be.kdg.se3.opdracht.application.orderProcessing.caching;


import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.adapters.LocationServiceAPI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Caching of the {@link OrderDTO} that could not be processed by the {@link LocationServiceAPI}
 */
public class FailedOrdersMemoryCache implements InMemoryCache<OrderDTO> {

    private static Map<Integer, OrderDTO> failedOrders = new HashMap<>();

    public FailedOrdersMemoryCache() {
    }

    @Override
    public void clearCache() {
        failedOrders.clear();
    }

    @Override
    public OrderDTO getValue(int key) {
        return failedOrders.get(key);
    }

    @Override
    public void addValue(int id, OrderDTO order) {
        failedOrders.put(id, order);
    }

    @Override
    public int getSize() {
        return failedOrders.size();
    }

    @Override
    public void removeValue(int key) {
        failedOrders.remove(key);
    }

    @Override
    public Collection<OrderDTO> getValues() {
        return failedOrders.values();
    }
}
