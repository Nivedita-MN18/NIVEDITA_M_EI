package coffee;

public abstract class AddOnDecorator implements Beverage {
    protected Beverage beverage;

    public AddOnDecorator(Beverage beverage) {
        if (beverage == null) throw new IllegalArgumentException("Beverage cannot be null");
        this.beverage = beverage;
    }

    @Override
    public abstract String getDescription();

    @Override
    public abstract double getCost();
}
