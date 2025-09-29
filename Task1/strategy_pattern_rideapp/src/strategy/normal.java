package strategy;

public class normal implements fare {
    @Override
    public double calculateFare(double distanceKm, double durationMin) {
        double baseFare = 50;
        double perKm = 10;
        double perMin = 2;
        return baseFare + (distanceKm * perKm) + (durationMin * perMin);
    }
}
