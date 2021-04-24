package be.kdg.se3.opdracht.application.domain;

import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.application.dto.ItemDTO;

import java.util.*;

public class Order {
    private int orderId;
    private int costumerId;
    private int price;
    private List<Item> items = new ArrayList<>();

    public Order(OrderDTO orderDTO) {
        this.orderId = orderDTO.getOrderId();
        this.costumerId = orderDTO.getCostumerId();
        this.price = orderDTO.getPrice();

        for (ItemDTO itemDTO: orderDTO.getItems() ) {
            this.items.add(new Item(itemDTO.getProductId(),itemDTO.getItemAmount()));
        }
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

    @Override
    public String toString() {
        return "Order{" + "id=" + this.orderId + ", costumer=" + this.costumerId + ", price=" + this.price +
                ", Items=" + this.items +'}';
    }


}
