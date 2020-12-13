package service;

import DAO.sql.AccountDAOSQL;
import DAO.sql.AssociationDAOSQL;
import DAO.sql.CardDAOSQL;
import DAO.sql.UserDAOSQL;
import entity.Account;
import entity.Card;
import entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CardService {
    public static boolean createCard(int userID, String name) {
        try {
            User user = new UserDAOSQL().getUserByID(userID);
            Card card = new Card(generateNumber(),generateDate(),name);

            new CardDAOSQL().addCard(card);
            new AccountDAOSQL().addAccount(card.account);
            new AssociationDAOSQL().addAssociation(user.id, card.id);

            user.cards.add(card);
            return true;
            } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static boolean transaction(Card from, Card to, String am){
        try {
            double amount = Double.parseDouble(am);
            if (!from.blocked && from.account.balance>=amount){
                new AccountDAOSQL().decreaseBalance(from.account.id,amount);
                new AccountDAOSQL().increaseBalance(to.account.id, amount);
                from.account.balance -= amount;
                to.account.balance += amount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean increaseBalance(int id, double amount){
        try{
            Card card = new CardDAOSQL().getCardByID(id);
            card.account.balance += amount;
            AccountService.increaseBalance(card.account.id,amount);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static String generateNumber(){
        int leftLimit = 48;
        int rightLimit = 57;
        int targetStringLength = 16;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public static String generateDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String generatedDate = dtf.format(now);

        char[] ch = new char[4];

        // Copy character by character into array
        for (int i = 3; i < generatedDate.length(); i++) {
            ch[i] = generatedDate.charAt(i);
        }

        Integer year = Integer.parseInt(ch.toString());
        year += 4;

        ch = new char[2];
        ch[0] = year.toString().charAt(2);
        ch[1] = year.toString().charAt(3);

        char[] str = new char[5];
        for (int i = 0; i < 3; i++) {
            str[i] = generatedDate.charAt(i);
        }
        str[3] = ch[0]; str[4] = ch[1];
        return str.toString();
    }
}
