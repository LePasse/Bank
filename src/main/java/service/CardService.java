package service;

import DAO.sql.AccountDAOSQL;
import DAO.sql.CardDAOSQL;
import DAO.sql.UserDAOSQL;
import entity.Account;
import entity.Card;
import entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CardService {
    public static Card createCard(int userID, String name) {
        try {
            User user = new UserDAOSQL().getUserByID(userID);
            Card card = new Card(generateNumber(),generateDate(),name);

            new CardDAOSQL().addCard(card);
            new AccountDAOSQL().addAccount(card.account);

            user.cards.add(card);

            } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
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
