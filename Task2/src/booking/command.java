package booking;

import model.Room;

public class Command implements BCommand {
    private Room r;
    private String time;
    private int duration;

    public Command(Room r, String time, int duration) {
        this.r = r;
        this.time = time;
        this.duration = duration;
    }

    @Override
    public void execute() {
        boolean success = r.book(time, duration);
        if (success) {
            System.out.println(r.getName() + " booked at " + time + " for " + duration + " mins.");
        } else {
            System.out.println(r.getName() + " is already booked at " + time);
        }
    }
}
