package be.kdg.se3.opdracht.application.orderProcessing.caching;

import java.util.Collection;

/**
 * Abstraction of the in-memory cache type classes
 */
public interface InMemoryCache<TYPE> {

    void clearCache();
    TYPE getValue(int key);
    void addValue(int key, TYPE type);
    int getSize();
    void removeValue(int key);
    Collection<TYPE> getValues();
}
