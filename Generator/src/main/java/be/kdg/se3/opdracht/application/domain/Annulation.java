package be.kdg.se3.opdracht.application.domain;

/**
 * The annulation object of a created order coupled with a sendingDelay
 */
public class Annulation {
    private int sendingDelay;
    private int orderId;

    public Annulation(int sendingDelay, int orderId) {
        this.sendingDelay = sendingDelay;
        this.orderId = orderId;
    }

    public int getSendingDelay() {
        return sendingDelay;
    }

    public int getOrderId() {
        return orderId;
    }

    /**
     * Represents the waiting cycle before sending the annulation message
     */
    public void setSendingDelay(int sendingDelay) {
        this.sendingDelay = sendingDelay;
    }
}
