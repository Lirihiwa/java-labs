package com.example.accounts;

import java.io.Serial;
import java.io.Serializable;

public class UserProfile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password;
    private final String email;

    public UserProfile(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile user = (UserProfile) o;
        return java.util.Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(username);
    }
}
