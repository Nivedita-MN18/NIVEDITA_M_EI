import java.util.*;

public class StockSubject {
    private Map<String, Double> stocks = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    public void setStockPrice(String stockName, double price) {
        stocks.put(stockName, price);
        notifyObservers(stockName, price);
    }

    private void notifyObservers(String stockName, double price) {
        for (StockObserver obs : observers) {
            obs.update(stockName, price);
        }
    }
}
