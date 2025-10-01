package sensors;

import model.Room;

public class AC implements occupancyObserver {
    @Override
    public void update(boolean occupied,int roomId) {
        if (occupied) {
            System.out.println("AC in Room " + roomId + " turned ON");
        } else {
            System.out.println("AC in Room " + roomId + " turned OFF");
        }
    }
}
