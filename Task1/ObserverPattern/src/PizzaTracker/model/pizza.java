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
        this.status = "PLACED";
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(observer ob) { 
        if (!observers.contains(ob)) {
            observers.add(ob);
        }
    }

    @Override
    public void removeObserver(observer ob) { 
        observers.remove(ob);
    }

    @Override
    public void notifyObservers() { 
        notifyObservers(""); 
    }

    private void notifyObservers(String details) {
        for (observer ob : observers) {
            ob.update(orderId, status, details);
        }
    }

    public void updateStatus(String newStatus, String details) {
        this.status = newStatus;
        System.out.println("\n[System] Order " + orderId + " status: " + newStatus);
        if (details != null && !details.isEmpty()) {
            System.out.println("Details: " + details);
        }
        notifyObservers(details);
    }
}