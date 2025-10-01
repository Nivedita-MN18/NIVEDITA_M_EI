
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<SpotContext> spots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            spots.add(new SpotContext(i));
        }

        ParkingSpot compact = ParkingSpotFactory.getParkingSpot("Compact", 5.0);
        ParkingSpot large = ParkingSpotFactory.getParkingSpot("Large", 8.0);

        for (int i = 0; i < spots.size(); i++) {
            SpotContext spot = spots.get(i);
            if (i % 2 == 0) compact.displaySpotInfo(spot);
            else large.displaySpotInfo(spot);
        }

        spots.get(0).setOccupied(true);
        spots.get(3).setOccupied(true);

        System.out.println("\nAfter some spots are occupied:");
        for (int i = 0; i < spots.size(); i++) {
            SpotContext spot = spots.get(i);
            if (i % 2 == 0) compact.displaySpotInfo(spot);
            else large.displaySpotInfo(spot);
        }
    }
}
