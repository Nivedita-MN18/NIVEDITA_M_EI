import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<SpotContext> spots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            spots.add(new SpotContext(i));
        }

        ParkingSpot compact = ParkingSpotFactory.getParkingSpot("Compact", 5.0);
        ParkingSpot large = ParkingSpotFactory.getParkingSpot("Large", 8.0);

        System.out.println("=== Parking Lot Status ===");
        for (int i = 0; i < spots.size(); i++) {
            SpotContext spot = spots.get(i);
            if (i % 2 == 0) compact.displaySpotInfo(spot);
            else large.displaySpotInfo(spot);
        }

        System.out.println("\nMark spots as occupied.");
        System.out.print("Enter spot number to occupy: ");
        int spotNum = sc.nextInt();
        if (spotNum > 0 && spotNum <= spots.size()) {
            spots.get(spotNum - 1).setOccupied(true);
        }

        System.out.println("\nUpdated Parking Lot Status");
        for (int i = 0; i < spots.size(); i++) {
            SpotContext spot = spots.get(i);
            if (i % 2 == 0) compact.displaySpotInfo(spot);
            else large.displaySpotInfo(spot);
        }

        System.out.println("\nTotal unique ParkingSpot objects created: " +
                ParkingSpotFactory.getTotalSpotsCreated());

        sc.close();
    }
}
