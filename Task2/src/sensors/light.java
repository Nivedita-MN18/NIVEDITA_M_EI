package sensors;
import model.room;

public class light implements occupancyObserver {
    @Override
    public void update(int roomId, boolean occupied) {
        if (occupied) {
            System.out.println("Lights in Room " + roomId + " turned ON");
        } else {
            System.out.println("Lights in Room " + roomId + " turned OFF");
        }
    }
}
