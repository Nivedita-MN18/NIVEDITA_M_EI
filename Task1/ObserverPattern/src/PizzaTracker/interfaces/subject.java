package PizzaTracker.interfaces;

public interface subject {
    void registerObserver(observer ob);
    void removeObserver(observer ob);
    void notifyObservers();
}