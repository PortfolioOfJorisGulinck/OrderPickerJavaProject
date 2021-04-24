package be.kdg.se3.opdracht.application.properties;

import be.kdg.se3.opdracht.application.generator.*;

/**
 * A generic properties class
 */
public class Properties {

    /**
     * Sets the parameters for making a connection with the RabbitMQ message broker
     */
    public static final String CONNECTIONSTRING = "127.0.0.1";
    public static final String USERNAME = "SE3";
    public static final String PASSWORD = "SE3";
    public static final String QUEUE_NAME = "ordersQueue";


    /**
     * Sets the interval for the ScheduledExecutorService in {@link MessageGenerator}
     */
    public static final int GENERATOR_INTERVAL = 1000;

    /**
     * Sets the interval between the creation of an annulation object
     * during the creation loops {@link Generator}
     */
    public static final int ANNULATION_COUNT_INTERVAL = 5;

    /**
     * Sets the delay when the annulation object is send after creation {@link Generator}
     */
    public static final int ANNULATION_SENDING_DELAY = 10;

    /**
     * Sets the two adjustable limits for the creation of the costumers id {@link CustomerCollection}
     */
    public static final int MIN_ID_COSTUMER = 1000000;
    public static final int MAX_ID_COSTUMER = 2500000;

    /**
     * Sets the two adjustable limits for the creation of the product id {@link ProductCollection}
     */
    public static final int MIN_ID_PRODUCT = 1000000;
    public static final int MAX_ID_PRODUCT = 3000000;

    /**
     * Sets the two adjustable limits for the creation of the order id {@link OrderGenerator}
     */
    public static final int MIN_ID_ORDER = 1000000;
    public static final int MAX_ID_ORDER = 9999999;

    /**
     * Sets the two adjustable limits for the creation of an random price in {@link PriceCollection}
     */
    public static final int ORDER_MAX_PRICE = 300;
    public static final int ORDER_MIN_PRICE = 30;


    /**
     * Sets the two adjustable limits for the creation of the number of items per
     * packaged product in {@link AmountCollection}
     */
    public static final int ITEMS_MAX_AMOUNT = 50;
    public static final int ITEMS_MIN_AMOUNT = 1;


    /**
     * Sets the two adjustable limits for the creation of the number of products per
     * order in {@link ItemCollection}
     */
    public static final int MIN_ITEMS = 1;
    public static final int MAX_ITEMS = 10;
}

