package model;

public class Account {
    private long id;
    private String user;
    private double balance;
    private boolean authenticated;

    public Account(long id, String user, double balance, boolean authenticated) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.authenticated = authenticated;
    }

    public long getId() { return id; }
    public String getUser() { return user; }
    public double getBalance() { return balance; }
    public boolean isAuthenticated() { return authenticated; }

    public void setBalance(double balance) { this.balance = balance; }
    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }
}
