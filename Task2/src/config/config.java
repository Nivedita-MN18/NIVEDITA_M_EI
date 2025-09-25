package config;

import model.room;
import java.util.ArrayList;
import java.util.List;

public class config {
    private static config instance;
    private List<room> rooms;

    private config() {
        rooms = new ArrayList<>();
    }

    public static config getInstance() {
        if (instance == null) {
            instance = new config();
        }
        return instance;
    }

    public void setRoomCount(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.add(new room(i));
        }
    }

    public List<room> getRooms() {
        return rooms;
    }
    public void initializeRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.add(new room(i)); // roomId, default occupants=0
        }
    }
    public room getRoomById(int id) {
        for (room r : rooms) {
            if (r.getId() == id) return r;
        }
        return null;
    }
}
