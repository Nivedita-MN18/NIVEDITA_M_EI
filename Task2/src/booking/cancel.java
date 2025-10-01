package booking;

import model.Room;

public class Cancel implements BCommand {

    private Room r;
    private String time;

    public Cancel(Room r, String time) {
        this.r = r;
        this.time = time;
    }

    @Override
    public void execute() {
        boolean success = r.cancelBooking(time);
        if (success) {
            System.out.println("Booking for " + r.getName() + " at " + time + " cancelled.");
        } else {
            System.out.println("No booking found for " + r.getName() + " at " + time);
        }
    }
}
