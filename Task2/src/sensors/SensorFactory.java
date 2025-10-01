package sensors;

public class SensorFactory {
    public static occupancyObserver createSensor(String type) {
        return switch (type.toLowerCase()) {
            case "ac" -> new AC();
            case "light" -> new light();
            case "occupancy" -> new occupancy();
            default -> throw new IllegalArgumentException("Unknown sensor type: " + type);
        };
    }
}
