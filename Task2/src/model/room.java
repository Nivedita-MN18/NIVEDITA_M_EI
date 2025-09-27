package model;

import sensors.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class room implements occupancySubject {

    private int id;
    private String name;
    private int maxCapacity;
    private boolean occupied;
    private int occupants;
    private Map<String, Integer> bookings;
    private List<occupancyObserver> observers; // observer list
    public room(int id) {
        this(id, "Room " + id); // call the main constructor
    }
    public room(int id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new HashMap<>();
        this.occupied = false;
        this.maxCapacity = 0;
        this.occupants = 0;
        this.observers = new ArrayList<>();
    }

    // getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getOccupants() { return occupants; }
    public boolean isOccupied() { return occupied; }
    public void setMaxCapacity(int capacity) { this.maxCapacity = capacity; }
    public int getMaxCapacity() { return maxCapacity; }
    public Map<String, Integer> getBookings() { return bookings; }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
        this.occupied = occupants >= 2;
        notifyObservers(); // notify sensors when occupancy changes
    }

    public boolean book(String time, int duration) {
        if (bookings.containsKey(time)) return false;
        bookings.put(time, duration);
        return true;
    }

    public boolean cancelBooking(String time) {
        if (bookings.containsKey(time)) {
            bookings.remove(time);
            return true;
        }
        return false;
    }

    // Observer pattern methods
    @Override
    public void addObserver(occupancyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(occupancyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (occupancyObserver observer : observers) {
            observer.update(this.occupied,this.id);
        }
    }
}
