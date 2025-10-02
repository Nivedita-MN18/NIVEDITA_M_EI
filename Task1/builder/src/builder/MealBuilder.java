package builder;

import items.Item;
import meal.Meal;

public class MealBuilder {
    private final Meal meal = new Meal();

    public MealBuilder addItem(Item item) {
        meal.addItem(item);
        return this;
    }

    public Meal build() {
        return meal;
    }
}
