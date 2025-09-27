package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;

/**
 * SMS notification service for sending order updates to customers.
 * Implements Observer interface to receive order status changes.
 */
public class customer implements observer {

    private String customerPhone;

    public customer(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public void update(String orderId, String status, String message) {
        String smsText = generateSMSText(orderId, status, message);
        sendSMS(customerPhone, smsText);
    }

    private String generateSMSText(String orderId, String status, String message) {
        switch (status) {
            case "PLACED":
                return "Your order #" + orderId + " has been confirmed. We will notify you when it's ready.";
            case "COOKING":
                return "Your order #" + orderId + " is being prepared. " + message;
            case "READY":
                return "Your order #" + orderId + " is ready for pickup!";
            case "OUT_FOR_DELIVERY":
                return "Your order #" + orderId + " is out for delivery. Expected time: " + message;
            case "DELIVERED":
                return "Your order #" + orderId + " has been delivered. Thank you!";
            default:
                return "Order #" + orderId + " status: " + status;
        }
    }

    private void sendSMS(String phoneNumber, String text) {
        System.out.println("[SMS to " + phoneNumber + "] " + text);
    }
}