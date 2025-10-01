package main;

import config.*;
import model.Room;
import sensors.*;
import booking.*;
import util.input;

public class Main {

    public static void main(String[] args) {
        int roomCount = input.getInt("Enter number of rooms");
        Config office = Config.getInstance();
        office.initializeRooms(roomCount);

        System.out.println("Office configured with " + roomCount + " rooms");

        for (Room r : office.getRooms()) {
            r.addObserver(new occupancy());
            r.addObserver(new AC());
            r.addObserver(new light());
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
                case 1:
                    int roomId = input.getInt("Room ID");
                    String time = input.getString("Start time (HH:MM)");
                    int duration = input.getInt("Duration (minutes)");
                    Command book = new Command(office.getRoomById(roomId), time, duration);
                    bookingManager.executeCommand(book);
                    break;

                case 2:
                    roomId = input.getInt("Room ID");
                    cancel c = new cancel(office.getRoomById(roomId),"09:00");
                    bookingManager.executeCommand(c);
                    break;

                case 3:
                    roomId = input.getInt("Room ID");
                    int occupants = input.getInt("Number of occupants");
                    Room r = office.getRoomById(roomId);
                    r.setOccupants(occupants);   // triggers observers automatically
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
