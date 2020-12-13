package DAO.sql;

import DAO.AssociationDAO;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssociationDAOSQL implements AssociationDAO {

    @Override
    public List<Integer> getIDs(int userID) throws Exception {
        Connection connection = DBConnection.connect();

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM association WHERE userID LIKE ?");
            // get card ids
            List<Integer> ids = new ArrayList<>();

            query.setString(1, Integer.toString(userID));
            ResultSet result = query.executeQuery();

            while (result.next()) {
                int _id = result.getInt(3);
                ids.add(_id);
            }
            return ids;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addAssociation(int userID, int cardID) throws Exception {
        Connection connection = DBConnection.connect();
        try {
            PreparedStatement query = connection.prepareStatement("INSERT INTO association (userID, cardID) VALUES (?, ?)");
            query.setString(1, String.valueOf(userID));
            query.setString(2, String.valueOf(cardID));
            query.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
