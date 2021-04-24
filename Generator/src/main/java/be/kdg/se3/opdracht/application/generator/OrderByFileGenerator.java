package be.kdg.se3.opdracht.application.generator;

import be.kdg.se3.opdracht.application.domain.Order;

import static be.kdg.se3.opdracht.application.properties.Properties.MAX_ID_ORDER;
import static be.kdg.se3.opdracht.application.properties.Properties.MIN_ID_ORDER;

/**
 * Expected expansion for the generation of test orders by file
 */
public class OrderByFileGenerator extends AdjustableCollection implements Generator<Order> {

    @Override
    public Order generate() {
        return null;
    }

    @Override
    protected int getLowerLimit() {
        return MIN_ID_ORDER;
    }

    @Override
    protected int getUpperLimit() {
        return MAX_ID_ORDER;
    }
}
