package be.kdg.se3.opdracht.application.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object used for conversion between stream formatOrder() and memory
 */
@XmlType(propOrder = {"orderId", "costumerId", "price", "items"})
@XmlRootElement
public class OrderDTO {
    private int orderId;
    private int costumerId;
    private int price;
    private List<ItemDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(int orderId, int costumerId, int price, List<ItemDTO> items) {
        this.orderId = orderId;
        this.costumerId = costumerId;
        this.price = price;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    @XmlElement
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    @XmlElement
    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public int getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(int price) {
        this.price = price;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    @XmlElement
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", costumerId=" + costumerId +
                ", price=" + price +
                ", items=" + items +
                '}';
    }
}

