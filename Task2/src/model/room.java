package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sensors.occupancyObserver;
import sensors.occupancySubject;

public class Room implements occupancySubject {
    private int id;
    private String name;
    private int maxCapacity;
    private int occupants;
    private boolean occupied;
    private Map<String, Integer> bookings;
    private List<occupancyObserver> observers;

    private Room(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.maxCapacity = builder.maxCapacity;
        this.occupants = builder.occupants;
        this.occupied = this.occupants > 0;
        this.bookings = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    // Builder class
    public static class Builder {
        private int id;
        private String name = "Room"; // default
        private int maxCapacity = 5;  // default
        private int occupants = 0;    // default

        public Builder(int id) { this.id = id; }

        public Builder setName(String name) { this.name = name; return this; }
        public Builder setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; return this; }
        public Builder setInitialOccupants(int occupants) { this.occupants = occupants; return this; }

        public Room build() { return new Room(this); }
    }

    // occupancySubject methods
    @Override
    public void addObserver(occupancyObserver observer) { observers.add(observer); }

    @Override
    public void removeObserver(occupancyObserver observer) { observers.remove(observer); }

    @Override
    public void notifyObservers() {
        for (occupancyObserver o : observers) o.update(this.occupied, this.id);
    }

    // getters & setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getOccupants() { return occupants; }
    public boolean isOccupied() { return occupied; }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
        this.occupied = occupants > 0;
        notifyObservers();
    }

    public boolean book(String time, int duration) {
        if (bookings.containsKey(time)) return false;
        bookings.put(time, duration);
        return true;
    }

    public boolean cancelBooking(String time) {
        return bookings.remove(time) != null;
    }
}
