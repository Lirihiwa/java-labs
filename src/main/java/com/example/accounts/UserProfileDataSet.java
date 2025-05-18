package com.example.accounts;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="users")
public class UserProfileDataSet implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    public UserProfileDataSet(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserProfileDataSet() {

    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileDataSet user = (UserProfileDataSet) o;
        return java.util.Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(username);
    }
}
