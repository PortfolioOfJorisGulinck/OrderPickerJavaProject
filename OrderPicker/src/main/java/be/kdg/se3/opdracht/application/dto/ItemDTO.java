package be.kdg.se3.opdracht.application.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Data transfer object used for conversion between stream formatOrder() and memory
 */
@XmlType(name = "product", propOrder = {"productId", "itemAmount"})
public class ItemDTO {
    private int productId;
    private int itemAmount;

    public ItemDTO() {
    }

    public ItemDTO(int productId, int itemAmount) {
        this.productId = productId;
        this.itemAmount = itemAmount;
    }

    public int getProductId() {
        return productId;
    }

    @XmlElement
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    @XmlElement
    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "productId=" + productId +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
