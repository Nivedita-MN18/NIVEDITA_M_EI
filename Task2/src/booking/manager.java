package booking;

public class manager {

    // Execute any command (Invoker)
    public void executeCommand(BCommand command) {
        command.execute();
    }
}
