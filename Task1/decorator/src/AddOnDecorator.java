// AddOnDecorator.java
public class AddOnDecorator extends CoffeeDecorator {
    private String addOnName;
    private double addOnCost;

    public AddOnDecorator(Coffee coffee, String addOnName, double addOnCost) {
        super(coffee);
        this.addOnName = addOnName;
        this.addOnCost = addOnCost;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", " + addOnName;
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + addOnCost;
    }
}
