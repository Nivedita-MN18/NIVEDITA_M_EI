package coffee;

public class WhippedCream extends AddOnDecorator {
    public WhippedCream(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", Whipped Cream"; }

    @Override
    public double getCost() { return beverage.getCost() + 12; }
}

