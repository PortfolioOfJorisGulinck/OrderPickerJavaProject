package be.kdg.se3.opdracht.application.domain;

public class Item {
    private int productId;
    private int itemAmount;
    private Location location;

    public Item(int productId, int itemAmount) {
        this.productId = productId;
        this.itemAmount = itemAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Item{" +
                "productId=" + productId +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
