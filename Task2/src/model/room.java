package model;

import java.time.Duration;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Room implements occupancySubject {
    private int id;
    private String name;
    private int maxCapacity;
    private int occupants;
    private boolean occupied;
    private Map<String, BookingInfo> bookings;
    private List<occupancyObserver> observers;
    private int totalBookings;
    private int totalOccupiedMinutes;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    public Room(int id, String name, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.occupied = false;
        this.occupants = 0;
        this.bookings = new HashMap<>();
        this.observers = new ArrayList<>();
        this.totalBookings = 0;
        this.totalOccupiedMinutes = 0;
    }
    public Room(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.maxCapacity = builder.maxCapacity;
        this.occupants = builder.initialOccupants;
        this.occupied = this.occupants > 0;
        this.bookings = new HashMap<>();
        this.observers = new ArrayList<>();
    }
    class BookingInfo {
        int duration;
        int occupants;

        public BookingInfo(int duration) {
            this.duration = duration;
            this.occupants = 0; // initially 0
        }
    }

    // Builder class
    public static class Builder {
        private int id;
        private String name = "Room"; // default
        private int maxCapacity = 5;  // default
        private int initialOccupants  = 0;    // default

        public Builder(int id) { this.id = id; }

        public Builder setName(String name) { this.name = name; return this; }
        public Builder setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; return this; }
        public Builder setInitialOccupants(int occupants) { this.initialOccupants  = occupants; return this; }

        public Room build() {
            Room r = new Room(id, name, maxCapacity);
            return r;
        }    }

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
    public int getTotalBookings() { return totalBookings; }
    public int getTotalOccupiedMinutes() { return totalOccupiedMinutes; }

    public String getName() { return name; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getOccupants() { return occupants; }
    public boolean isOccupied() { return occupied; }
    public void addOccupants(String time, int newOccupants) {
        BookingInfo booking = bookings.get(time);
        if (booking == null) {
            System.out.println("No booking exists at " + time);
            return;
        }

        if (booking.occupants + newOccupants > maxCapacity) {
            System.out.println("Cannot add " + newOccupants + " occupants. Max capacity: " + maxCapacity);
            return;
        }

        booking.occupants += newOccupants;
        updateOccupied();
    }

    public void removeOccupants(String time, int leaving) {
        BookingInfo booking = bookings.get(time);
        if (booking == null || leaving > booking.occupants) {
            System.out.println("Invalid occupants removal for time " + time);
            return;
        }

        booking.occupants -= leaving;
        updateOccupied();
    }


    private void updateOccupied() {
        boolean anyOccupied = false;
        for (BookingInfo b : bookings.values()) {
            if (b.occupants >= 2) {
                anyOccupied = true;
                break;
            }
        }
        occupied = anyOccupied;
        notifyObservers();
    }


    public boolean book(String time, int duration) {
        // Validate duration
        if (duration <= 0) {
            System.out.println("Invalid duration. Must be > 0.");
            return false;
        }

        // Validate time
        LocalTime startTime;
        try {
            startTime = LocalTime.parse(time, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Use HH:mm.");
            return false;
        }

        // Check office hours
        LocalTime officeStart = LocalTime.of(9, 0);
        LocalTime officeEnd = LocalTime.of(18, 0);
        LocalTime endTime = startTime.plusMinutes(duration);
        if (startTime.isBefore(officeStart) || endTime.isAfter(officeEnd)) {
            System.out.println("Booking must be within office hours (09:00 - 18:00).");
            return false;
        }

        // Check overlaps
        for (Map.Entry<String, BookingInfo> booking : bookings.entrySet()) {
            LocalTime bookedStart = LocalTime.parse(booking.getKey(), TIME_FORMAT);
            LocalTime bookedEnd = bookedStart.plusMinutes(booking.getValue().duration);
            if (startTime.isBefore(bookedEnd) && endTime.isAfter(bookedStart)) {
                System.out.println("Time slot overlaps with existing booking: " + booking.getKey());
                return false;
            }
        }

        // Check capacity
        if (occupants >= maxCapacity) {
            System.out.println("Cannot book. Room at full capacity (" + maxCapacity + ").");
            return false;
        }

        bookings.put(time, new BookingInfo(duration));
        totalBookings++;

        // Start auto-release timer
        scheduleAutoRelease(time, duration);

        return true;
    }

    public boolean cancelBooking(String time) {
        if (bookings.containsKey(time)) {
            bookings.remove(time);
            System.out.println("Booking for " + name + " at " + time + " cancelled.");
            return true;
        }
        return false;
    }

    // Auto-release bookings if not occupied within 5 minutes
    private void scheduleAutoRelease(String time, int duration) {
        LocalTime bookingTime = LocalTime.parse(time, TIME_FORMAT);
        long delay = Duration.between(LocalTime.now(), bookingTime.plusMinutes(5)).toMillis();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            if (!occupied && bookings.containsKey(time)) {
                bookings.remove(time);
                System.out.println("Booking at " + time + " for " + name + " auto-released (room not occupied).");
            }
        }, Math.max(delay, 0), TimeUnit.MILLISECONDS);
    }



}
