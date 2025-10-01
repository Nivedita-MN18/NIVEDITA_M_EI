package model;
public class TransferRequest {
    private Account from;
    private Account to;
    private double amount;

    public TransferRequest(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Account getFrom() { return from; }
    public Account getTo() { return to; }
    public double getAmount() { return amount; }
}
