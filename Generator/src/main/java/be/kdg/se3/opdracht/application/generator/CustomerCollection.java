package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.properties.Properties.*;

/**
 * Sets the two adjustable limits for the creation of the costumers id
 */
public class CustomerCollection extends AdjustableCollection {

    @Override
    protected int getLowerLimit() {
        return MIN_ID_COSTUMER;
    }

    @Override
    protected int getUpperLimit() {
        return MAX_ID_COSTUMER;
    }
}

