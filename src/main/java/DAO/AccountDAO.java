package DAO;

import entity.Account;

public interface AccountDAO {
    boolean addAccount(Account account) throws Exception;
    Account getAccountByID(int id) throws Exception;
    boolean isAccountExists(String number) throws Exception;
    Account getAccountByNumber(String number) throws Exception;
    boolean increaseBalance(int id, Double amount) throws Exception;
    boolean decreaseBalance(int id, Double amount) throws Exception;
}
