package handlers;


import model.TransferRequest;

public class ApprovalHandler extends handler {
    private static final double APPROVAL_THRESHOLD = 20000.0;

    @Override
    protected boolean process(TransferRequest request) {
        if (request.getAmount() > APPROVAL_THRESHOLD) {
            System.out.println("Transfer requires manager approval. Pending...");
            return false;
        }
        return true;
    }
}
