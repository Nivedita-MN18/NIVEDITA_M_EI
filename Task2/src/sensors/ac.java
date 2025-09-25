package sensors;

import model.room;

public class ac implements occupancyObserver {
    @Override
    public void update(int roomId, boolean occupied) {
        if (occupied) {
            System.out.println("AC in Room " + roomId + " turned ON");
        } else {
            System.out.println("AC in Room " + roomId + " turned OFF");
        }
    }
}
