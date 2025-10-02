package model;

import sensors.occupancyObserver;
import java.time.LocalTime;
import java.util.*;

public class Room {
    private int id;
    private String name;
    private int maxCapacity;
    private int currentOccupants;
    private boolean occupied;
    private List<occupancyObserver> observers;
    private Map<String, Booking> bookings;
    private Timer autoReleaseTimer;

    private Room(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.maxCapacity = builder.maxCapacity;
        this.currentOccupants = builder.initialOccupants;
        this.occupied = currentOccupants >= 2;
        this.observers = new ArrayList<>();
        this.bookings = new HashMap<>();
        this.autoReleaseTimer = new Timer(true);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentOccupants() { return currentOccupants; }
    public boolean isOccupied() { return occupied; }
    public int getTotalBookings() { return bookings.size(); }
    public int getTotalOccupiedMinutes() {
        return bookings.values().stream().mapToInt(Booking::getDuration).sum();
    }

    // ------------------- Observer Pattern -------------------
    public void addObserver(occupancyObserver o) { observers.add(o); }
    public void removeObserver(occupancyObserver o) { observers.remove(o); }
    private void notifyObservers(boolean occupied) {
        observers.forEach(o -> o.update(occupied, id));
    }

    private void setOccupied(boolean status) {
        this.occupied = status;
        notifyObservers(status);
    }

    // ------------------- Booking -------------------
    public boolean book(String timeStr, int duration) {
        if (!isValidTime(timeStr)) {
            System.out.println("Invalid time. Must be between 09:00-18:00.");
            return false;
        }

        LocalTime start = LocalTime.parse(timeStr);
        LocalTime end = start.plusMinutes(duration);

        // check overlaps
        for (Booking b : bookings.values()) {
            if (start.isBefore(b.getEnd()) && end.isAfter(b.getStart())) {
                System.out.println("Room " + name + " is already booked during this time.");
                return false;
            }
        }

        Booking booking = new Booking(start, duration);
        bookings.put(timeStr, booking);
        System.out.println(name + " booked at " + timeStr + " for " + duration + " mins.");
        scheduleAutoRelease(timeStr); // schedule auto-release immediately
        return true;
    }

    public boolean cancelBooking(String timeStr) {
        if (bookings.containsKey(timeStr)) {
            bookings.remove(timeStr);
            System.out.println("Booking for " + name + " at " + timeStr + " cancelled.");
            return true;
        } else {
            System.out.println("No booking found for " + name + " at " + timeStr);
            return false;
        }
    }

    public boolean addOccupants(String timeStr, int count) {
        Booking booking = bookings.get(timeStr);
        if (booking == null) {
            System.out.println("No booking found for " + name + " at " + timeStr);
            return false;
        }
        if (currentOccupants + count > maxCapacity) {
            System.out.println("Cannot add occupants: exceeds max capacity.");
            return false;
        }

        currentOccupants += count;
        setOccupied(currentOccupants >= 2);
        System.out.println(name + " now has " + currentOccupants + " occupants.");
        scheduleAutoRelease(timeStr); // re-schedule auto-release after occupancy change
        return true;
    }

    public boolean removeOccupants(String timeStr, int count) {
        Booking booking = bookings.get(timeStr);
        if (booking == null) {
            System.out.println("No booking found for " + name + " at " + timeStr);
            return false;
        }
        currentOccupants = Math.max(0, currentOccupants - count);
        setOccupied(currentOccupants >= 2);
        System.out.println(name + " now has " + currentOccupants + " occupants.");
        scheduleAutoRelease(timeStr);
        return true;
    }

    private void scheduleAutoRelease(String timeStr) {
        // Cancel previous tasks for this booking
        autoReleaseTimer.purge();
        autoReleaseTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Booking booking = bookings.get(timeStr);
                if (booking != null && currentOccupants < 2) { // release if less than 2
                    bookings.remove(timeStr);
                    setOccupied(false);
                    System.out.println("Booking for " + name + " at " + timeStr + " auto-released due to inactivity.");
                }
            }
        }, 5 * 60* 1000);
    }

    private boolean isValidTime(String timeStr) {
        try {
            LocalTime t = LocalTime.parse(timeStr);
            return !t.isBefore(LocalTime.of(9, 0)) && !t.isAfter(LocalTime.of(18, 0));
        } catch (Exception e) {
            return false;
        }
    }

    // ------------------- Builder -------------------
    public static class Builder {
        private int id;
        private String name = "Room";
        private int maxCapacity = 10;
        private int initialOccupants = 0;

        public Builder(int id) { this.id = id; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; return this; }
        public Builder setInitialOccupants(int val) { this.initialOccupants = val; return this; }
        public Room build() { return new Room(this); }
    }

    // ------------------- Booking Inner Class -------------------
    private static class Booking {
        private LocalTime start;
        private int duration;

        public Booking(LocalTime start, int duration) {
            this.start = start;
            this.duration = duration;
        }

        public LocalTime getStart() { return start; }
        public LocalTime getEnd() { return start.plusMinutes(duration); }
        public int getDuration() { return duration; }
    }
}
