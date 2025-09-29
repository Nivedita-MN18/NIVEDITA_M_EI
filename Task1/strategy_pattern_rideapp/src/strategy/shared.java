package strategy;

public class shared implements fare {
    @Override
    public double calculateFare(double distanceKm, double durationMin) {
        double baseFare = 30;
        double perKm = 5;
        double perMin = 1.5;
        return baseFare + (distanceKm * perKm) + (durationMin * perMin);
    }
}

