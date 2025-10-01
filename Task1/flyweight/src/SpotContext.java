
public class SpotContext {
    private int spotNumber;
    private boolean occupied;

    public SpotContext(int spotNumber) {
        this.spotNumber = spotNumber;
        this.occupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
