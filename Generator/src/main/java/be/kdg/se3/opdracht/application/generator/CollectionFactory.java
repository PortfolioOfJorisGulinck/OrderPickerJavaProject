package be.kdg.se3.opdracht.application.generator;

/**
 * Factory responsible for the creation of an extension of the {@link AdjustableCollection} class
 */
public class CollectionFactory {

    public static AdjustableCollection getCollection(CollectionType type) {
        if (type == null) {
            throw new UnsupportedOperationException("Please fill in a valid CollectionType");
        }
        switch (type) {
            case CUSTOMER:
                return new CustomerCollection();
            case PRICE:
                return new PriceCollection();
            case ITEM:
                return new ItemCollection();
            case PRODUCT:
                return new ProductCollection();
            case AMOUNT:
                return new AmountCollection();
        }
        return null;
    }
}
