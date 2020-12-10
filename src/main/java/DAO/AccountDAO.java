package DAO;

import entity.Card;
import entity.Account;
import entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface AccountDAO {
    boolean addAccount(Account account) throws Exception;
    Account getAccountByID(int id) throws Exception;
    boolean isAccountExists(String number) throws Exception;
    Account getAccountByNumber(String number) throws Exception;
}
