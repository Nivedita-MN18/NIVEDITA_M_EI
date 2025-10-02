
public class Factory {

    // Factory method
    public static Vehicle createVehicle(String type) {
        if (type == null) return null;
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            case "truck" -> new Truck();
            default -> null;
        };
    }
}
