package entity;

import java.util.ArrayList;


public class User {

    public enum Role {
        ADMIN,
        CUSTOMER
    }

    public final Integer id;
    public final String name;
    public final String surname;
    public final String email;
    public final Role role;
    public final String passwordHash;

    public ArrayList<Card> Cards;

    public User(int id, String name, String surname, String email, Role role, String passwordHash) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User(String name, String surname, String email, String passwordHash) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = Role.CUSTOMER;
    }
}
