// Main.java
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " -> Rs." + coffee.getCost());

        coffee = new AddOnDecorator(coffee, "Milk", 10.0);
        coffee = new AddOnDecorator(coffee, "Sugar", 5.0);
        coffee = new AddOnDecorator(coffee, "Whipped Cream", 20.0);

        System.out.println(coffee.getDescription() + " -> Rs." + coffee.getCost());
    }
}
