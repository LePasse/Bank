package entity;

import java.util.ArrayList;
import java.util.List;

import DAO.sql.UserDAOSQL;
import service.UserService;

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

    public List<Card> cards;

    public User(int id, String name, String surname, String email, Role role, String passwordHash) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.cards = null;
        try {
            this.cards = new UserDAOSQL().getCards(this.id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public User(String name, String surname, String email, String passwordHash) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = Role.CUSTOMER;
        this.cards = null;
    }
}
