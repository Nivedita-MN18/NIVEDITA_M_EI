package coffee;

public class CaramelSyrup extends AddOnDecorator {
    public CaramelSyrup(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", Caramel Syrup"; }

    @Override
    public double getCost() { return beverage.getCost() + 15; }
}

