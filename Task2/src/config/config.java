package config;

import model.Room;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private static Config instance;
    private List<Room> rooms;

    private Config() {
        rooms = new ArrayList<>();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public void setRoomCount(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.add(new Room(i));
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public void initializeRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.add(new Room(i)); // roomId, default occupants=0
        }
    }
    public Room getRoomById(int id) {
        for (Room r : rooms) {
            if (r.getId() == id) return r;
        }
        return null;
    }
}
