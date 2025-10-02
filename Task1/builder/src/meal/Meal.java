package meal;

import items.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Meal {
    private final List<Item> items = new ArrayList<>();

    public Meal addItem(Item item) {
        items.add(item);
        return this;
    }

    public float getCost() {
        return (float) items.stream().mapToDouble(Item::price).sum();
    }

    public String summary() {
        return items.stream()
                .map(i -> String.format("%-15s | %-8s | %.2f",
                        i.name(), i.packing().pack(), i.price()))
                .collect(Collectors.joining("\n"));
    }
}
