package com.example.accounts;

import com.example.orm.UserProfileDao;
import org.hibernate.PersistentObjectException;


public class AccountService {

    UserProfileDao dao = new UserProfileDao();

    public boolean registerUser(String username, String password, String email) {
        if(username == null
                || username.trim().isEmpty()
                || password == null
                || password.trim().isEmpty()
                || email == null
                || email.trim().isEmpty()) {
            return false;
        }

        try {
            dao.save(new UserProfileDataSet(username, password, email));
            return true;
        } catch (PersistentObjectException e) {
            return true;
        }
    }

    public UserProfileDataSet loginUser(String username, String password) {
        return dao.getUserProfileByUsernameAndPassword(username, password);
    }
}
