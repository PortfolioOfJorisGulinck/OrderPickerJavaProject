package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.properties.Properties.MAX_ITEMS;
import static be.kdg.se3.opdracht.application.properties.Properties.MIN_ITEMS;

/**
 * Sets the two adjustable limits for the creation of the number of products per order
 */
public class ItemCollection extends AdjustableCollection {

    @Override
    protected int getLowerLimit() {
        return MIN_ITEMS;
    }

    @Override
    protected int getUpperLimit() {
        return MAX_ITEMS;
    }
}
