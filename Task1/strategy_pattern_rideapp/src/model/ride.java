package model;

import strategy.fare;

public class ride {
    private fare fare_obj;
    private double distanceKm;
    private double durationMin;

    public ride(double distanceKm, double durationMin) {
        this.distanceKm = distanceKm;
        this.durationMin = durationMin;
    }

    public void setFareStrategy(fare fare_obj) {
        this.fare_obj = fare_obj;
    }

    public double getFare() {
        return fare_obj.calculateFare(distanceKm, durationMin);
    }
}
