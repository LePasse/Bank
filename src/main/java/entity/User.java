package entity;

import java.util.ArrayList;


public class User {

    public enum Role {
        ADMIN,
        CUSTOMER
    }

    public final int id;
    public final String Name;
    public final String Surname;
    public final String email;
    public final Role role;
    public final String passwordHash;

    public ArrayList<Card> Cards;

    public User(int id, String name, String surname, String email, Role role, String passwordHash) {
        this.id = id;
        this.Name = name;
        this.Surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User(int id, String name, String surname, String email, String passwordHash) {
        this.id = id;
        this.Name = name;
        this.Surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = Role.CUSTOMER;
    }
}
