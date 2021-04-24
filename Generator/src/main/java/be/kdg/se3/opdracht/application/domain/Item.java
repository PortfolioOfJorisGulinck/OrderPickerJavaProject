package be.kdg.se3.opdracht.application.domain;

import javax.xml.bind.annotation.*;

/**
 * Business entity representing a product with a productId and the amount of items per package of the product
 */
@XmlType(name = "product", propOrder = {"productId", "itemAmount"})
public class Item {
    private int productId;
    private int itemAmount;

    public Item() {
    }

    public Item(int productId, int itemAmount) {
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
