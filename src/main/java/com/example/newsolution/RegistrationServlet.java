package com.example.newsolution;

import com.example.accounts.AccountService;
import com.example.accounts.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession(false) == null
                && req.getSession(false).getAttribute("userProfile") != null) {

            resp.sendRedirect(req.getContextPath() + "/files");
            return;
        }

        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean success = accountService.registerUser(username, password, email);

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
