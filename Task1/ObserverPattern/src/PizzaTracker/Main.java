package PizzaTracker;

import PizzaTracker.model.pizza;
import PizzaTracker.observers.*;
public class Main {

    public static void main(String[] args) {
        System.out.println("Pizza Order Tracking System Started");
        System.out.println("=====================================");

        // Create a pizza order
        pizza order = new pizza("PZ001");

        // Create different notification systems
        kitchen kit = new kitchen("Kitchen Terminal #1");
        customer sms = new customer("+1-555-0123");
        driver dr = new driver("Mike Johnson");
        manager dashboard = new manager();

        order.registerObserver(kit);
        order.registerObserver(sms);
        order.registerObserver(dr);
        order.registerObserver(dashboard);

        simulateOrderProcess(order);

        System.out.println("=====================================");
        System.out.println("Order process completed successfully");
    }

    private static void simulateOrderProcess(pizza order) {
        try {
            // Order placed
            order.updateStatus("PLACED", "2x Margherita Pizza");
            Thread.sleep(1000);

            // Cooking started
            order.updateStatus("COOKING", "Estimated time: 15 minutes");
            Thread.sleep(2000);

            // Order ready
            order.updateStatus("READY", "Ready for pickup at counter");
            Thread.sleep(1000);

            // Out for delivery
            order.updateStatus("OUT_FOR_DELIVERY", "20 minutes");
            Thread.sleep(2000);

            // Delivered
            order.updateStatus("DELIVERED", "Successfully delivered");

        } catch (InterruptedException e) {
            System.out.println("Order simulation interrupted");
        }
    }
}