package sensors;
import model.room;

public class light implements observer {
    @Override
    public void update(room room) {
        if (room.isOccupied()) {
            System.out.println("Lights in Room " + room.getId() + " turned ON.");
        } else {
            System.out.println("Lights in Room " + room.getId() + " turned OFF.");
        }
    }
}
