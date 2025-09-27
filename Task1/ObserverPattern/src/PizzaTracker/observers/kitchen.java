package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;

/**
 * Kitchen display system that shows order status to kitchen staff.
 * Implements Observer interface to receive order updates.
 */
public class kitchen implements observer {

    private String displayName;

    public kitchen(String displayName) { this.displayName = displayName; }

    @Override
    public void update(String orderId, String status, String message) {
        switch (status) {
            case "PLACED":
                displayKitchenMessage(orderId, "NEW ORDER RECEIVED - Start preparation");
                break;
            case "COOKING":
                displayKitchenMessage(orderId, "IN PREPARATION - " + message);
                break;
            case "READY":
                displayKitchenMessage(orderId, "READY FOR PICKUP");
                break;
            case "OUT_FOR_DELIVERY":
                displayKitchenMessage(orderId, "OUT FOR DELIVERY");
                break;
        }
    }

    private void displayKitchenMessage(String orderId, String displayMessage) {
        System.out.println("[" + displayName + "] Order #" + orderId + ": " + displayMessage);
    }
}
