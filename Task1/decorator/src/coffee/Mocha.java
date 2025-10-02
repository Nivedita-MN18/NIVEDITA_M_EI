package coffee;

public class Mocha extends AddOnDecorator {
    public Mocha(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", Mocha"; }

    @Override
    public double getCost() { return beverage.getCost() + 15; }
}

