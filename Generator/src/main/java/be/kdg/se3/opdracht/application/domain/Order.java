package be.kdg.se3.opdracht.application.domain;

import java.util.*;

/**
 * Business entity representing the order of a client
 */
public class Order {
    private int orderId;
    private int costumerId;
    private int price;
    private List<Item> items = new ArrayList<>();

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public int getPrice() {
        return price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.orderId + ", costumer=" + this.costumerId + ", price=" + this.price +
                ", Items=" + this.items +'}';
    }
}
