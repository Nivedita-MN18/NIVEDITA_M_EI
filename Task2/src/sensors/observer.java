package sensors;

import model.room;

public interface observer {
    void update(room r);  // called whenever room occupancy changes
}
