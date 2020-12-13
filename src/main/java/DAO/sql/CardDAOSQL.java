package DAO.sql;

import DAO.CardDAO;
import DAO.UserDAO;
import entity.Account;
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
            PreparedStatement addUserQuery = connection.prepareStatement("INSERT INTO card (accountID, number, date, name, blocked) VALUES (?, ?, ?, ?, ?)");
            addUserQuery.setString(1, String.valueOf(card.account.id));
            addUserQuery.setString(2, card.number);
            addUserQuery.setString(3, card.date);
            addUserQuery.setString(4, card.name);
            addUserQuery.setString(5, String.valueOf(card.blocked));
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Card getCardByID(int id) throws Exception {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM card WHERE id LIKE ?");
            query.setString(1, Integer.toString(id));

            ResultSet result = query.executeQuery();
            if (result.next()) {
                int _id = result.getInt(1);
                String _number = result.getString(2);
                String _date = result.getString(3);
                String _name = result.getString(4);
                String _blocked = result.getString(5);
                return new Card(_id,_number, _date, _name, Boolean.parseBoolean(_blocked));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean block(int id) throws Exception {
        Connection connection = DBConnection.connect();
        Card card = new CardDAOSQL().getCardByID(id);
        try {
            card.blocked = true;
            PreparedStatement addUserQuery = connection.prepareStatement("UPDATE card (accountID, number, date, name, blocked) VALUES (?, ?, ?, ?, ?)");
            addUserQuery.setString(1, String.valueOf(card.account.id));
            addUserQuery.setString(2, card.number);
            addUserQuery.setString(3, card.date);
            addUserQuery.setString(4, card.name);
            addUserQuery.setString(5, String.valueOf(card.blocked));
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean unblock(int id) throws Exception {
        Connection connection = DBConnection.connect();
        Card card = new CardDAOSQL().getCardByID(id);
        try {
            card.blocked = false;
            PreparedStatement addUserQuery = connection.prepareStatement("UPDATE card (accountID, number, date, name, blocked) VALUES (?, ?, ?, ?, ?)");
            addUserQuery.setString(1, String.valueOf(card.account.id));
            addUserQuery.setString(2, card.number);
            addUserQuery.setString(3, card.date);
            addUserQuery.setString(4, card.name);
            addUserQuery.setString(5, String.valueOf(card.blocked));
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Card getCardByNumber(String number) throws Exception {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM card WHERE number LIKE ?");
            query.setString(1, number);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                int _id = result.getInt(1);
                String _number = result.getString(2);
                String _date = result.getString(3);
                String _name = result.getString(4);
                String _blocked = result.getString(5);
                return new Card(_id,_number, _date, _name, Boolean.parseBoolean(_blocked));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAccountID(int cardID) throws Exception {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM card WHERE id LIKE ?");
            query.setString(1, String.valueOf(cardID));

            ResultSet result = query.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
