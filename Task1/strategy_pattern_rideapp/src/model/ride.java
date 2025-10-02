package model;

import strategy.fare;

public class ride {
    private fare fare_obj;
    private double distanceKm;
    private double durationMin;
    private final fare fareStrategy;

    public ride(double distanceKm, double durationMin,fare fareStrategy) {
        this.distanceKm = distanceKm;
        this.durationMin = durationMin;
        this.fareStrategy = fareStrategy;

    }
    @Override
    public String toString() {
        return String.format("Ride [%.2f km, %.2f min] => Fare: ₹%.2f",
                distanceKm, durationMin, getFare());
    }

    public double getFare() {
        return fare_obj.calculateFare(distanceKm, durationMin);
    }
}
