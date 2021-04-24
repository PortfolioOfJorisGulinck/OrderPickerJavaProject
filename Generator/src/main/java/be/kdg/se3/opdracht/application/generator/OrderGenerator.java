package be.kdg.se3.opdracht.application.generator;

import static be.kdg.se3.opdracht.application.generator.CollectionFactory.getCollection;
import static be.kdg.se3.opdracht.application.generator.CollectionType.CUSTOMER;
import static be.kdg.se3.opdracht.application.generator.CollectionType.ITEM;
import static be.kdg.se3.opdracht.application.generator.CollectionType.PRICE;
import static be.kdg.se3.opdracht.application.properties.Properties.MAX_ID_ORDER;
import static be.kdg.se3.opdracht.application.properties.Properties.MIN_ID_ORDER;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import be.kdg.se3.opdracht.application.domain.Item;
import be.kdg.se3.opdracht.application.domain.Order;

/**
 * Generator responsible for the creation of the Order
 */
public class OrderGenerator extends AdjustableCollection implements Generator<Order> {

    private int currentOrderId;
    private ItemGenerator itemGenerator;

    public OrderGenerator() {
        this.currentOrderId = getLowerLimit();
        this.itemGenerator = new ItemGenerator();
    }

    @Override
    public Order generate() {
        Order order = new Order();
        order.setOrderId(currentOrderId++);
        order.setCostumerId(requireNonNull(getCollection(CUSTOMER)).getRandomId());
        order.setPrice(requireNonNull(getCollection(PRICE)).getRandomId());
        order.setItems(generateItems());

        return order;
    }

    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();

        int itemsToGenerate = requireNonNull(getCollection(ITEM)).getRandomId();

        for (int i = 0; i < itemsToGenerate; i++) {
            items.add(itemGenerator.generate());
        }
        return items;
    }

    @Override
    protected int getLowerLimit() {
        return MIN_ID_ORDER;
    }

    @Override
    protected int getUpperLimit() {
        return MAX_ID_ORDER;
    }
}
