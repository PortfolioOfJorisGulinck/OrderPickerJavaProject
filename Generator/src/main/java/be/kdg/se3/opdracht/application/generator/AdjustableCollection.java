package be.kdg.se3.opdracht.application.generator;

import java.util.Random;

/**
 * Abstraction for the creation of collections bound by an upper and lower limit
 */
public abstract class AdjustableCollection {

    /**
     * Can be overriden to set a min limit
     */
    protected abstract int getLowerLimit();

    /**
     * Can be overriden to set a max limit
     */
    protected abstract int getUpperLimit();

    /**
     * Creates a random Integer set between an upper and lower limit
     */
    protected int getRandomId() {
        return new Random().nextInt(getUpperLimit() + 1) + getLowerLimit();
    }
}
