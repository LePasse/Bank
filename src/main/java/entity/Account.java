package entity;

public class Account {
    public final Integer id;
    public final String number;
    public double balance;

    public Account(int id, String number, double balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public Account(String number, double balance) {
        this.id = null;
        this.number = number;
        this.balance = balance;
    }
}
