
import coffee.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Beverage beverage = null;

        System.out.println("=== Welcome to Coffee Builder ===");

        while (beverage == null) {
            System.out.println("\nChoose base beverage:");
            System.out.println("1. Espresso (₹50)");
            System.out.println("2. Latte (₹60)");
            System.out.println("3. Cappuccino (₹70)");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> beverage = new coffee.Espresso();
                case 2 -> beverage = new coffee.Latte();
                case 3 -> beverage = new Cappuccino();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        boolean addingAddOns = true;
        while (addingAddOns) {
            System.out.println("\nAdd-ons available:");
            System.out.println("1. Milk (+₹10)");
            System.out.println("2. Mocha (+₹15)");
            System.out.println("3. Whipped Cream (+₹12)");
            System.out.println("4. Caramel Syrup (+₹15)");
            System.out.println("5. Extra Shot (+₹20)");
            System.out.println("6. Finish");
            System.out.print("Enter choice: ");

            int addonChoice = sc.nextInt();
            switch (addonChoice) {
                case 1 -> beverage = new Milk(beverage);
                case 2 -> beverage = new Mocha(beverage);
                case 3 -> beverage = new WhippedCream(beverage);
                case 4 -> beverage = new CaramelSyrup(beverage);
                case 5 -> beverage = new ExtraShot(beverage);
                case 6 -> addingAddOns = false;
                default -> System.out.println("Invalid choice! Try again.");
            }
        }

        System.out.println("\n=== Your Order ===");
        System.out.println(beverage.getDescription());
        System.out.println("Total Cost: ₹" + beverage.getCost());
        sc.close();
    }
}
