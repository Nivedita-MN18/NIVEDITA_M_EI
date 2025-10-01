package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sensors.occupancyObserver;
import sensors.occupancySubject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Room implements occupancySubject {
    private int id;
    private String name;
    private int maxCapacity;
    private int occupants;
    private boolean occupied;
    private Map<String, Integer> bookings;
    private List<occupancyObserver> observers;

    public Room(Builder builder) {
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

    public boolean book(String time, int duration) {
        if (duration <= 0) {
            System.out.println("Invalid duration. Must be > 0.");
            return false;
        }

        // Validate time format and office hours (09:00 - 18:00)
        LocalTime startTime;
        try {
            startTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Use HH:mm.");
            return false;
        }

        LocalTime officeStart = LocalTime.of(9, 0);
        LocalTime officeEnd = LocalTime.of(18, 0);
        LocalTime endTime = startTime.plusMinutes(duration);
        if (startTime.isBefore(officeStart) || endTime.isAfter(officeEnd)) {
            System.out.println("Booking must be within office hours (09:00 - 18:00).");
            return false;
        }

        // Check for overlapping bookings
        for (Map.Entry<String, Integer> booking : bookings.entrySet()) {
            LocalTime bookedStart = LocalTime.parse(booking.getKey(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime bookedEnd = bookedStart.plusMinutes(booking.getValue());
            if ((startTime.isBefore(bookedEnd) && endTime.isAfter(bookedStart))) {
                System.out.println("Time slot overlaps with existing booking: " + booking.getKey());
                return false;
            }
        }

        // Check occupancy limit
        if (occupants >= maxCapacity) {
            System.out.println("Cannot book. Room at max capacity.");
            return false;
        }

        // Add booking
        bookings.put(time, duration);
        System.out.println(name + " booked at " + time + " for " + duration + " mins.");
        return true;
    }

    public boolean cancelBooking(String time) {
        // Validate time format
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Use HH:mm.");
            return false;
        }

        if (bookings.containsKey(time)) {
            bookings.remove(time);
            System.out.println("Booking for " + name + " at " + time + " cancelled.");
            return true;
        } else {
            System.out.println("No booking found for " + name + " at " + time);
            return false;
        }
    }

    // ---------------- Occupancy Methods ----------------
    public void setOccupants(int occupants) {
        if (occupants < 0) {
            System.out.println("Occupants cannot be negative.");
            return;
        }
        if (occupants > maxCapacity) {
            System.out.println("Occupants exceed max capacity (" + maxCapacity + ").");
            return;
        }
        this.occupants = occupants;
        this.occupied = occupants > 0;
        notifyObservers();
    }

}
