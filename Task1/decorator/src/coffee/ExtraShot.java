package coffee;

public class ExtraShot extends AddOnDecorator {
    public ExtraShot(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", Extra Shot"; }

    @Override
    public double getCost() { return beverage.getCost() + 20; }
}
