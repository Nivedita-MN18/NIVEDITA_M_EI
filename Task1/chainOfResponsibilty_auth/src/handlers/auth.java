package handlers;

import model.TransferRequest;

public class auth extends handler {
    @Override
    protected boolean process(TransferRequest request) {
        if (!request.getFrom().isAuthenticated()) {
            System.out.println(" User not authenticated. Transfer denied.");
            return false;
        }
        return true;
    }
}
