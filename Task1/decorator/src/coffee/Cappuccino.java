package coffee;

public class Cappuccino implements Beverage {
    @Override
    public String getDescription() {
        return "Cappuccino";
    }

    @Override
    public double getCost() {
        return 70.0;
    }
}
