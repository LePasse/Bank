package DAO;

import entity.Card;
import entity.User;

import java.util.List;


public interface UserDAO {
    User getUserByEmail(String email) throws Exception;
    User getUserByID(int id) throws Exception;
    List<Card> getCards(int id) throws Exception;
    boolean addUser(User user) throws Exception;
    boolean isUserExists(String email) throws Exception;
}
