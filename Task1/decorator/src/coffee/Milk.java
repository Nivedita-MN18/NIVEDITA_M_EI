package coffee;

public class Milk extends AddOnDecorator {
    public Milk(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", Milk"; }

    @Override
    public double getCost() { return beverage.getCost() + 10; }
}

