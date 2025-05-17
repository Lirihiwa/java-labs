package com.example.servlets;

import com.example.accounts.AccountService;
import com.example.accounts.UserProfile;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {

    private final String STORAGE_PATH = "D:\\user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");
        String username = userProfile.getUsername();

        Path userHome = Paths.get(STORAGE_PATH, username).toAbsolutePath().normalize();

        if (!Files.exists(userHome)) {
            try {
                Files.createDirectory(userHome);
            } catch (IOException e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            }
        }

        String reqPath = req.getParameter("path");
        Path currentPath;

        if (reqPath == null || reqPath.trim().isEmpty()) {
            currentPath = userHome;
        } else {
            try {
                currentPath = Paths.get(reqPath).toAbsolutePath().normalize();
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            }

            File currentFile = currentPath.toFile();
            File userHomeFile = userHome.toFile();

            if (!currentFile.getCanonicalPath().startsWith(userHomeFile.getCanonicalPath())) {
                resp.sendRedirect(req.getContextPath() + "/files");
                return;
            }
        }

        File currentDirectory = currentPath.toFile();

        req.setAttribute("currentPath", currentPath.toString().replace('\\', File.separatorChar));
        req.setAttribute("files", currentDirectory.listFiles());
        req.setAttribute("userHome", userHome.toString().replace('\\', File.separatorChar));
        req.setAttribute("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        req.getRequestDispatcher("filePeaker.jsp").forward(req, resp);
    }
}