package handlers;

import model.TransferRequest;

import java.util.HashMap;
import java.util.Map;

public class LimitHandler extends handler {
    private static final double DAILY_LIMIT = 50000.0;
    private Map<Long, Double> dailyTransfers = new HashMap<>();

    @Override
    protected boolean process(TransferRequest request) {
        long accId = request.getFrom().getId();
        double already = dailyTransfers.getOrDefault(accId, 0.0);
        if (already + request.getAmount() > DAILY_LIMIT) {
            System.out.println("Daily transfer limit exceeded.");
            return false;
        }
        dailyTransfers.put(accId, already + request.getAmount());
        return true;
    }
}
