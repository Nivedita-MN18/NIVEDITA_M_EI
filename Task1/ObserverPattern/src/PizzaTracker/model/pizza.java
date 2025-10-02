package PizzaTracker.model;

import PizzaTracker.interfaces.observer;
import PizzaTracker.interfaces.subject;
import java.util.ArrayList;
import java.util.List;

public class pizza implements subject {
    private final String orderId;
    private final List<String> pizzas;
    private final List<observer> observers;
    private String status;

    public pizza(String orderId, List<String> pizzas) {
        this.orderId = orderId;
        this.pizzas = pizzas;
        this.observers = new ArrayList<>();
        this.status = "PLACED";
    }

    @Override
    public void registerObserver(observer ob) {
        if (!observers.contains(ob)) observers.add(ob);
    }

    @Override
    public void removeObserver(observer ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObservers() {
        notifyObservers("");
    }

    public void updateStatus(String newStatus, String details) {
        this.status = newStatus;
        System.out.println("\n[System] Order " + orderId + " status: " + newStatus);
        if (!details.isBlank()) System.out.println("Details: " + details);
        notifyObservers(details);
    }

    private void notifyObservers(String details) {
        for (observer ob : observers) {
            ob.update(orderId, status, details);
        }
    }

    public List<String> getPizzas() { return pizzas; }
    public String getOrderId() { return orderId; }
}
