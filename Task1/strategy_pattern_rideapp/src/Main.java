import model.ride;
import strategy.normal;
import strategy.premium;
import strategy.shared;
import utils.input;

public class Main {
    public static void main(String[] args) {

        double distance = input.readDouble("Enter distance in km: ");
        double duration = input.readDouble("Enter duration in minutes: ");
        System.out.println("Select ride type: 1.Normal 2.Premium 3.Shared");
        int type = input.readInt("Choice: ");

        ride ride_obj = new ride(distance, duration);

        switch (type) {
            case 1: ride_obj.setFareStrategy(new normal()); break;
            case 2: ride_obj.setFareStrategy(new premium()); break;
            case 3: ride_obj.setFareStrategy(new shared()); break;
            default: System.out.println("Invalid choice"); return;
        }

        System.out.println("Calculated Fare: " + ride_obj.getFare());
    }
}
