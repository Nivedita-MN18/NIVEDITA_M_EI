package sensors;

import model.room;

public class occupancy implements observer {
    @Override
    public void update(room room) {
        if (room.isOccupied()) {
            System.out.println("Room " + room.getId() + " is now occupied.");
        } else {
            System.out.println("Room " + room.getId() + " is now empty.");
        }
    }
}
