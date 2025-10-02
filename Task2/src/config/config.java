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

    public List<Room> getRooms() {
        return rooms;
    }

    public void initializeRooms(int count, int maxCapacity) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.add(new Room.Builder(i)
                    .setName("Room " + i)
                    .setMaxCapacity(maxCapacity)
                    .setInitialOccupants(0)
                    .build());
        }
    }

    public Room getRoomById(int id) {
        return rooms.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
