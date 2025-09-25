package booking;

import model.room;

public class cancel implements BCommand {

    private room r;
    private String time;

    public cancel(room r, String time) {
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
