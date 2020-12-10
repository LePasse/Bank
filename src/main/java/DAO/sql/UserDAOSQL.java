package DAO.sql;

import DAO.UserDAO;
import entity.User;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOSQL implements UserDAO {
    @Override
    public User getUserByEmail(String email) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM user WHERE email LIKE ?");
            query.setString(1, email);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                int _id = result.getInt(1);
                String _name = result.getString(2);
                String _surname = result.getString(3);
                String _email = result.getString(4);
                User.Role _role = User.Role.valueOf(result.getString(5).toUpperCase());
                String _passwordHash = result.getString(6);
                return new User(_id, _name, _surname, _email, _role,  _passwordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByID(int id) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM user WHERE id LIKE ?");
            query.setString(1, Integer.toString(id));

            ResultSet result = query.executeQuery();
            if (result.next()) {
                int _id = result.getInt(1);
                String _name = result.getString(2);
                String _surname = result.getString(3);
                String _email = result.getString(4);
                User.Role _role = User.Role.valueOf(result.getString(5).toUpperCase());
                String _passwordHash = result.getString(6);
                return new User(_id, _name, _surname, _email, _role,  _passwordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getUsers() throws Exception {
        Connection connection = DBConnection.connect();

        try {
            List<User> users = new ArrayList<>();

            PreparedStatement query = connection.prepareStatement("SELECT * FROM user");
            ResultSet result = query.executeQuery();
            while (result.next()) {
                int _id = result.getInt(1);
                String _name = result.getString(2);
                String _surname = result.getString(3);
                String _email = result.getString(4);
                User.Role _role = User.Role.valueOf(result.getString(5).toUpperCase());
                String _passwordHash = result.getString(6);
               users.add(new User(_id, _name, _surname, _email, _role,  _passwordHash));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addUser(User user) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement addUserQuery = connection.prepareStatement("INSERT INTO user (name, surname, email, role, password_hash) VALUES (?, ?, ?, ?, ?)");
            addUserQuery.setString(1, user.name);
            addUserQuery.setString(2, user.surname);
            addUserQuery.setString(3, user.email);
            addUserQuery.setString(4, user.role.name().toLowerCase());
            addUserQuery.setString(5, user.passwordHash);
            addUserQuery.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean isUserExists(String email) throws Exception {
        return getUserByEmail(email) != null;
    }
}
