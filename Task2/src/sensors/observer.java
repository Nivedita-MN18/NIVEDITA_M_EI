package sensors;

import model.Room;

public interface observer {
    void update(Room r);  // called whenever room occupancy changes
}
