package DAO;

import entity.Association;

import java.util.List;

public interface AssociationDAO {
    List<Integer> getIDs(int userID) throws Exception;
}
