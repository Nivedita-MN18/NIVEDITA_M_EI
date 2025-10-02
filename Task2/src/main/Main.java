package main;

import config.Config;
import model.Room;
import sensors.SensorFactory;
import booking.*;
import util.input;

public class Main {
    public static void main(String[] args) {
        Config office = Config.getInstance();
        int roomCount = input.getInt("Enter number of rooms");
        int maxCapacity = input.getInt("Enter max capacity per room");
        if(maxCapacity <= 0) {
            System.out.println("Invalid capacity. Enter a positive number.");
            return;
        }
        office.initializeRooms(roomCount, maxCapacity);

        for (Room r : office.getRooms()) {
            r.addObserver(SensorFactory.createSensor("AC"));
            r.addObserver(SensorFactory.createSensor("Light"));
            r.addObserver(SensorFactory.createSensor("Occupancy"));
        }

        manager manager_obj = new manager();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Smart Office Menu ---");
            System.out.println("1. Book room");
            System.out.println("2. Cancel booking");
            System.out.println("3. Add occupants");
            System.out.println("4. Remove occupants");
            System.out.println("5. Room usage summary");
            System.out.println("6. Exit");

            int choice = input.getInt("Choose option");
            switch(choice) {
                case 1 -> {
                    Room r = getRoom(office); if(r==null) break;
                    String time = input.getString("Booking time (HH:MM)");
                    int dur = input.getInt("Duration in minutes");
                    manager_obj.executeCommand(new Command(r,time,dur));
                }
                case 2 -> {
                    Room r = getRoom(office); if(r==null) break;
                    String time = input.getString("Booking time (HH:MM)");
                    manager_obj.executeCommand(new Cancel(r,time));
                }
                case 3 -> {
                    Room r = getRoom(office); if(r==null) break;
                    String time = input.getString("Booking time (HH:MM)");
                    int num = input.getInt("Number of occupants to add");
                    r.addOccupants(time,num);
                }
                case 4 -> {
                    Room r = getRoom(office); if(r==null) break;
                    String time = input.getString("Booking time (HH:MM)");
                    int num = input.getInt("Number of occupants to remove");
                    r.removeOccupants(time,num);
                }
                case 5 -> {
                    System.out.println("Room Usage Summary");
                    for(Room r: office.getRooms()) {
                        System.out.println(r.getName() + " | Bookings: " + r.getTotalBookings() +
                                " | Occupied mins: " + r.getTotalOccupiedMinutes());
                    }
                }
                case 6 -> { running = false; System.out.println("Exiting..."); }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static Room getRoom(Config office) {
        int roomId = input.getInt("Room ID");
        Room r = office.getRoomById(roomId);
        if(r==null) System.out.println("Invalid Room ID");
        return r;
    }
}
