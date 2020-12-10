package service;

import DAO.sql.UserDAOSQL;
import entity.Card;
import entity.User;

import java.util.List;

public class UserService {
        public static User register(String name, String surname, String email, String phash) {
            if (!name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !phash.isEmpty()) {
                try {
                    if (!(new UserDAOSQL().isUserExists(email))) {
                        User user = new User(name, surname, email, phash);
                        if (new UserDAOSQL().addUser(user)) {
                            return user;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public static User auth(String email, String phash) {
            try {
                User user = new UserDAOSQL().getUserByEmail(email);
                if (user != null && user.passwordHash.equals(phash)) {
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}
