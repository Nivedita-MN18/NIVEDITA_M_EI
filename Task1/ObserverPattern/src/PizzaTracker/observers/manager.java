package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;
import java.time.LocalTime;

public class manager implements observer {
    private int totalOrders = 0;
    private int completedOrders = 0;

    @Override
    public void update(String orderId, String status, String details) {
        String message = getManagerMessage(orderId, status, details);
        if (message != null) {
            String time = LocalTime.now().toString().substring(0, 8);
            System.out.println("\n [Manager] (" + time + ") " + message);
            
            if (status.equals("PLACED")) totalOrders++;
            if (status.equals("DELIVERED")) {
                completedOrders++;
                System.out.println("   Stats: Completed " + completedOrders + "/" + totalOrders + " orders");
            }
        }
    }
    
    private String getManagerMessage(String orderId, String status, String details) {
        switch (status) {
            case "PLACED":
                return "New order #" + orderId + " - " + details;
            case "PREPARING":
                return "Order #" + orderId + " is being prepared";
            case "IN_OVEN":
                return "Order #" + orderId + " is in the oven";
            case "READY":
                return "Order #" + orderId + " is ready for pickup";
            case "OUT_FOR_DELIVERY":
                return "Order #" + orderId + " is out for delivery";
            case "DELIVERED":
                return "Order #" + orderId + " has been delivered";
            default:
                return null;
        }
    }
}