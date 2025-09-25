package sensors;


public interface occupancySubject {
    void addObserver(occupancyObserver observer);
    void removeObserver(occupancyObserver observer);
    void notifyObservers();
}
