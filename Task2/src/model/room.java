package model;

import java.util.ArrayList;
import java.util.List;

import sensors.observer;

public class room {
    private int id;                  // Room ID
    private int maxCapacity;         // Max number of people allowed
    private int occupants;           // Current occupants
    private List<observer> observers; // Observers: AC, Light, etc.

    public room(int id) {
        this.id = id;
        this.occupants = 0;
        this.maxCapacity = 5; // default
        observers = new ArrayList<>();
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter & Setter for max capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Occupancy management
    public void addOccupants(int count) {
        this.occupants += count;
        notifyObservers();
    }

    public void removeOccupants(int count) {
        this.occupants -= count;
        if (this.occupants < 0) this.occupants = 0;
        notifyObservers();
    }

    public int getOccupants() {
        return occupants;
    }

    // Observer methods
    public void addObserver(observer observer) {
        observers.add(observer);
    }

    public void removeObserver(observer observer) {
        observers.remove(observer);
    }
    // Add this method inside the Room class
    public boolean isOccupied() {
        return occupants >= 2;
    }

    public void notifyObservers() {
        for (observer obs : observers) {
            obs.update(this);
        }
    }
}
