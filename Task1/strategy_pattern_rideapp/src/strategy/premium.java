package strategy;

public class premium implements fare {
    @Override
    public double calculateFare(double distanceKm, double durationMin) {
        double baseFare = 100;
        double perKm = 20;
        double perMin = 4;
        double surgeMultiplier = 1.5;
        return (baseFare + (distanceKm * perKm) + (durationMin * perMin)) * surgeMultiplier;
    }
}
