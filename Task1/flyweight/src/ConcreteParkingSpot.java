import model.SpotContext;

public class ConcreteParkingSpot implements flyweight.ParkingSpot {

    private final String type;      // intrinsic state
    private final double rate;

    public ConcreteParkingSpot(String type, double rate) {
        this.type = type;
        this.rate = rate;
    }

    @Override
    public void displaySpotInfo(SpotContext context) {
        String status = context.isOccupied() ? "Occupied" : "Available";
        System.out.printf("Spot #%d | %s | Type: %s | Rate: $%.2f/hr%n",
                context.getSpotNumber(), status, type, rate);
    }
}
