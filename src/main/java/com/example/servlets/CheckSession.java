package com.example.servlets;

import com.example.accounts.AccountService;
import com.example.accounts.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/session")
public class CheckSession extends HttpServlet {

    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> d = Collections.list(session.getAttributeNames());

        PrintWriter writer = response.getWriter();
        writer.println("Session: " + d);
        writer.println("\n");
        writer.println("Accounts: ");
        writer.println("\n");

        Map<String, UserProfile> f = accountService.getProfiles();
        for (Map.Entry<String, UserProfile> user : f.entrySet()){
            UserProfile u = user.getValue();

            writer.println(u.getUsername() + " " + u.getPassword() + " " + u.getEmail());
            writer.println("\n");
        }
    }
}
