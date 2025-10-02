package items;

import packing.Packing;
import packing.Bottle;

public abstract class Drink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}
