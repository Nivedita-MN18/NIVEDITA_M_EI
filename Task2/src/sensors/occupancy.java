package sensors;

import model.room;

public class occupancy implements occupancyObserver {
    @Override
    public void update(boolean occupied, int roomId) {
        if (occupied) {
            System.out.println("Room " + roomId + " is now occupied. AC and lights ON.");
        } else {
            System.out.println("Room " + roomId + " is now unoccupied. AC and lights OFF.");
        }
    }
}
