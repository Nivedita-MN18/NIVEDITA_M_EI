package sensors;
import model.Room;

public class light implements occupancyObserver {
    @Override
    public void update(boolean occupied,int roomId) {
        if (occupied) {
            System.out.println("Lights in Room " + roomId + " turned ON");
        } else {
            System.out.println("Lights in Room " + roomId + " turned OFF");
        }
    }
}
