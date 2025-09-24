package sensors;

import model.room;

public class ac implements observer {
    @Override
    public void update(room room) {
        if (room.isOccupied()) {
            System.out.println("AC in Room " + room.getId() + " turned ON.");
        } else {
            System.out.println("AC in Room " + room.getId() + " turned OFF.");
        }
    }
}
