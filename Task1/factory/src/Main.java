public class Main {
    public static void main(String[] args) {
        Transport t1 = Factory.getTransport("car");
        t1.bookRide();

        Transport t2 = Factory.getTransport("bike");
        t2.bookRide();

        Transport t3 = Factory.getTransport("bus");
        t3.bookRide();

        Factory.registerTransport("scooter", () -> () -> System.out.println("Scooter ride booked 🛵"));
        Transport t4 = Factory.getTransport("scooter");
        t4.bookRide();
    }
}
