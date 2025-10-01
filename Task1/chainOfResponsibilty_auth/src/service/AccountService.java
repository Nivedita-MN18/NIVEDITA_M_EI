package service;
import model.Account;
import model.TransferRequest;

public class AccountService {

    public void transfer(TransferRequest request) {
        double amount = request.getAmount();
        Account from = request.getFrom();
        Account to = request.getTo();

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        System.out.println("Transfer successful: " + amount +
                " from " + from.getUser() + " to " + to.getUser());
        System.out.println("New balance of " + from.getUser() + ": " + from.getBalance());
        System.out.println("New balance of " + to.getUser() + ": " + to.getBalance());
    }
}
