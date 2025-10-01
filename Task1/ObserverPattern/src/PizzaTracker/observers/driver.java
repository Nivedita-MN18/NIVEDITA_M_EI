package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;

public class driver implements observer {
    private String name;

    public driver(String name) {
        this.name = name;
    }

    @Override
    public void update(String orderId, String status, String details) {
        String message = getDriverMessage(orderId, status, details);
        if (message != null) {
            System.out.println("\n🚗 [" + name + "'s App] " + message);
        }
    }

    private String getDriverMessage(String orderId, String status, String details) {
        switch (status) {
            case "READY":
                return "Order #" + orderId + " ready for delivery!";
            case "OUT_FOR_DELIVERY":
                return "Delivering order #" + orderId + " now!";
            case "DELIVERED":
                return "Successfully delivered order #" + orderId;
            default:
                return null;
        }
    }
}
