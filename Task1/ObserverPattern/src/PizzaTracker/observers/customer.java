package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;

public class customer implements observer {
    private String phone;

    public customer(String phone) {
        this.phone = phone;
    }

    @Override
    public void update(String orderId, String status, String details) {
        String message = getMessage(orderId, status, details);
        System.out.println("\nSMS to " + phone + ": " + message);
    }

    private String getMessage(String orderId, String status, String details) {
        switch (status) {
            case "PLACED":
                return "Order #" + orderId + " received! " + details;
            case "PREPARING":
                return "Your pizza is being prepared now!";
            case "IN_OVEN":
                return "Your pizza is in the oven!";
            case "READY":
                return "Your order is ready for pickup!";
            case "OUT_FOR_DELIVERY":
                return "Your driver is on the way with your order!";
            case "DELIVERED":
                return "Order delivered! " + details;
            default:
                return "Order update: " + status;
        }
    }
}