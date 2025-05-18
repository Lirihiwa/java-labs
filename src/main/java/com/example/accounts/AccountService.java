package com.example.accounts;

import java.sql.*;

public class AccountService {

    String URL = "jdbc:mysql://localhost:3306/mydb";
    String USER = "root";
    String PASSWORD = "123454321";

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
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT IGNORE INTO users (username, password, email) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, email);

                    int rows = preparedStatement.executeUpdate();

                    return rows >= 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserProfile loginUser(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT username, password, email FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet result = preparedStatement.executeQuery();

                if (result.next()) {
                    return new UserProfile(
                            result.getString("username"),
                            result.getString("password"),
                            result.getString("email")
                    );
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
