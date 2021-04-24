package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.properties.Properties.*;

/**
 * Sets the two adjustable limits for the creation of the product id
 */
public class ProductCollection extends AdjustableCollection {
    @Override
    protected int getLowerLimit() {
        return MIN_ID_PRODUCT;
    }

    @Override
    protected int getUpperLimit() {
        return MAX_ID_PRODUCT;
    }
}
