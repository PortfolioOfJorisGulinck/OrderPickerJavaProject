package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.properties.Properties.ITEMS_MAX_AMOUNT;
import static be.kdg.se3.opdracht.application.properties.Properties.ITEMS_MIN_AMOUNT;

/**
 * Sets the two adjustable limits for the creation of the number of items per
 * packaged product
 */
public class AmountCollection extends AdjustableCollection {

    @Override
    protected int getLowerLimit() {
        return ITEMS_MIN_AMOUNT;
    }

    @Override
    protected int getUpperLimit() {
        return ITEMS_MAX_AMOUNT;
    }
}
