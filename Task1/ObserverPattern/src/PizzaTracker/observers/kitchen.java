package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;

public class kitchen implements observer {
    private String name;

    public kitchen(String name) { 
        this.name = name; 
    }

    @Override
    public void update(String orderId, String status, String details) {
        String message = getKitchenMessage(orderId, status, details);
        if (message != null) {
            System.out.println("\n👨‍🍳 [" + name + "] " + message);
        }
    }

    private String getKitchenMessage(String orderId, String status, String details) {
        switch (status) {
            case "PLACED":
                return "New order #" + orderId + ": " + details;
            case "PREPARING":
                return "Preparing order #" + orderId;
            case "IN_OVEN":
                return "Baking order #" + orderId;
            case "READY":
                return "Order #" + orderId + " is ready!";
            default:
                return null;
        }
    }
}
