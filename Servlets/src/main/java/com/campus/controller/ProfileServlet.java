package com.campus.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.campus.dao.StudentDAO;
import com.campus.model.Student;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student user = (Student) session.getAttribute("user");

        // Security check
        if (user == null || !"student".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.html");
            return;
        }

        String action = req.getParameter("action");

        if ("update-cgpa".equals(action)) {
            try {
                double newCgpa = Double.parseDouble(req.getParameter("newCgpa"));
                
                // 1. Update in Database
                boolean success = studentDAO.updateStudentCgpa(user.getStudentId(), newCgpa);

                if (success) {
                    // 2. Update the user object in the LIVE session memory!
                    // If we don't do this, the dashboard will still show the old CGPA until they log out.
                    user.setCgpa(newCgpa);
                    session.setAttribute("user", user);

                    resp.sendRedirect("profile.jsp?status=success");
                } else {
                    resp.sendRedirect("profile.jsp?status=error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("profile.jsp?status=error");
            }
        } else {
            resp.sendRedirect("dashboard.jsp");
        }
    }
}