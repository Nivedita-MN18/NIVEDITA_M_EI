package handlers;

import model.TransferRequest;

public class BalanceHandler extends handler {
    @Override
    protected boolean process(TransferRequest request) {
        if (request.getFrom().getBalance() < request.getAmount()) {
            System.out.println("Insufficient balance. Transfer denied.");
            return false;
        }
        return true;
    }
}
