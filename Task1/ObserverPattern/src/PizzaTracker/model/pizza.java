package PizzaTracker.model;

import PizzaTracker.interfaces.observer;
import PizzaTracker.interfaces.subject;

import java.util.ArrayList;
import java.util.List;

public class pizza implements subject {
    private String orderId;
    private String status;
    private List<observer> observers;

    public pizza(String orderId) {
        this.orderId = orderId;
        this.status = "Placed";
        this.observers = new ArrayList<>();
    }

    // Subject API
    @Override
    public void registerObserver(observer ob) { observers.add(ob); }

    @Override
    public void removeObserver(observer ob) { observers.remove(ob); }

    @Override
    public void notifyObservers() { notifyObservers(""); }

    // Notify all observers with details
    private void notifyObservers(String details) {
        for (observer ob : observers) {
            ob.update(orderId, status, details);
        }
    }

    // Update order status - this triggers notifications
    public void updateStatus(String newStatus, String details) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status changed to: " + newStatus);
        notifyObservers(details);
    }
}