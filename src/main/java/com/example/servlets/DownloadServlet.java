package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String encodedPath = req.getParameter("path");
            if (encodedPath == null || encodedPath.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing path parameter");
                return;
            }

            String filepath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8)
                                      .replace('/', File.separatorChar);

            Path file = Paths.get(filepath).normalize().toAbsolutePath();


            String contentType = Files.probeContentType(file);
            resp.setContentType(contentType != null ? contentType : "application/octet-stream");

            String filename = file.getFileName().toString();

            resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            Files.copy(file, resp.getOutputStream());

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                         "Error downloading file: " + e.getMessage());
        }
    }
}