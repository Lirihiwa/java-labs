package com.example.accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UserProfile> users = new HashMap<>();

    public boolean registerUser(String username, String password, String email) {
        if(username == null
                || username.trim().isEmpty()
                || password == null
                || password.trim().isEmpty()
                || email == null
                || email.trim().isEmpty()
                || users.containsKey(username)) {
            return false;
        }

        UserProfile newUserProfile = new UserProfile(username, password, email);
        users.put(username, newUserProfile);
        return true;
    }

    public UserProfile loginUser(String username, String password) {
        UserProfile userProfile = users.get(username);

        if(userProfile != null && userProfile.getPassword().equals(password)) {
            return userProfile;
        }

        return null;
    }

    public Map<String, UserProfile> getProfiles() {
        return users;
    }
}
