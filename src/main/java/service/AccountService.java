package service;

import DAO.sql.AccountDAOSQL;


public class AccountService {
    public static boolean increaseBalance(int id, double amount){
        try {
            new AccountDAOSQL().increaseBalance(id,amount);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
