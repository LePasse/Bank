package DAO.sql;

import DAO.AccountDAO;
import entity.Card;
import entity.Account;
import entity.User;
import utils.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountDAOSQL implements AccountDAO {
    @Override
    public boolean addAccount(Account account) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement addUserQuery = connection.prepareStatement("INSERT INTO account (number,balance) VALUES (?, ?)");

            addUserQuery.setString(1, account.number);
            addUserQuery.setString(2, String.valueOf(account.balance));
            addUserQuery.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccountByID(int id) throws Exception {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM account WHERE id LIKE ?");
            query.setString(1, Integer.toString(id));

            ResultSet result = query.executeQuery();
            if (result.next()) {
                int _id = result.getInt(1);
                String _number = result.getString(2);
                String _balance = result.getString(3);
                return new Account(_id,_number,Double.parseDouble(_balance));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getAccountByNumber(String number) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM account WHERE number LIKE ?");
            query.setString(1, number);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                int _id = result.getInt(1);
                String _number = result.getString(2);
                String _balance = result.getString(3);
                return new Account(_id,_number,Double.parseDouble(_balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isAccountExists(String number) throws Exception {
        return getAccountByNumber(number) != null;
    }

    @Override
    public boolean increaseBalance(int id, Double amount) throws Exception {
        Connection connection = DBConnection.connect();

        Account account = new AccountDAOSQL().getAccountByID(id);

        try {
            account.balance += amount;
            PreparedStatement addUserQuery = connection.prepareStatement("UPDATE account (number,balance) VALUES (?, ?)");

            addUserQuery.setString(1, account.number);
            addUserQuery.setString(2, String.valueOf(account.balance));
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean decreaseBalance(int id, Double amount) throws Exception {
        Connection connection = DBConnection.connect();

        Account account = new AccountDAOSQL().getAccountByID(id);

        try {
            account.balance -= amount;
            PreparedStatement addUserQuery = connection.prepareStatement("UPDATE account (number,balance) VALUES (?, ?)");

            addUserQuery.setString(1, account.number);
            addUserQuery.setString(2, String.valueOf(account.balance));
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
