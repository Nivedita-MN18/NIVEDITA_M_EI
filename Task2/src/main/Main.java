package main;

import config.Config;
import model.Room;
import sensors.SensorFactory;
import booking.*;
import util.input;

public class Main {

    public static void main(String[] args) {
        int roomCount = input.getInt("Enter number of rooms");
        Config office = Config.getInstance();
        office.initializeRooms(roomCount);

        System.out.println("Office configured with " + roomCount + " rooms");

        for (Room r : office.getRooms()) {
            r.addObserver(SensorFactory.createSensor("AC"));
            r.addObserver(SensorFactory.createSensor("Light"));
            r.addObserver(SensorFactory.createSensor("Occupancy"));
        }

        manager bookingManager = new manager();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Smart Office Menu ---");
            System.out.println("1. Book room");
            System.out.println("2. Cancel booking");
            System.out.println("3. Add occupants");
            System.out.println("4. Exit");

            int choice = input.getInt("Choose option");
            switch (choice) {
                case 1: // Book room
                    int roomId = input.getInt("Room ID");
                    Room bookRoom = office.getRoomById(roomId);
                    if (bookRoom == null) {
                        System.out.println("Invalid Room ID");
                        break;
                    }
                    String time = input.getString("Start time (HH:MM)");
                    int duration = input.getInt("Duration (minutes)");
                    Command book = new Command(bookRoom, time, duration);
                    bookingManager.executeCommand(book);
                    break;

                case 2: // Cancel booking
                    roomId = input.getInt("Room ID");
                    Room cancelRoom = office.getRoomById(roomId);
                    if (cancelRoom == null) {
                        System.out.println("Invalid Room ID");
                        break;
                    }
                    String cancelTime = input.getString("Start time to cancel (HH:MM)");
                    Cancel cancel = new Cancel(cancelRoom, cancelTime);
                    bookingManager.executeCommand(cancel);
                    break;

                case 3: // Add occupants
                    roomId = input.getInt("Room ID");
                    Room occRoom = office.getRoomById(roomId);
                    if (occRoom == null) {
                        System.out.println("Invalid Room ID");
                        break;
                    }
                    int occupants = input.getInt("Number of occupants");
                    occRoom.setOccupants(occupants);   // triggers observers automatically
                    break;

                case 4: // Exit
                    running = false;
                    System.out.println("Exiting Smart Office...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
