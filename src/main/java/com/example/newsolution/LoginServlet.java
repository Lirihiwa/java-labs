package com.example.newsolution;

import com.example.accounts.AccountService;
import com.example.accounts.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AccountService accountService = new AccountService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession(false) != null &&
                req.getSession(false).getAttribute("userProfile") != null) {
            resp.sendRedirect(req.getContextPath() + "/files");
            return;
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserProfile user = accountService.loginUser(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userProfile", user);
            resp.sendRedirect(req.getContextPath() + "/files");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
