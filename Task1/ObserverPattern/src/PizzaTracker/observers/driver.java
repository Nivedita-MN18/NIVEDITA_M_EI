package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;
public class driver implements observer {

    private String driverName;

    public driver(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public void update(String orderId, String status, String message) {
        if ("READY".equals(status)) {
            assignDelivery(orderId, message);
        } else if ("DELIVERED".equals(status)) {
            completeDelivery(orderId);
        }
    }

    private void assignDelivery(String orderId, String message) {
        System.out.println("[Driver App - " + driverName + "] NEW DELIVERY ASSIGNED: Order #" +
                orderId + " - " + message);
    }

    private void completeDelivery(String orderId) {
        System.out.println("[Driver App - " + driverName + "] DELIVERY COMPLETED: Order #" + orderId);
    }
}
