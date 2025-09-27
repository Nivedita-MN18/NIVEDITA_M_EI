package PizzaTracker.observers;

import PizzaTracker.interfaces.observer;
import java.time.LocalTime;

public class manager implements observer {

    private int totalOrders;
    private int completedOrders;

    public manager() {
        this.totalOrders = 0;
        this.completedOrders = 0;
    }

    @Override
    public void update(String orderId, String status, String message) {
        logOrderEvent(orderId, status);
        updateMetrics(status);
    }

    private void logOrderEvent(String orderId, String status) {
        String timestamp = LocalTime.now().toString().substring(0, 8);
        System.out.println("[Manager Dashboard] " + timestamp + " - Order #" + orderId +
                " changed to " + status);
    }

    private void updateMetrics(String status) {
        if ("PLACED".equals(status)) {
            totalOrders++;
        } else if ("DELIVERED".equals(status)) {
            completedOrders++;
            displayMetrics();
        }
    }

    private void displayMetrics() {
        System.out.println("[Manager Dashboard] Orders completed: " + completedOrders +
                "/" + totalOrders);
    }
}