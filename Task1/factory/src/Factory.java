import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Factory {
    private static final Map<String, Supplier<Transport>> transportMap = new HashMap<>();

    static {
        transportMap.put("car", Car::new);
        transportMap.put("bike", Bike::new);
        transportMap.put("bus", Bus::new);
    }

    public static Transport getTransport(String type) {
        Supplier<Transport> transport = transportMap.get(type.toLowerCase());
        if (transport != null) return transport.get();
        throw new IllegalArgumentException("Unknown transport: " + type);
    }

    public static void registerTransport(String type, Supplier<Transport> supplier) {
        transportMap.put(type.toLowerCase(), supplier);
    }
}
