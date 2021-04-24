package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.properties.Properties.ORDER_MAX_PRICE;
import static be.kdg.se3.opdracht.application.properties.Properties.ORDER_MIN_PRICE;

/**
 * Sets the two adjustable limits for the creation of an random price
 */
public class PriceCollection extends AdjustableCollection {

    @Override
    protected int getLowerLimit() {
        return ORDER_MIN_PRICE;
    }

    @Override
    protected int getUpperLimit() {
        return ORDER_MAX_PRICE;
    }
}
