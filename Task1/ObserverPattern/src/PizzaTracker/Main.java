package PizzaTracker;

import PizzaTracker.model.*;
import PizzaTracker.observers.*;

import java.sql.Driver;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Pizza Order Tracking System");

        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine().trim();

        List<String> customerPhones = new ArrayList<>();
        while (true) {
            System.out.print("Enter customer phone number (or 'done'): ");
            String phone = scanner.nextLine().trim();
            if (phone.equalsIgnoreCase("done")) break;
            if (!phone.isBlank()) customerPhones.add(phone);
        }

        System.out.print("Enter driver name: ");
        String driverName = scanner.nextLine().trim();

        List<String> pizzas = new ArrayList<>();
        while (true) {
            System.out.print("Enter pizza type and quantity (or 'done'): ");
            String pizzaInput = scanner.nextLine().trim();
            if (pizzaInput.equalsIgnoreCase("done")) break;
            if (!pizzaInput.isBlank()) pizzas.add(pizzaInput);
        }

        if (pizzas.isEmpty()) {
            System.out.println("No pizzas in order! Exiting.");
            return;
        }

        pizza order = new pizza(orderId, pizzas);

        // Register observers
        kitchen kit = new kitchen("Terminal #1");
        order.registerObserver(kit);
        for (String phone : customerPhones) order.registerObserver(new customer(phone));
        order.registerObserver(new driver(driverName));
        order.registerObserver(new manager());

        System.out.println("\nProcessing order...");
        simulateOrderProcess(order);

        System.out.println("\nOrder process completed!");
    }

    private static void simulateOrderProcess(pizza order) {
        try {
            String pizzaList = String.join(", ", order.getPizzas());
            order.updateStatus("PLACED", pizzaList);
            waitForUser();

            order.updateStatus("PREPARING", "Pizzas being prepared");
            waitForUser();

            order.updateStatus("IN_OVEN", "Baking pizzas");
            waitForUser();

            order.updateStatus("READY", "Pizzas ready for pickup");
            waitForUser();

            order.updateStatus("OUT_FOR_DELIVERY", "Driver on the way");
            waitForUser();

            order.updateStatus("DELIVERED", "Enjoy your meal!");
        } catch (InterruptedException e) {
            System.out.println("Order process was interrupted!");
        }
    }

    private static void waitForUser() throws InterruptedException {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
