package entity;

import DAO.sql.AccountDAOSQL;
import DAO.sql.CardDAOSQL;
import entity.Account;

import java.nio.charset.Charset;
import java.util.Random;

public class Card {
    public final Integer id;
    public final String number;
    public final String date;
    public final String name;
    public Account account;

    public boolean blocked;

    public Card(int id, String number, String date, String name, boolean blocked) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.name = name;
        try {
            this.account = new AccountDAOSQL().getAccountByID(new CardDAOSQL().getAccountID(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.blocked = blocked;
    }
    public Card(String number, String date, String name) {
        this.id = 0;
        this.number = number;
        this.date = date;
        this.name = name;
        this.account = new Account(generateNumber(),0);
        this.blocked = false;
    }

    public String generateNumber() {
        int leftLet = 65;
        int rightLet = 90;
        int leftLimit = 48;
        int rightLimit = 57;
        int letLength = 5;
        int numLength = 15;
        Random random = new Random();

        StringBuilder buffer1 = new StringBuilder(letLength);
        for (int i = 0; i < letLength; i++) {
            int randomLimitedInt = leftLet + (int)
                    (random.nextFloat() * (rightLet - leftLet + 1));
            buffer1.append((char) randomLimitedInt);
        }
        StringBuilder buffer2 = new StringBuilder(letLength);
        for (int i = 0; i < numLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer2.append((char) randomLimitedInt);
        }

        String generatedString = buffer1.toString()+buffer2.toString();

        return generatedString;
    }
}
