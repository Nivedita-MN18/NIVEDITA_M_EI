package handlers;

import model.TransferRequest;

public abstract class handler {
    private handler next;

    public handler linkWith(handler next) {
        this.next = next;
        return next;
    }

    public boolean handle(TransferRequest request) {
        if (!process(request)) {
            return false;
        }
        if (next == null) return true;
        return next.handle(request);
    }

    protected abstract boolean process(TransferRequest request);
}
