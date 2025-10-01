
import java.util.HashMap;
import java.util.Map;

public class ParkingSpotFactory {

    private static final Map<String, ParkingSpot> spotMap = new HashMap<>();

    public static ParkingSpot getParkingSpot(String type, double rate) {
        String key = type + "-" + rate;
        if (!spotMap.containsKey(key)) {
            spotMap.put(key, new ConcreteParkingSpot(type, rate));
        }
        return spotMap.get(key);
    }
}
