package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.generator.CollectionFactory.getCollection;
import static be.kdg.se3.opdracht.application.generator.CollectionType.AMOUNT;
import static be.kdg.se3.opdracht.application.generator.CollectionType.PRODUCT;
import static java.util.Objects.requireNonNull;

import be.kdg.se3.opdracht.application.domain.Item;

/**
 * Generator responsible for the creation of the ordered products
 */
public class ItemGenerator implements Generator<Item> {

    @Override
    public Item generate() {
        Item item = new Item();
        item.setItemAmount(requireNonNull(getCollection(AMOUNT)).getRandomId());
        item.setProductId(requireNonNull(getCollection(PRODUCT)).getRandomId());
        return item;
    }
}
