import model.ride;
import strategy.*;
import utils.input;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ride Fare Calculator ===");

        double distance = input.readDouble("Enter distance (km): ");
        double duration = input.readDouble("Enter duration (minutes): ");

        System.out.println("Select ride type:");
        System.out.println("1. Normal");
        System.out.println("2. Premium");
        System.out.println("3. Shared");

        RideType type;
        while (true) {
            int choice = input.readInt("Choice: ");
            switch (choice) {
                case 1 -> { type = RideType.NORMAL; break; }
                case 2 -> { type = RideType.PREMIUM; break; }
                case 3 -> { type = RideType.SHARED; break; }
                default -> { System.out.println("Invalid choice. Try again."); continue; }
            }
            break;
        }

        fare fareStrategy = switch (type) {
            case NORMAL -> new normal();
            case PREMIUM -> new premium();
            case SHARED -> new shared();
        };

        ride r = new ride(distance, duration, fareStrategy);
        System.out.println("\n" + r);
    }
}
