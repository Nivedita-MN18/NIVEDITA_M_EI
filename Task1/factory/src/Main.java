import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Vehicle Factory ===");

        while (running) {
            System.out.println("\nChoose vehicle to create:");
            System.out.println("1. Car");
            System.out.println("2. Bike");
            System.out.println("3. Truck");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            Vehicle vehicle = null;

            switch (choice) {
                case 1 -> vehicle = Factory.createVehicle("car");
                case 2 -> vehicle = Factory.createVehicle("bike");
                case 3 -> vehicle = Factory.createVehicle("truck");
                case 4 -> {
                    running = false;
                    continue;
                }
                default -> {
                    System.out.println("Invalid choice! Try again.");
                    continue;
                }
            }

            if (vehicle != null) {
                System.out.println("\nCreated a " + vehicle.getType());
                vehicle.start();
                vehicle.stop();
            } else {
                System.out.println("Vehicle creation failed!");
            }
        }

        System.out.println("Exiting Vehicle Factory...");
        sc.close();
    }
}
