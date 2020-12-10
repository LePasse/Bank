package entity;

public class Card {
    public final String Number;
    public final String Date;
    public final String Name;
    public final Account Acc;

    public Card(String number, String date, String name, Account acc) {
        this.Number = number;
        this.Date = date;
        this.Name = name;
        this.Acc = acc;
    }
}
