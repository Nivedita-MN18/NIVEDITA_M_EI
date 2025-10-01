package PizzaTracker;

import PizzaTracker.model.pizza;
import PizzaTracker.observers.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🍕 Pizza Order Tracking System 🍕");
        System.out.println("=====================================\n");

        // Get order details from user
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        
        System.out.print("Enter customer phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter driver name: ");
        String driverName = scanner.nextLine();
        
        System.out.print("Enter pizza type and quantity (e.g., 2x Margherita): ");
        String orderDetails = scanner.nextLine();
        
        // Create a pizza order
        pizza order = new pizza(orderId);

        // Create different notification systems
        kitchen kitchen = new kitchen("Kitchen Terminal #1");
        customer customer = new customer(phone);
        driver driver = new driver(driverName);
        manager manager = new manager();

        // Register observers
        order.registerObserver(kitchen);
        order.registerObserver(customer);
        order.registerObserver(driver);
        order.registerObserver(manager);

        // Start order process
        System.out.println("\nProcessing order...");
        simulateOrderProcess(order, orderDetails);

        System.out.println("\n=====================================");
        System.out.println("✅ Order process completed!");
    }

    private static void simulateOrderProcess(pizza order, String orderDetails) {
        try {
            // Order placed
            order.updateStatus("PLACED", orderDetails);
            waitForUser();
            
            // Cooking started
            order.updateStatus("PREPARING", "Your pizza is being prepared");
            waitForUser();
            
            // In oven
            order.updateStatus("IN_OVEN", "Baking your delicious pizza");
            waitForUser();
            
            // Order ready
            order.updateStatus("READY", "Your order is ready for pickup!");
            waitForUser();
            
            // Out for delivery
            order.updateStatus("OUT_FOR_DELIVERY", "Your driver is on the way");
            waitForUser();
            
            // Delivered
            order.updateStatus("DELIVERED", "Enjoy your meal! 🍕");
            
        } catch (InterruptedException e) {
            System.out.println("Order process was interrupted!");
        }
    }
    
    private static void waitForUser() throws InterruptedException {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}