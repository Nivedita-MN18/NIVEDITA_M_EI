
import java.util.LinkedHashMap;
import java.util.Map;

public class Meal {
    private final Map<String, String> items;

    private Meal(MealBuilder builder) {
        this.items = builder.items;
    }

    public void showMeal() {
        System.out.println("Meal Details:");
        if (items.isEmpty()) {
            System.out.println("No items selected.");
        } else {
            items.forEach((key, value) -> System.out.println(key + ": " + value));
        }
        System.out.println("-----------------------");
    }

    public static class MealBuilder {
        private final Map<String, String> items = new LinkedHashMap<>();

        public MealBuilder addItem(String type, String name) {
            items.put(type, name);
            return this;
        }

        public Meal build() {
            return new Meal(this);
        }
    }
}
