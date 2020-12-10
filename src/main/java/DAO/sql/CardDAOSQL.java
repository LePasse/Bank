package DAO.sql;

import DAO.CardDAO;
import DAO.UserDAO;
import entity.Card;
import entity.User;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAOSQL implements CardDAO {

    @Override
    public boolean addCard(Card card) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement addUserQuery = connection.prepareStatement("INSERT INTO card (accountID, number, date, name) VALUES (?, ?, ?, ?)");
            addUserQuery.setString(1, String.valueOf(card.account.id));
            addUserQuery.setString(2, card.number);
            addUserQuery.setString(3, card.date);
            addUserQuery.setString(4, card.name);
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
