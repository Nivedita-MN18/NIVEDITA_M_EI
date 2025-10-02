import builder.MealBuilder;
import items.*;
import meal.Meal;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MealBuilder builder = new MealBuilder();

        Map<Integer, Item> menu = new HashMap<>();
        menu.put(1, new VegBurger());
        menu.put(2, new ChickenBurger());
        menu.put(3, new Coke());
        menu.put(4, new Pepsi());

        System.out.println("=== Welcome to Meal Builder ===");

        boolean running = true;
        while (running) {
            System.out.println("\nMenu");
            for (Map.Entry<Integer, Item> entry : menu.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().name() + " (₹" + entry.getValue().price() + ")");
            }
            System.out.println("5. Finish & Build Meal");
            System.out.print("Enter choice: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            if (choice == 5) {
                running = false;
                break;
            }

            if (menu.containsKey(choice)) {
                Item selectedItem = menu.get(choice);
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();

                if (qty <= 0) {
                    System.out.println("Quantity must be at least 1!");
                    continue;
                }

                for (int i = 0; i < qty; i++) {
                    builder.addItem(selectedItem);
                }
                System.out.println("Added " + qty + " x " + selectedItem.name());
            } else {
                System.out.println("Invalid choice! Try again.");
            }
        }

        // Final meal build
        Meal customMeal = builder.build();
        System.out.println("\n=== Your Meal ===");
        System.out.println(customMeal.summary());
        System.out.println("Total Cost: ₹" + customMeal.getCost());

        sc.close();
    }
}
