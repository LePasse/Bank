package DAO;

import entity.Card;

public interface CardDAO {
    boolean addCard(Card card) throws Exception;
    boolean block(int id) throws Exception;
    boolean unblock(int id) throws Exception;
    Card getCardByID(int id) throws Exception;
    Card getCardByNumber(String number) throws Exception;
    int getAccountID(int cardID) throws Exception;
}
