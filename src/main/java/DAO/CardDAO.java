package DAO;

import entity.Card;
import entity.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CardDAO {
    boolean addCard(Card card) throws Exception;
}
