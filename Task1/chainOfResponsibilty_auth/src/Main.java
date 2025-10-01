import handlers.*;
import model.Account;
import model.TransferRequest;
import service.AccountService;

public class Main {
    public static void main(String[] args) {
        // sample accounts
        Account acc1 = new Account(1, "Nivi", 60000.0, true);
        Account acc2 = new Account(2, "mohan", 2000.0, true);

        TransferRequest request = new TransferRequest(acc1, acc2, 15000.0);

        // build chain
        handler chain = new auth();
        chain.linkWith(new BalanceHandler())
                .linkWith(new LimitHandler())
                .linkWith(new ApprovalHandler());

        // process
        if (chain.handle(request)) {
            new AccountService().transfer(request);
        } else {
            System.out.println("Transfer failed.");
        }
    }
}
