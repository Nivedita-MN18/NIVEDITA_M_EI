package sensors;

public interface occupancyObserver {
    void update(boolean occupied,int roomId);
}
